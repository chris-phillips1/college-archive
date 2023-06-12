namespace Lab_09_Bell3k
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
			this.cButton = new System.Windows.Forms.Button();
			this.sTB = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// cButton
			// 
			this.cButton.Location = new System.Drawing.Point(61, 67);
			this.cButton.Name = "cButton";
			this.cButton.Size = new System.Drawing.Size(75, 23);
			this.cButton.TabIndex = 0;
			this.cButton.Text = "Crawl";
			this.cButton.UseVisualStyleBackColor = true;
			this.cButton.Click += new System.EventHandler(this.cButton_Click);
			// 
			// sTB
			// 
			this.sTB.Location = new System.Drawing.Point(49, 31);
			this.sTB.Name = "sTB";
			this.sTB.Size = new System.Drawing.Size(100, 20);
			this.sTB.TabIndex = 1;
			this.sTB.Text = "Search";
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(202, 102);
			this.Controls.Add(this.sTB);
			this.Controls.Add(this.cButton);
			this.Name = "Form1";
			this.Text = "Form1";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Button cButton;
		private System.Windows.Forms.TextBox sTB;
	}
}

