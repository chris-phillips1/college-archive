namespace Homework_02_fall_18
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.btnSubmit = new System.Windows.Forms.Button();
            this.chkCountry = new System.Windows.Forms.CheckBox();
            this.chkEvent = new System.Windows.Forms.CheckBox();
            this.checkYear = new System.Windows.Forms.CheckBox();
            this.comboSearch = new System.Windows.Forms.ComboBox();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.SuspendLayout();
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            // 
            // btnSubmit
            // 
            this.btnSubmit.Location = new System.Drawing.Point(60, 65);
            this.btnSubmit.Name = "btnSubmit";
            this.btnSubmit.Size = new System.Drawing.Size(75, 23);
            this.btnSubmit.TabIndex = 0;
            this.btnSubmit.Text = "Submit";
            this.btnSubmit.UseVisualStyleBackColor = true;
            this.btnSubmit.Click += new System.EventHandler(this.btnSubmit_Click);
            // 
            // chkCountry
            // 
            this.chkCountry.AutoSize = true;
            this.chkCountry.Location = new System.Drawing.Point(12, 15);
            this.chkCountry.Name = "chkCountry";
            this.chkCountry.Size = new System.Drawing.Size(62, 17);
            this.chkCountry.TabIndex = 2;
            this.chkCountry.Text = "Country";
            this.chkCountry.UseVisualStyleBackColor = true;
            this.chkCountry.CheckedChanged += new System.EventHandler(this.chkCountry_CheckedChanged);
            // 
            // chkEvent
            // 
            this.chkEvent.AutoSize = true;
            this.chkEvent.Location = new System.Drawing.Point(81, 15);
            this.chkEvent.Name = "chkEvent";
            this.chkEvent.Size = new System.Drawing.Size(54, 17);
            this.chkEvent.TabIndex = 3;
            this.chkEvent.Text = "Event";
            this.chkEvent.UseVisualStyleBackColor = true;
            this.chkEvent.CheckedChanged += new System.EventHandler(this.chkEvent_CheckedChanged);
            // 
            // checkYear
            // 
            this.checkYear.AutoSize = true;
            this.checkYear.Location = new System.Drawing.Point(142, 15);
            this.checkYear.Name = "checkYear";
            this.checkYear.Size = new System.Drawing.Size(48, 17);
            this.checkYear.TabIndex = 4;
            this.checkYear.Text = "Year";
            this.checkYear.UseVisualStyleBackColor = true;
            this.checkYear.CheckedChanged += new System.EventHandler(this.checkYear_CheckedChanged);
            // 
            // comboSearch
            // 
            this.comboSearch.FormattingEnabled = true;
            this.comboSearch.Location = new System.Drawing.Point(12, 38);
            this.comboSearch.Name = "comboSearch";
            this.comboSearch.Size = new System.Drawing.Size(178, 21);
            this.comboSearch.TabIndex = 5;
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.Location = new System.Drawing.Point(244, 1);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(556, 446);
            this.listBox1.TabIndex = 6;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.comboSearch);
            this.Controls.Add(this.checkYear);
            this.Controls.Add(this.chkEvent);
            this.Controls.Add(this.chkCountry);
            this.Controls.Add(this.btnSubmit);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.Button btnSubmit;
        private System.Windows.Forms.CheckBox chkCountry;
        private System.Windows.Forms.CheckBox chkEvent;
        private System.Windows.Forms.CheckBox checkYear;
        private System.Windows.Forms.ComboBox comboSearch;
        private System.Windows.Forms.ListBox listBox1;
    }
}

