using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace lab_02
{
    public partial class Form1 : Form
    {

        private int range = 10, count = 5;
        private Random r = new Random();

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            List<int> xAxis = new List<int>(Enumerable.Range(0, range).ToArray()); // fancy way to create and initialize array as the parameter of the list instantiation
            List<int> yAxis = new List<int>(new int[range]);

            for (int i = 0; i < count; i++)
            {
                int tmp = r.Next() % range;
                yAxis[tmp]++;
            }

            chart1.Series["vals"].Points.DataBindXY(xAxis, yAxis);
            chart1.Series["vals"].ChartType = SeriesChartType.Column;
        }
    }
}
