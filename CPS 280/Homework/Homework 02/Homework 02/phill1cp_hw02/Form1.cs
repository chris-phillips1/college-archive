// Name: Chris Phillips
// Course: CPS 280
// Date: 10/16/18
// Assignment: Homework 02
using System;
using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace phill1cp_hw02
{
    public partial class Form1 : Form
    {
        public string searchType;   // This variable will store which checkbox the user has most recently clicked on.

        // This a list of arrays. Each array is the representation of a single line in the csv. 
        // Each element in a given array corresponds to one piece of data on that line.
        public List<string[]> master = new List<string[]>();  


        /// <summary>
        /// This method is where all the file-related things are done. The csv is parsed and stored in a list.
        /// </summary>
        public Form1()
        {
            InitializeComponent();

            searchType = "";
            label1.Text = searchType;

            string[] currentLine;
            var reader = new StreamReader("medals_expanded.csv");

            while (!reader.EndOfStream)
            {
                currentLine = new string[12]; // An array that will contain all the data from the current line.
                var line = reader.ReadLine();  // Reading in the current line.

                // This regex will ensure that data fields aren't split incorrectly, like when there's an additional comma.
                Regex CSVParser = new Regex(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))"); 
                var values = CSVParser.Split(line);

                // Add each data piece to the array representing this line
                for (int i = 0; i < 12; i++)
                {
                    currentLine[i] = values[i];
                }

                // Add the array representing the current line to the "master" list.
                master.Add(currentLine);
            }

            // Iterate through all the data and make sure that quotes and commas are removed. After this, the string is trimmed of any whitespace.
            for (int i = 0; i < master.Count; i++)
            {
                for (int j = 0; j < 12; j++)
                {
                    master[i][j] = master[i][j].Replace("\"", "");
                    master[i][j] = master[i][j].Replace(",", "");
                    master[i][j] = master[i][j].Trim();
                }
            }
        }


        /// <summary>
        /// This method will set the current 'searchType' to be 'Country' when the country checkbox is selected. The ComboBox will also be updated with the corresponding data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            searchType = "Country";
            label1.Text = searchType;
            UpdateComboCountry();
        }

        /// <summary>
        /// This method will set the current 'searchType' to be 'Event' when the event checkbox is selected. The ComboBox will also be updated with the corresponding data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void checkBox2_CheckedChanged(object sender, EventArgs e)
        {
            searchType = "Event";
            label1.Text = searchType;
            UpdateComboEvent();
        }

        /// <summary>
        /// This method will set the current 'searchType' to be 'Year' when the year checkbox is selected. The ComboBox will also be updated with the corresponding data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void checkBox3_CheckedChanged(object sender, EventArgs e)
        {
            searchType = "Year";
            label1.Text = searchType;
            UpdateComboYear();
        }


        /// <summary>
        /// This method will update the ComboBox with the relevant country information.
        /// </summary>
        private void UpdateComboCountry()
        {
            comboBox1.Items.Clear();
            List<string> currentlyUsed = new List<string>(); // This will be a list of all the countries that are seen in the CSV, with no overlap
            string currentCountry;

            //start i at 1 to stop from showing "NOC"
            for (int i = 1; i < master.Count; i++)
            {
                currentCountry = master[i][5]; // i represents the current line of the data, 5 represents the 6th column, the country
                if (!currentlyUsed.Contains(currentCountry))
                {
                    currentlyUsed.Add(currentCountry);
                }
            }

            // Add all the found countries to the ComboBox
            foreach (string country in currentlyUsed)
            {
                comboBox1.Items.Add(country);
            }
        }

        /// <summary>
        /// This method will update the ComboBox with the relevant event information.
        /// </summary>
        private void UpdateComboEvent()
        {
            comboBox1.Items.Clear();
            List<string> currentlyUsed = new List<string>(); // This will be a list of all the events that are seen in the CSV, with no overlap
            string currentEvent;

            //start i at 1 to stop from showing "Event"
            for (int i = 1; i < master.Count; i++)
            {
                currentEvent = master[i][7]; // i represents the current line of the data, 7 represents the 8th column, the event
                if (!currentlyUsed.Contains(currentEvent))
                {
                    currentlyUsed.Add(currentEvent);
                }
            }

            // Add all the found events to the ComboBox
            foreach (string Event in currentlyUsed)
            {
                comboBox1.Items.Add(Event);
            }
        }

        /// <summary>
        /// This method will update the ComboBox with the relevant year information.
        /// </summary>
        private void UpdateComboYear()
        {
            comboBox1.Items.Clear();
            List<string> currentlyUsed = new List<string>(); // This will be a list of all the years that are seen in the CSV, with no overlap
            string currentYear;

            //start i at 1 to stop from showing "Edition"
            for (int i = 1; i < master.Count; i++)
            {
                currentYear = master[i][1]; // i represents the current line of the data, 1 represents the 2nd column, the edition (or year)
                if (!currentlyUsed.Contains(currentYear))
                {
                    currentlyUsed.Add(currentYear);
                }
            }

            // Add all the found years to the ComboBox
            foreach (string year in currentlyUsed)
            {
                comboBox1.Items.Add(year);
            }
        }

        /// <summary>
        /// The Combine method will take an array of strings and return a single string of every element concatenated together.
        /// </summary>
        /// <param name="arr"> An array of strings to concatenate. </param>
        /// <returns> A string that will be a concatenation of all elements of arr. </returns>
        private string Combine(string[] arr)
        {
            string fullVal = "";

            for (int i = 0; i < arr.Length; i++)
            {
                fullVal += arr[i] + " ";
            }

            return fullVal;
        }

        /// <summary>
        /// When the sumbit button is clicked, the listBox will be updated with relevant information based on which category was selected.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
            listBox1.Items.Add(Combine(master[0])); // Show the headers in the listbox.
            string searchTerm = comboBox1.GetItemText(this.comboBox1.SelectedItem); // Get the selected option's text

            // If the user wants to search countries, search every line to see if it's country matches the user's country selection
            if (searchType.Equals("Country"))
            {
                for (int i = 1; i < master.Count; i++)
                {
                    string currentCountry = master[i][5];
                    if (currentCountry.Equals(searchTerm))
                    {
                        string line = Combine(master[i]);
                        listBox1.Items.Add(line);
                    }
                }
            }

            // If the user wants to search events, search every line to see if it's event matches the user's event selection
            if (searchType.Equals("Event"))
            {
                for (int i = 1; i < master.Count; i++)
                {
                    string currentEvent = master[i][7];
                    if (currentEvent.Equals(searchTerm))
                    {
                        string line = Combine(master[i]);
                        listBox1.Items.Add(line);
                    }
                }
            }

            // If the user wants to search years, search every line to see if it's year matches the user's year selection
            if (searchType.Equals("Year"))
            {
                for (int i = 1; i < master.Count; i++)
                {
                    string currentYear = master[i][1];
                    if (currentYear.Equals(searchTerm))
                    {
                        string line = Combine(master[i]);
                        listBox1.Items.Add(line);
                    }
                }
            }
        }

    }
}
