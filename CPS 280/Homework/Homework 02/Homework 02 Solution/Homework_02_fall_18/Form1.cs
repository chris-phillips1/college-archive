using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Homework_02_fall_18
{
    public partial class Form1 : Form
    {
        List<String []> rows = new List<string[]>();
        String[] header;
        /// <summary>
        /// Read and split up in the input file.
        /// </summary>
        public Form1()
        {
            using (StreamReader reader = new StreamReader("medals_expanded.csv"))
            {
                header = reader.ReadLine().Split(',');
                while (!reader.EndOfStream)
                {
                    String line = reader.ReadLine().Replace(@"""","").TrimEnd('-').TrimStart('-').Trim();
                    rows.Add( line.Split(',') );
                }
            }
            InitializeComponent();
        }

        /// <summary>
        /// Finds and appends the searched for text to the output.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSubmit_Click(object sender, EventArgs e)
        {
            listBox1.Items.Add(String.Format("{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}", header[0], header[1], header[2], header[3], header[4], header[5], header[6], header[7], header[8], header[9]));
            if (chkCountry.Checked)
            {
                listBox1.Items.Clear();

                foreach (String[] sa in rows)
                {
                    if (sa[6].Equals(comboSearch.SelectedItem.ToString()))
                        listBox1.Items.Add(String.Format("{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}",sa[0], sa[1], sa[2], sa[3], sa[4], sa[5], sa[6], sa[7], sa[8], sa[9]));
                }
            } else if (chkEvent.Checked)
            {
                listBox1.Items.Clear();
                foreach (String[] sa in rows)
                {
                    if (sa[8].Equals(comboSearch.SelectedItem.ToString()))
                        listBox1.Items.Add(String.Format("{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}", sa[0], sa[1], sa[2], sa[3], sa[4], sa[5], sa[6], sa[7], sa[8], sa[9]));
                }
            } else
            {
                listBox1.Items.Clear();
                foreach (String[] sa in rows)
                {
                    if (sa[1].Equals(comboSearch.SelectedItem.ToString()))
                        listBox1.Items.Add(String.Format("{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}", sa[0], sa[1], sa[2], sa[3], sa[4], sa[5], sa[6], sa[7], sa[8], sa[9]));
                }
            }
        }

        /// <summary>
        /// When the country check box is checked load the appropriate data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void chkCountry_CheckedChanged(object sender, EventArgs e)
        {
            chkEvent.Checked = false;
            checkYear.Checked = false;
            comboSearch.Items.Clear();
            String[] tmp = new String[rows.Count];
            int i = 0;
            foreach (String [] sa in rows)
            {
                if (!tmp.Contains(sa[6]))
                    tmp[i++] = sa[6];
            }

            Array.Sort(tmp);

            foreach (String s in tmp)
            {
                if (s != null && s.Length > 0)
                    comboSearch.Items.Add(s);
            }
        }

        /// <summary>
        /// When the event check box is checked load the appropriate data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void chkEvent_CheckedChanged(object sender, EventArgs e)
        {
            checkYear.Checked = false;
            chkCountry.Checked = false;

            String[] tmp = new String[rows.Count];
            int i = 0;
            foreach (String[] sa in rows)
            {
                if (!tmp.Contains(sa[8]))
                    tmp[i++] = sa[8];
            }

            Array.Sort(tmp);

            foreach (String s in tmp)
            {
                if (s != null && s.Length > 0)
                    comboSearch.Items.Add(s);
            }
        }

        /// <summary>
        /// When the year check box is checked load the appropriate data.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void checkYear_CheckedChanged(object sender, EventArgs e)
        {
            chkCountry.Checked = false;
            chkEvent.Checked = false;

            String[] tmp = new String[rows.Count];
            int i = 0;
            foreach (String[] sa in rows)
            {
                if (!tmp.Contains(sa[1]))
                    tmp[i++] = sa[1];
            }

            Array.Sort(tmp);

            foreach (String s in tmp)
            {
                if (s != null && s.Length > 0)
                    comboSearch.Items.Add(s);
            }
        }
    }
}
