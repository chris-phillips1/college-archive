// Name: Chris Phillips
// Course: CPS 280
// Date: 11/27/18
// Assignment: Homework 3
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace phill1cp_hw03
{
    public partial class Form1 : Form
    {
        bool stringSelected = true; // Allows the user to change between the string array and the integer array

        // Arbitrarily defining the integer array
        static int[] intArr = { 1, 2, 3, 4, 5, 3 };
        MyArray myIntArr = new MyArray(intArr);

        // Arbitrarily defining the string array
        static string[] strArr = { "Hello", "World", "Speaker", "Phone", "Water Bottle", "Hello" };
        MyArray myStrArr = new MyArray(strArr);

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // Determine whether to initially display the string or the integer array
            if (stringSelected)
            {
                foreach (string s in myStrArr.myStr)
                {
                    listBox1.Items.Add(s);
                }
            }
            else
            {
                foreach (int i in myIntArr.myInt)
                {
                    listBox1.Items.Add(i);
                }
            }
        }

        // Replace Button
        private void button1_Click(object sender, EventArgs e)
        {
            // Clear these fields from previous button presses
            textBox2.Clear();
            textBox4.Clear();

            if(stringSelected)
            {
                String toReplace = textBox1.Text;
                String replacement = textBox3.Text;
                myStrArr.Replace(toReplace, replacement);
                UpdateDisplay();
            }
            else
            {
                int toReplace = Int32.Parse(textBox1.Text);
                int replacement = Int32.Parse(textBox3.Text);
                myIntArr.Replace(toReplace, replacement);
                UpdateDisplay();
            }

            // Clear these fields after the replacement has happened
            textBox1.Clear();
            textBox3.Clear();
        }

        // Find Button
        private void button2_Click(object sender, EventArgs e)
        {
            int index; //The index of the first instance that the search term was found at.

            if (stringSelected)
            {
                String toFind = textBox2.Text;
                index = myStrArr.Search(toFind);
            }
            else
            {
                int toFind = Int32.Parse(textBox2.Text);
                index = myIntArr.Search(toFind);
            }

            //Based on whether the item was found or not, display index accordingly.
            textBox4.Text = (index == -1 ? "Not Found" : index.ToString()); 
        }

        // Sort Button
        private void button3_Click(object sender, EventArgs e)
        {
            // Clear these fields from previous button presses
            textBox2.Clear();
            textBox4.Clear();

            // Sort the array based on whether its a string or an integer
            if(stringSelected)
            {
                myStrArr.SortString();
                UpdateDisplay();
            }
            else
            {
                myIntArr.SortInt();
                UpdateDisplay();
            }
        }

        // Reverse Button
        private void button4_Click(object sender, EventArgs e)
        {
            // Clear these fields from previous button presses
            textBox2.Clear();
            textBox4.Clear();

            // Decide whether to reverse the string array or the integer array
            if (stringSelected)
            {
                myStrArr.ReverseString();
                UpdateDisplay();
            }
            else
            {
                myIntArr.ReverseInt();
                UpdateDisplay();
            }
        }

        // Change the display from string -> int or int -> string
        private void button5_Click(object sender, EventArgs e)
        {
            // Clear these fields from previous button presses
            textBox2.Clear();
            textBox4.Clear();

            // Flip the stringSelected boolean and update the left-side display
            stringSelected = !stringSelected; 
            UpdateInitDisplay();
        }

        // Updates the right-side listBox with changes to be displayed
        private void UpdateDisplay()
        {
            listBox2.Items.Clear();

            if (stringSelected)
            {
                foreach (string s in myStrArr.myStr)
                {
                    listBox2.Items.Add(s);
                }
            }
            else
            {
                foreach (int i in myIntArr.myInt)
                {
                    listBox2.Items.Add(i);
                }
            }
        }

        // Updates the left-side listBox with the current data type
        public void UpdateInitDisplay()
        {
            listBox1.Items.Clear();
            listBox2.Items.Clear();

            if (stringSelected)
            {
                foreach (string s in myStrArr.myStr)
                {
                    listBox1.Items.Add(s);
                }
            }
            else
            {
                foreach (int i in myIntArr.myInt)
                {
                    listBox1.Items.Add(i);
                }
            }
        }

    }

    /// <summary>
    /// The MyArray class is a special class that takes either a string or integer array
    /// and provides additional functionality to these arrays such as reverse, sort, replace, and search.
    /// </summary>
    public class MyArray
    {
        public int[] myInt;
        public string[] myStr;

        public MyArray(String[] s)
        {
            myStr = s;
        }

        public MyArray(int[] i)
        {
            myInt = i;
        }

        /// <summary>
        /// This method will reverse all of the elements in an integer array.
        /// </summary>
        public void ReverseInt()
        {
            int current = 0;
            int[] tmpArr = new int[myInt.Length]; //Temporary array to store the reversed configuration of integers

            for (int i = myInt.Length - 1; i >= 0; i--)
            {
                tmpArr[current] = myInt[i];
                current++;
            }

            myInt = tmpArr;
        }

        /// <summary>
        /// This method will reverse all of the elements in a string array.
        /// </summary>
        public void ReverseString()
        {
            int current = 0;
            string[] tmpArr = new string[myStr.Length]; //Temporary array to store the reversed configuration of strings

            for (int i = myStr.Length - 1; i >= 0; i--)
            {
                tmpArr[current] = myStr[i];
                current++;
            }

            myStr = tmpArr;
        }

        /// <summary>
        /// This method allows the user to replace the value of an existing element in an
        /// integer array with a new integer.
        /// </summary>
        /// <param name="toReplace">The integer that will be replaced, if found</param>
        /// <param name="replacement">The integer that will be doing the replacing</param>
        public void Replace(int toReplace, int replacement)
        {
            for(int i = 0; i < myInt.Length; i++)
            {
                if(myInt[i] == toReplace)
                {
                    myInt[i] = replacement;
                    break;
                }
            }
        }

        /// <summary>
        /// This method allows the user to replace the value of an existing element in a
        /// string array with a new string.
        /// </summary>
        /// <param name="toReplace">The string that will be replaced, if found</param>
        /// <param name="replacement">The string that will be doing the replacing</param>
        public void Replace(String toReplace, String replacement)
        {
            for(int i = 0; i < myStr.Length; i++)
            {
                if(myStr[i].Equals(toReplace))
                {
                    myStr[i] = replacement;
                    break;
                }
            }

        }

        /// <summary>
        /// This method will search through the integer array and return the index of the
        /// integer that is being searched for, or return -1 if not found.
        /// </summary>
        /// <param name="term">The integer to be searched for in the array.</param>
        /// <returns></returns>
        public int Search(int term)
        {
            int current = -1;

            for(int i = 0; i < myInt.Length; i++)
            {
                if(myInt[i] == term)
                {
                    current = i;
                    break;
                }
            }

            return current;
        }

        /// <summary>
        /// This method will search through the string array and return the index of the
        /// string that is being searched for, or return -1 if not found.
        /// </summary>
        /// <param name="term">The string to be searched for in the array.</param>
        /// <returns></returns>
        public int Search(String term)
        {
            int current = -1;

            for (int i = 0; i < myStr.Length; i++)
            {
                if (myStr[i].Equals(term))
                {
                    current = i;
                    break;
                }
            }

            return current;
        }

        /// <summary>
        /// This method uses a selection sort to sort an integer array, based on numerical value
        /// </summary>
        public void SortInt()
        {
            int n = myInt.Length;

            for (int i = 0; i < n - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < n; j++)
                {
                    if (myInt[j] < myInt[min])
                        min = j;
                }

                int temp = myInt[min];
                myInt[min] = myInt[i];
                myInt[i] = temp;
            }
        }

        /// <summary>
        /// This method uses a selection sort to sort a string array, based on lexigraph
        /// </summary>
        public void SortString()
        {
            int n = myStr.Length;

            for (int i = 0; i < n - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < n; j++)
                {
                    if (myStr[j].CompareTo(myStr[min]) < 0)
                        min = j;
                }

                String temp = myStr[min];
                myStr[min] = myStr[i];
                myStr[i] = temp;
            }
        }
        
    }
}
