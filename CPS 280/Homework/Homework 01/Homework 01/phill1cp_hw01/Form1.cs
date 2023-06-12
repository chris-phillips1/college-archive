// Name: Chris Phillips
// Course: CPS 280A
// Date: 9/30/18
// Assignment: Homework O1
using System;
using System.Collections;
using System.IO;
using System.Windows.Forms;

namespace phill1cp_hw01
{
    public partial class Form1 : Form
    {
        ArrayList lines = new ArrayList();
        int snippetSize, resultCount, currentCount = 0;

        public Form1()
        {
            InitializeComponent();

            listBox1.Hide();
            string currentLine;
            StreamReader inFile = new StreamReader("aiw.txt");

            // Adds each line in the file to an ArrayList called lines
            while ((currentLine = inFile.ReadLine()) != null)
            {
                lines.Add(currentLine);
            }

        }

        /// <summary>
        /// The button1_Click method will determine what to display to the listBox, 
        /// based on what the user inputs to the "search", "result count", and "snippet size" fields.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            listBox1.Show();
            listBox1.Items.Clear();

            currentCount = 0;
            resultCount = int.Parse(textBox2.Text);
            snippetSize = int.Parse(textBox3.Text);


            // For every line in the file, do the following
            for (int i = 0; i < lines.Count; i++)
            {
                // Get relevant information
                string userSearch = textBox1.Text;
                string currentLine = lines[i].ToString();
                string fixedLine;

                // If the current line contains the user search, do the following
                if ((currentLine.ToLower().Contains(userSearch.ToLower()) && !listBox1.Items.Contains(currentLine)) && (currentCount < resultCount))
                {
                    // Based on the snippet size, determine the starting index of the string to be printed out
                    int startIdx = currentLine.ToLower().IndexOf(userSearch.ToLower()) - (snippetSize / 2);

                    // If the snippet size isn't too big, the startIdx will be greater than 0
                    if (startIdx > 0)
                    {
                        // Start at the starting index, with a length of the the snippet size plus the length of the user's search term
                        fixedLine = currentLine.Substring(startIdx, userSearch.Length + snippetSize);
                    }
                    // If the snippet size is large, but not bigger than the current line, the starting index will be where the term is first found
                    else if(snippetSize + userSearch.Length < currentLine.Length)
                    {
                        startIdx = currentLine.ToLower().IndexOf(userSearch.ToLower());
                        fixedLine = currentLine.Substring(startIdx, userSearch.Length + snippetSize);
                    }
                    // If the snippet size is large and bigger than the current line, print the whole line
                    else
                    {
                        fixedLine = currentLine;
                    }

                    currentCount++;
                    listBox1.Items.Add(fixedLine);
                }

               // snippetSize = int.Parse(textBox3.Text);
            }
        }
    }
}
