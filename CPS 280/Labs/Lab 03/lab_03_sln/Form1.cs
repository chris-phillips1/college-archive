using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace lab_03_sln
{
    public partial class Form1 : Form
    {
        // Our list of brand names.
        private List<String> brands = new List<string> ();

        public Form1()
        {
            InitializeComponent();

            try
            {
                String tmp;
                StreamReader cin = new StreamReader("nestle_products.txt");

                while ((tmp = cin.ReadLine()) != null)
                    brands.Add(tmp);

                cin.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Exception: " + e.Message);
            }
            button1.Text = "Submit";

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Loop through and see what we have.
        /// Looking for a case insensitive match to a partial string.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            foreach (String s in brands)
            {
                if (s.ToLower().Contains(textBox1.Text.ToLower())) {
                    listBox1.Items.Add(s);
                }
            }
        }
    }
}
