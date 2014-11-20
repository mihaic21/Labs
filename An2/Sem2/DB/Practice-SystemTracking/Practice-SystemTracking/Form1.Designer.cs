namespace Practice_SystemTracking
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
            this.projectsComboBox = new System.Windows.Forms.ComboBox();
            this.tasksDataGridView = new System.Windows.Forms.DataGridView();
            this.saveTasksButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.tasksDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // projectsComboBox
            // 
            this.projectsComboBox.FormattingEnabled = true;
            this.projectsComboBox.Location = new System.Drawing.Point(15, 55);
            this.projectsComboBox.Name = "projectsComboBox";
            this.projectsComboBox.Size = new System.Drawing.Size(121, 21);
            this.projectsComboBox.TabIndex = 0;
            // 
            // tasksDataGridView
            // 
            this.tasksDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tasksDataGridView.Location = new System.Drawing.Point(142, 55);
            this.tasksDataGridView.Name = "tasksDataGridView";
            this.tasksDataGridView.Size = new System.Drawing.Size(684, 349);
            this.tasksDataGridView.TabIndex = 1;
            // 
            // saveTasksButton
            // 
            this.saveTasksButton.Location = new System.Drawing.Point(416, 410);
            this.saveTasksButton.Name = "saveTasksButton";
            this.saveTasksButton.Size = new System.Drawing.Size(75, 23);
            this.saveTasksButton.TabIndex = 2;
            this.saveTasksButton.Text = "Save Tasks";
            this.saveTasksButton.UseVisualStyleBackColor = true;
            this.saveTasksButton.Click += new System.EventHandler(this.saveTasksButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 24);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(45, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Projects";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(413, 24);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(36, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Tasks";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(838, 480);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.saveTasksButton);
            this.Controls.Add(this.tasksDataGridView);
            this.Controls.Add(this.projectsComboBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.tasksDataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox projectsComboBox;
        private System.Windows.Forms.DataGridView tasksDataGridView;
        private System.Windows.Forms.Button saveTasksButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}

