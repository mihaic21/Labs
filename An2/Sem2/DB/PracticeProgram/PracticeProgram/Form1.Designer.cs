namespace PracticeProgram
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
            this.categoriesComboBox = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.moviesDataGridView = new System.Windows.Forms.DataGridView();
            this.saveMoviesButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.moviesDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // categoriesComboBox
            // 
            this.categoriesComboBox.FormattingEnabled = true;
            this.categoriesComboBox.Location = new System.Drawing.Point(91, 52);
            this.categoriesComboBox.Name = "categoriesComboBox";
            this.categoriesComboBox.Size = new System.Drawing.Size(121, 21);
            this.categoriesComboBox.TabIndex = 0;
            this.categoriesComboBox.SelectedIndexChanged += new System.EventHandler(this.categoriesComboBox_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(91, 33);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(89, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Movie Categories";
            // 
            // moviesDataGridView
            // 
            this.moviesDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.moviesDataGridView.Location = new System.Drawing.Point(319, 12);
            this.moviesDataGridView.Name = "moviesDataGridView";
            this.moviesDataGridView.Size = new System.Drawing.Size(303, 279);
            this.moviesDataGridView.TabIndex = 2;
            // 
            // saveMoviesButton
            // 
            this.saveMoviesButton.Location = new System.Drawing.Point(489, 315);
            this.saveMoviesButton.Name = "saveMoviesButton";
            this.saveMoviesButton.Size = new System.Drawing.Size(124, 23);
            this.saveMoviesButton.TabIndex = 3;
            this.saveMoviesButton.Text = "Save Movies";
            this.saveMoviesButton.UseVisualStyleBackColor = true;
            this.saveMoviesButton.Click += new System.EventHandler(this.saveMoviesButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(672, 389);
            this.Controls.Add(this.saveMoviesButton);
            this.Controls.Add(this.moviesDataGridView);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.categoriesComboBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.moviesDataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox categoriesComboBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView moviesDataGridView;
        private System.Windows.Forms.Button saveMoviesButton;
    }
}

