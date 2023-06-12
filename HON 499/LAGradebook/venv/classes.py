import csv
import statistics
import tkinter
from tkinter import font as tkfont

from openpyxl import Workbook
from openpyxl.styles import Alignment, Font, Color, colors, PatternFill
from openpyxl.styles.borders import Border, Side
from openpyxl.utils.cell import get_column_letter
from openpyxl.utils import quote_sheetname
from openpyxl.workbook.defined_name import DefinedName
from openpyxl.worksheet.datavalidation import DataValidation
from openpyxl.worksheet.pagebreak import Break


class Gradebook:
    book = Workbook()
    sheet = book.active
    CONTROL_START_COL = 7
    CONTROL_START_ROW = 4

    # Primary functions used in main

    def __init__(self, in_file, out_file):
        self.in_file = in_file
        self.out_file = out_file
        print('gradebook initialized')

    # This function makes a call to all of the other "create" functions, one for each sheet of the workbook.
    def create_sheets(self):
        self.create_import_sheet()
        self.create_header_sheet()
        self.create_control_sheet()
        self.create_z_score_sheet()
        self.create_top_performer_sheet()
        self.create_relative_performance_sheet()
        self.calculate_correlations()
        self.create_student_report_sheet()

    # This function creates the 'imported from blackboard' sheet and imports the data from the provided xls file
    # into the workbook.
    def create_import_sheet(self):

        # Set up basic sheet details
        self.sheet.title = 'imported from blackboard'
        self.sheet.protection.sheet = True
        self.sheet.protection.enable()

        # Using the xls import file, read through each line and each item in that line and add it to the spreadsheet
        with open(self.in_file, encoding='utf-16', newline='') as gradebook:
            gb_reader = csv.reader(gradebook, delimiter='\t')
            for x, line in enumerate(gb_reader):
                for y, item in enumerate(line):
                    item = self.correct_type(item)
                    self.sheet.cell(row=x+1, column=y+1, value=item)

    # This function creates the hidden 'headers' sheet. This sheet allows the user's selections to be persisted across
    # gradebook upload and download. The end result is a trimmed down header with additional information appended
    # to the end. This information corresponds to the top performer and relative performance data validators on the
    # control sheet.
    def create_header_sheet(self):

        # Find the highest data column from the imported data
        self.sheet = self.book['imported from blackboard']
        max_col = self.sheet.max_column

        # Set up basic sheet details
        self.book.create_sheet('headers')
        self.sheet = self.book['headers']
        self.sheet.sheet_state = self.sheet.SHEETSTATE_HIDDEN

        # Using the max column as an upper limit, create some data for each column.
        for col_idx in range(1, max_col + 1):
            col_letter = get_column_letter(col_idx)

            # Cell 3: Get the raw header data
            self.sheet.cell(3, col_idx, self.book['imported from blackboard'].cell(1, col_idx).value)

            # Cell 2: Using cell 3, take only the text on the left side of the first '|' symbol
            self.sheet.cell(2, col_idx, "=IFERROR(LEFT({0}3, FIND(\"|\", {0}3)-1), {0}3)".format(col_letter))

            # Cell 1: Using cell 2, take only the text on the left side of the first '[' symbol
            self.sheet.cell(1, col_idx, "=CONCATENATE(IFERROR(LEFT({0}2, FIND(\" [\", {0}2)-1), {0}2), \" | \", "
                                        "IF('control sheet'!{0}1=\"Yes\", \"ytp\", \"ntp\"), IF('control sheet'!{0}2="
                                        "\"Yes\", \"yrp\", \"nrp\"))"
                            .format(col_letter))

        # Update the headers in the 'imported from blackboard' sheet to reflect this new change
        self.sheet = self.book['imported from blackboard']
        for cell in self.sheet[1]:
            cell.value = "=headers!{}1".format(cell.column_letter)

    # This function creates the 'control sheet' sheet. This sheet contains original data from the 'imported from
    # blackboard' sheet, data validators allowing the user to choose which columns should factor into top performer and
    # relative performance calculations, and additional descriptive statistics for each column.
    def create_control_sheet(self):

        # Set up basic sheet details
        self.sheet = self.book.copy_worksheet(self.book['imported from blackboard'])
        self.sheet.title = 'control sheet'

        # After copying the 'imported from blackboard' sheet, re-assign the headers to be static by getting the value
        # from the 'headers' sheet. This value is what was in the 'imported from blackboard' sheet before the 'headers'
        # sheet was created.
        for cell in self.sheet[1]:
            self.sheet = self.book['headers']
            cell.value = self.sheet.cell(3, cell.col_idx).value
            self.sheet = self.book['control sheet']

        # Add top performer and relative performance data validators from the column the data starts to the max column.
        self.add_control_sheet_validator('{}1'.format(get_column_letter(self.CONTROL_START_COL)),
                                         '{}2'.format(get_column_letter(self.sheet.max_column)))

        # Add custom styling to the data (a basic table)
        self.create_custom_table('control sheet', start_row=3)

        # Add descriptive statistics to the bottom of the page
        self.add_stat_formulas()

        # Add options for customizable z scores
        self.sheet.cell(1, 1, 'Z-Score:')
        self.sheet.cell(1, 2, 0.65)
        self.sheet.cell(2, 1, 'Z-Score:')
        self.sheet.cell(2, 2, 0.65)

    # This function creates the hidden 'z-scores' sheet. This sheet is hidden from the user and is primarily used for
    # calculations made in other sheets.
    def create_z_score_sheet(self):

        # Set up basic sheet details
        self.sheet = self.book.copy_worksheet(self.book['imported from blackboard'])
        self.sheet.title = 'z-scores'
        self.sheet.sheet_state = self.sheet.SHEETSTATE_HIDDEN
        max_r = self.sheet.max_row + 1

        # Using data from the control sheet, iterate through each column and get the coordinates for standard deviation
        # and average. These coordinates are used when placing the formula into the 'z-scores' sheet.
        self.sheet = self.book['control sheet']
        for cells in self.sheet.iter_cols(min_row=self.CONTROL_START_ROW, min_col=self.CONTROL_START_COL):
            cell = cells[0]

            # Check to make sure the column contains data that a z score can be calculated for (int or float), then
            # find the coordinates for the average and standard deviation.
            if self.calc_type(cell.value) in ['INT', 'FLOAT']:
                std_dev = self.sheet.cell(self.sheet.max_row, cell.col_idx).coordinate
                avg = self.sheet.cell(self.sheet.max_row - 1, cell.col_idx).coordinate

                # For every assignment in the current column (in the control sheet), place a formula calculating the z
                # score into the z-score sheet.
                for idx, cell in enumerate(cells):
                    if idx < len(cells) - 3:  # -3 to avoid the descriptive stats at the bottom of the page
                        self.sheet = self.book['z-scores']
                        current = self.sheet.cell(cell.row, cell.col_idx).coordinate
                        self.sheet.cell(cell.row - 2, cell.col_idx, '=IFERROR((\'control sheet\'!{}-\'control sheet\'!'
                                                                    '{}) / ''\'control sheet\'!{}, 0)'
                                        .format(current, avg, std_dev))
                        self.sheet = self.book['control sheet']

                # After completing z score calculations for each column, calculate the average for the column
                self.sheet = self.book['z-scores']
                col_letter = cells[-1].column_letter
                self.sheet.cell(max_r + 2, cells[-1].col_idx,
                                '=AVERAGE({}2:{}{})'.format(col_letter, col_letter, max_r - 1))
                self.sheet = self.book['control sheet']

    # This function creates the 'top performers' sheet. This sheet shows the user how many top performances each student
    # has had and on which specific assignments they performed well on.
    def create_top_performer_sheet(self):

        # Set up basic sheet details
        self.sheet = self.book.copy_worksheet(self.book['imported from blackboard'])
        self.sheet.title = 'top performers'

        # Top Performance Count Column
        self.sheet.insert_cols(1)
        self.sheet.cell(1, 1, 'Top Count')

        # For the first column, starting after the header, set each item equal to the countif formula, freeze to left
        for cells in self.sheet.iter_rows(min_row=2, min_col=1, max_col=1):
            cell = cells[0]

            self.sheet.cell(cell.row, cell.col_idx, '=COUNTIF(B{0}:{1}{0}, "X")'
                            .format(cell.row, get_column_letter(self.sheet.max_column)))
        self.sheet.freeze_panes = 'B1'

        # Replace last access with top performance count
        self.replace_column(5, 'Top Performance Count', 'top performers')

        # Header setup

        # Take header information from the control sheet and move it over to the top performers sheet
        for cells in self.sheet.iter_cols(min_row=1, max_row=1, max_col=self.sheet.max_column - 1):
            cell = cells[0]
            self.sheet = self.book['control sheet']
            header_value = self.sheet.cell(self.CONTROL_START_ROW-1, cell.col_idx).value
            self.sheet = self.book['top performers']
            self.sheet.cell(cell.row, cell.col_idx + 1, header_value)

        # Data setup

        # For each row and column, besides the top performance count column and the header row, add data
        for cells in self.sheet.iter_rows(min_row=2, min_col=2):
            for cell in cells:
                offset_letter = get_column_letter(cell.col_idx - 1)

                # After checking that the user wants to include this item on the sheet, check to see how the item's z
                # score relates to the user-designated z score on the control sheet. If it passes the threshold, place
                # an 'X' in the cell, otherwise leave it blank.
                self.sheet.cell(cell.row, cell.col_idx,
                                '=IF(\'control sheet\'!{0}1 = "Yes", IF(\'z-scores\'!{0}{1} > \'control sheet\'!B1, "X"'
                                ', ""), "")'
                                .format(offset_letter, cell.row))

        # Add custom styling (like a table), similar to the control sheet
        self.create_custom_table('top performers', start_row=1)

    # This function creates the 'relative performance' sheet. This sheet give the user an overall look at relative
    # performance or a per-assessment evaluation of relative performance.
    def create_relative_performance_sheet(self):

        # Set up basic sheet details
        self.sheet = self.book.copy_worksheet(self.book['imported from blackboard'])
        self.sheet.title = 'relative performance'

        # Adding columns for the overall relative performance and the corresponding z score
        self.sheet.insert_cols(1, 2)
        self.sheet.cell(1, 1, 'Relative Performance')
        self.sheet.cell(1, 2, 'Average Z-Score')

        # Average Z-Score setup

        # For every row in the average z-score column, add an average calculation formula
        for cells in self.sheet.iter_rows(min_row=2, min_col=2):
            cell = cells[0]

            self.sheet.cell(cell.row, cell.col_idx, '=AVERAGE(\'z-scores\'!{0}{1}:{2}{1})'
                            .format(get_column_letter(cell.col_idx + 3), cell.row,
                                    get_column_letter(self.sheet.max_column - 2)))

        # Relative Performance setup

        # Add an arbitrary table that the user can further modify with their own numbers and labels
        initial_z_scores = [-15, -1, 0, 0.25, 0.5]
        initial_labels = ['Terrible', 'Below', 'Average', 'Above', 'Great']
        start_row = self.sheet.max_row + 2
        end_row = self.sheet.max_row + 7
        table = 'A{}:B{}'.format(start_row, end_row - 1)

        self.sheet.freeze_panes = 'C1'

        # Replace the last access column with the overall relative performance column
        self.replace_column(6, 'Relative Performance', 'relative performance')

        # Header setup

        # Take header information from the control sheet and move it over to the relative performance sheet
        for cells in self.sheet.iter_cols(min_row=1, max_row=1, max_col=self.sheet.max_column - 2):
            cell = cells[0]
            self.sheet = self.book['control sheet']
            header_value = self.sheet.cell(self.CONTROL_START_ROW-1, cell.col_idx).value
            self.sheet = self.book['relative performance']
            self.sheet.cell(cell.row, cell.col_idx + 2, header_value)

        # Data setup

        # For each row and column, besides the overall relative performance column, the average z-score column, and the
        # header row, add data.
        for cells in self.sheet.iter_rows(min_row=2, min_col=3):
            for cell in cells:
                offset_letter = get_column_letter(cell.col_idx - 2)

                # After checking that the user wants to include this item on the sheet, do a VLOOKUP using the table
                # created earlier. The z-score for each assignment is being sourced from the hidden 'z-scores' sheet.
                self.sheet.cell(cell.row, cell.col_idx,
                                '=IF(\'control sheet\'!{0}2 = "Yes", VLOOKUP(\'z-scores\'!{0}{1}, {2}, 2, TRUE), "")'
                                .format(offset_letter, cell.row, table))

        # Add custom styling (like a table), similar to the control sheet
        self.create_custom_table('relative performance', start_row=1)

        # Add the z-scores and the labels for the lookup table to the sheet
        for row_idx in range(start_row, end_row):
            self.sheet.cell(row_idx, 1, initial_z_scores[row_idx - start_row])
            self.sheet.cell(row_idx, 2, initial_labels[row_idx - start_row])

        # Do a Vlookup on the average z-score column and place the corresponding labels into the overall
        # relative performance column.
        for cells in self.sheet.iter_rows(min_row=2, max_row=start_row - 2, min_col=1, max_col=2):
            cell = cells[0]
            cell.value = '=VLOOKUP({},{},2,TRUE)'.format(cells[1].coordinate, table)

    # This function creates the 'correlations' sheet. This sheet is used to show the user the correlations between every
    # assignment in the gradebook.
    def calculate_correlations(self):

        # Set up basic sheet details
        self.sheet = self.book['control sheet']
        self.book.create_sheet('correlations')

        # lists to store each column for calculation
        first_col = []

        # variables to keep track of which columns are being compared
        first_col_idx = 7

        while first_col_idx < self.sheet.max_column:

            if self.calc_type(self.sheet.cell(4, first_col_idx).value) in ['INT', 'FLOAT']:
                for cell in self.sheet.iter_cols(min_row=4, min_col=first_col_idx, max_col=first_col_idx):
                    first_col += cell

                for i in range(3):
                    first_col.pop()

                title = self.sheet.cell(3, first_col[0].col_idx).value
                self.sheet = self.book['correlations']
                self.sheet.cell(first_col[0].col_idx, 1, value=title)
                self.sheet = self.book['control sheet']

                self.set_pairings(first_col, first_col_idx)

            first_col_idx += 1
            if first_col_idx != self.sheet.max_column:
                first_col.clear()

    # This function is used with the 'control sheet' sheet. The function will replace a user-specified column with a new
    # column from a user-specified sheet.
    def replace_column(self, replace_col, col_name, sheet_name):
        self.sheet = self.book['control sheet']

        self.sheet.cell(self.CONTROL_START_ROW - 1, replace_col, col_name)
        for cells in self.sheet.iter_rows(min_row=self.CONTROL_START_ROW, max_row=self.sheet.max_row - 3,
                                          min_col=replace_col, max_col=replace_col):
            cell = cells[0]
            self.sheet = self.book[sheet_name]
            metric = self.sheet.cell(cell.row - 2, 1)
            self.sheet = self.book['control sheet']
            self.sheet.cell(cell.row, replace_col, '=\'{}\'!{}'.format(sheet_name, metric.coordinate))

    # This function creates the 'student reports' sheet. This sheet is intended to provide meaningful information for
    # each student. Currently a WIP.
    def create_student_report_sheet(self):

        # Set up basic sheet details
        self.book.create_sheet('student reports')
        self.sheet = self.book['student reports']

        # Configuration Information
        self.sheet.cell(1, 1, "Student Reports Configuration:")
        self.sheet.cell(1, 2, "Preferences")

        # Allow users to select specific assessments to include in the reports
        self.sheet.cell(3, 1, "Add Assessment to Report:")
        data = '=\'control sheet\'!${0}${1}:${2}${1}'.format(get_column_letter(self.CONTROL_START_COL),
                                                             self.CONTROL_START_ROW - 1,
                                                             get_column_letter(self.book['control sheet'].max_column))
        dv = DataValidation(type="list", formula1=data)
        dv.add(self.sheet.cell(3, 2).coordinate)
        self.sheet.add_data_validation(dv)

        # Creating categories and default values
        self.sheet.cell(4, 1, "Display Overall Relative Performance:")
        self.sheet.cell(4, 2, "Yes")
        self.sheet.cell(5, 1, "Display Top Performance Counts:")
        self.sheet.cell(5, 2, "Yes")
        self.sheet.cell(6, 1, "Display General Message:")
        self.sheet.cell(6, 2, "Yes")

        # Adding data validator to previous categories
        dv = DataValidation(type="list", formula1='"Yes, No"')
        dv.add('B4:B6')
        self.sheet.add_data_validation(dv)

        self.sheet.cell(7, 1, "Overall Grade Column:")

        self.sheet.cell(9, 1, "Selectable Comment Messages")
        self.sheet.cell(10, 1, "Sample Message 1")
        self.sheet.cell(12, 1, "Sample Message 2")
        self.sheet.cell(14, 1, "Sample Message 3")
        self.sheet.cell(16, 1, "Sample Message 4")
        self.sheet.cell(18, 1, "Sample Message 5")
        self.sheet.cell(20, 1, "Sample Message 6")
        self.sheet.cell(22, 1, "Sample Message 7")
        self.sheet.cell(24, 1, "Sample Message 8")
        self.sheet.cell(26, 1, "Sample Message 9")
        self.sheet.cell(28, 1, "Sample Message 10")

        page_break = Break(id=7)
        self.sheet.row_breaks.append(page_break)

    # This function will call the set_column_width for each of the created sheets (that aren't hidden).
    def fix_column_widths(self):
        self.set_column_width('imported from blackboard')
        self.set_column_width('control sheet')
        self.set_column_width('top performers')
        self.set_column_width('relative performance')
        self.set_column_width('correlations')
        self.set_column_width('student reports')

    # Secondary functions used by primary functions

    # This function aims to determine whether a "string" is an integer or a float, or is actually a string. This is
    # useful for placing data into a spreadsheet with the correct formatting.
    def calc_type(self, in_str):
        try:
            int(in_str)
            return 'INT'
        except:
            try:
                float(in_str)
                return 'FLOAT'
            except:
                return 'STR'

    # This function will check the type of an input and, if it is an integer or float, the input will be updated.
    def correct_type(self, item):
        cur_type = self.calc_type(item)
        fixed_item = item

        if cur_type is 'INT':
            fixed_item = int(item)
        if cur_type is 'FLOAT':
            fixed_item = float(item)

        return fixed_item

    # This function creates a styling similar to a table on the specified sheet starting at the start_row row.
    def create_custom_table(self, sheet_name, start_row):
        self.sheet = self.book[sheet_name]

        tbl_border = Border(top=Side(style='medium'),
                            bottom=Side(style='medium'),
                            left=Side(style='medium'))

        tbr_border = Border(top=Side(style='medium'),
                            bottom=Side(style='medium'),
                            right=Side(style='medium'))

        font = Font(size=14, bold=True)

        # Iterating through the entire sheet, applying the styling, starting at the start_row
        for cells in self.sheet.iter_cols(min_row=start_row):
            for cell in cells:

                # Added a gray background to every other line, for a banding effect
                if cell.row % 2 is 0:
                    cell.fill = PatternFill(start_color="DCDCDC", fill_type="solid")

                # Add custom borders for the start row
                if cell.row is start_row:
                    cell.font = font
                    if cell.col_idx is 1:
                        cell.border = tbl_border
                    elif cell.col_idx is self.sheet.max_column:
                        cell.border = tbr_border
                    else:
                        cell.border = Border(top=Side(style='medium'),
                                             bottom=Side(style='medium'))
                else:
                    if cell.col_idx is 1:
                        cell.border = Border(left=Side(style='medium'))
                    elif cell.col_idx is self.sheet.max_column:
                        cell.border = Border(right=Side(style='medium'))

                # Adding custom borders for sheets that don't use the 'default' control sheet styling
                if cell.row - 2 is len(cells) or (not (sheet_name == "control sheet") and cell.row is len(cells)):
                    if cell.col_idx is 1:
                        cell.border = Border(bottom=Side(style='medium'),
                                             left=Side(style='medium'))
                    elif cell.col_idx is self.sheet.max_column:
                        cell.border = Border(bottom=Side(style='medium'),
                                             right=Side(style='medium'))
                    else:
                        cell.border = Border(bottom=Side(style='medium'))

    # This function is used to determine how wide a column should be to display it's contents fully.
    def set_column_width(self, sheet_name):
        self.sheet = self.book[sheet_name]

        # Based on the sheet, there will be a different row to calculate column width by
        if sheet_name == 'student reports':
            row = 1
        elif sheet_name == 'control sheet':
            row = 3
        else:
            row = 1

        dims = {}

        # for every column, measure the value of the specified row to determine column width
        for cell in self.sheet.columns:
            width = 0
            try:
                head = self.sheet.cell(row, cell[0].col_idx).value
                head = head.split('|')[0].split('[')[0]

                # Create a tkinter frame to "draw" the text in a specific font to get an estimation for the width
                tkinter.Frame().destroy()
                arial14b = tkfont.Font(family='Arial', size=14, weight='bold')
                width = arial14b.measure(head)
            except AttributeError:
                pass

            # Approximate conversion from arbitrary MS EXCEL units to pixels
            width = (width / 6.4)
            width = width * 1.15 if width < 15 else width * 1.05
            dims[cell[0].column_letter] = max((dims.get(cell[0].column_letter, 0), width))
        for col, value in dims.items():
            self.sheet.column_dimensions[col].width = str(value)

    # This function adds descriptive statistics to the bottom of the control sheet.
    def add_stat_formulas(self):
        self.sheet = self.book['control sheet']
        max_row = self.sheet.max_row

        # Place the labels one cell to the left of where the data will start
        self.sheet.cell(max_row + 2, self.CONTROL_START_COL - 1, "Average")
        self.sheet.cell(max_row + 3, self.CONTROL_START_COL - 1, "Std. Dev.")

        # for every data column, add the descriptivate statistics, only if it is an int or float column.
        for cells in self.sheet.iter_cols(min_col=self.CONTROL_START_COL):
            cell = cells[self.CONTROL_START_ROW - 1]
            cell_type = self.calc_type(cell.value)

            if cell_type in ['INT', 'FLOAT']:
                self.sheet.cell(max_row + 2, cell.col_idx, '=AVERAGE({}:{}{})'
                                .format(cell.coordinate, cell.column_letter, max_row))
                self.sheet.cell(max_row + 3, cell.col_idx, '=STDEV({}:{}{})'
                                .format(cell.coordinate, cell.column_letter, max_row))

    # This function adds the top performer and relative performance data validators to the control sheet.
    def add_control_sheet_validator(self, start, end):
        self.sheet = self.book['control sheet']
        self.sheet.insert_rows(1, amount=2)

        self.sheet.cell(1, self.CONTROL_START_COL - 1, 'Top Performance')
        self.sheet.cell(2, self.CONTROL_START_COL - 1, 'Relative Performance')

        dv = DataValidation(type="list", formula1='"Yes, No"')
        dv.error = 'Your entry is not in the list'
        dv.errorTitle = 'Invalid Entry'
        dv.prompt = 'Should this assignment be factored in?'
        dv.promptTitle = 'Include this assignment'

        # This information is provided in the calling function, it's two coordinates that the data will be validated on.
        cell_range = '{}:{}'.format(start, end)
        dv.add(cell_range)

        # This adds the data validator to the sheet, then sets some default values for each one
        self.sheet.add_data_validation(dv)
        self.set_default_validation()

    # Upon the completion of setting up the control sheet validators, this function will give each numerical column
    # a default value of 'No' and each other column a default value of 'Yes'.
    def set_default_validation(self):
        self.sheet = self.book['control sheet']

        # For every column, using only the first data row, check the type of the column and assign values accordingly.
        for cell in self.sheet.iter_cols(min_row=self.CONTROL_START_ROW, max_row=self.CONTROL_START_ROW,
                                         min_col=self.CONTROL_START_COL):
            cell_type = self.calc_type(cell[0].value)
            col = cell[0].col_idx

            # If column has int or float values, set default validation to 'Yes', otherwise 'No'
            if cell_type in ['INT', 'FLOAT']:
                self.sheet.cell(1, col, value='Yes')
                self.sheet.cell(2, col, value='Yes')
            else:
                self.sheet.cell(1, col, value='No')
                self.sheet.cell(2, col, value='No')

    # This function is used by the calculate_correlations function and is used to pair up each assignment for
    # correlation
    def set_pairings(self, first_col, idx):
        second_col = []
        second_idx = idx + 1

        while second_idx <= self.sheet.max_column:

            if self.calc_type(self.sheet.cell(4, second_idx).value) in ['INT', 'FLOAT']:
                for cell in self.sheet.iter_cols(min_row=self.CONTROL_START_ROW,
                                                 min_col=second_idx, max_col=second_idx):
                    second_col += cell

                for i in range(3):
                    second_col.pop()

                first_item = first_col[0]
                second_item = second_col[0]
                title = self.sheet.cell(3, second_item.col_idx).value

                self.sheet = self.book['correlations']
                self.sheet.cell(1, second_item.col_idx, value=title)
                correlation = '=IFERROR(PEARSON(\'control sheet\'!{}:{},\'control sheet\'!{}:{}), "")' \
                    .format(first_item.coordinate, first_col[-1].coordinate,
                            second_item.coordinate, second_col[-1].coordinate)
                self.sheet.cell(first_item.col_idx, second_item.col_idx, value=correlation)

            second_idx += 1
            second_col.clear()
            self.sheet = self.book['control sheet']

    def save(self):
        self.book.save(self.out_file)
        print('saving')
