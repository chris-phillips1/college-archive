using System;
using System.Drawing;
using System.Windows.Forms;

namespace lab01_fall_2018_sln
{
    public partial class Form1 : Form
    {
        // global count variable
        public int cnt = 0;

        /// <summary>
        /// Required, but unmodified
        /// </summary>
        public Form1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Required, but unmodified
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {

        }

        /// <summary>
        /// Moves the button and increments the counter
        /// </summary>
        /// <param name="sender">Default, auto param</param>
        /// <param name="e">Default, auto param</param>
        private void button1_Click(object sender, EventArgs e)
        {
            cnt++;
            Random r = new Random();
            button1.Location = new Point(r.Next(0, this.Width-button1.Width*2), r.Next(0, this.Height - button1.Height*2));

            if (cnt == 15)
            {
                MessageBox.Show("Nice work!");
                cnt = 0;
            }
        }
    }
}
