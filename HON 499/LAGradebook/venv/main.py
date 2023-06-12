import csv
from openpyxl import Workbook
from classes import Gradebook


class LAGradebook:
    gradebook = Gradebook(in_file='gc_supp_UTF16.xls', out_file='gc_supp_UTF16.xlsx')
    gradebook.create_sheets()
    gradebook.fix_column_widths()
    gradebook.save()
