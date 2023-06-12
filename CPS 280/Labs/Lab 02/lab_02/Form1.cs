using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace lab_02
{
    public partial class Form1 : Form
    {
        private static int range = 10, count = 5;
        private static List<int> xAxis = xAxis = new List<int>(Enumerable.Range(0, range).ToArray()); 
        private static List<int> yAxis = yAxis = new List<int>(new int[range]);

        private Random r = new Random();

        public Form1()
        {
            InitializeComponent();
            // setting up some properties here instead of with the properties editor tool
            button1.Text = "Submit";
            txtRange.Text = range.ToString();
            txtCount.Text = count.ToString();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            // check if the values are both valid integers
            if (int.TryParse(txtRange.Text, out int tmpRange) && int.TryParse(txtCount.Text, out int tmpCount))
            {
                // check if the values are different
                if (tmpCount != count || tmpRange != range)
                {
                    range = tmpRange;
                    count = tmpCount;
                    xAxis = new List<int>(Enumerable.Range(0, range).ToArray()); // fancy way to create and initialize array as the parameter of the list instantiation
                    yAxis = new List<int>(new int[range]);
                }
            } 

            // generate and add the random values
            for (int i = 0; i < count; i++)
            {
                int tmp = r.Next() % range;
                yAxis[tmp]++;
            }

            // bind our data to the chart
            // notice that the chart has an object as the property of the chart object itself
            // char -> series -> points (three objects actually)
            chart1.Series["vals"].Points.DataBindXY(xAxis, yAxis);
            chart1.Series["vals"].ChartType = SeriesChartType.Column;
        }
    }
}
