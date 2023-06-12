using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace WindowsFormsApp2
{
    public partial class Form1 : Form
    {
        // Where we are putting the text of the file.
        private static String aiw;

        /// <summary>
        /// Initialize the form and read the file.
        /// </summary>
        public Form1()
        {
            InitializeComponent();

            try
            {
                StreamReader cin = new StreamReader("aiw.txt");
                aiw = cin.ReadToEnd();
            }
            catch (Exception e)
            {
                System.Windows.Forms.MessageBox.Show("File not found!");
            }

        }

        /// <summary>
        /// Hold for default stub.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Finds the instances of the string specified and writes to list box.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSubmit_Click(object sender, EventArgs e)
        {
            List<int> indexes = new List<int>();

            // if we have a list, and the last value is not -1 add the next instance to the array
            for (int index = 0; indexes.Count > 0 ? indexes[indexes.Count-1] != -1 : true; index = aiw.IndexOf(txtSearch.Text, index)+txtSearch.Text.Length)
            {
                indexes.Add(aiw.IndexOf(txtSearch.Text, index));
            }

            // drop the last value that is -1
            indexes.RemoveAt(indexes.Count - 1);

            // display the results with context
            foreach (int i in indexes)
            {
                lstFound.Items.Add(aiw.Substring(i < (int)(20 + .5 * txtSearch.Text.Length) ? 0 : i - 20 + (int)(.5 * txtSearch.Text.Length), 40 + txtSearch.Text.Length));
            }
        }
    }
}
