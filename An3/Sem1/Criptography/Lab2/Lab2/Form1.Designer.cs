namespace Lab2
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
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series2 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series3 = new System.Windows.Forms.DataVisualization.Charting.Series();
            this.algorithmGroupBox = new System.Windows.Forms.GroupBox();
            this.inefficientRadioButton = new System.Windows.Forms.RadioButton();
            this.euclidRecursiveRadioButton = new System.Windows.Forms.RadioButton();
            this.euclidRadioButton = new System.Windows.Forms.RadioButton();
            this.label1 = new System.Windows.Forms.Label();
            this.milisecondsLabel = new System.Windows.Forms.Label();
            this.goButton = new System.Windows.Forms.Button();
            this.timeAnalysisChart = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.algorithmGroupBox.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.timeAnalysisChart)).BeginInit();
            this.SuspendLayout();
            // 
            // algorithmGroupBox
            // 
            this.algorithmGroupBox.Controls.Add(this.inefficientRadioButton);
            this.algorithmGroupBox.Controls.Add(this.euclidRecursiveRadioButton);
            this.algorithmGroupBox.Controls.Add(this.euclidRadioButton);
            this.algorithmGroupBox.Location = new System.Drawing.Point(13, 13);
            this.algorithmGroupBox.Name = "algorithmGroupBox";
            this.algorithmGroupBox.Size = new System.Drawing.Size(200, 100);
            this.algorithmGroupBox.TabIndex = 0;
            this.algorithmGroupBox.TabStop = false;
            this.algorithmGroupBox.Text = "Select Algorithm";
            // 
            // inefficientRadioButton
            // 
            this.inefficientRadioButton.AutoSize = true;
            this.inefficientRadioButton.Location = new System.Drawing.Point(7, 66);
            this.inefficientRadioButton.Name = "inefficientRadioButton";
            this.inefficientRadioButton.Size = new System.Drawing.Size(71, 17);
            this.inefficientRadioButton.TabIndex = 2;
            this.inefficientRadioButton.TabStop = true;
            this.inefficientRadioButton.Text = "Inefficient";
            this.inefficientRadioButton.UseVisualStyleBackColor = true;
            // 
            // euclidRecursiveRadioButton
            // 
            this.euclidRecursiveRadioButton.AutoSize = true;
            this.euclidRecursiveRadioButton.Location = new System.Drawing.Point(7, 43);
            this.euclidRecursiveRadioButton.Name = "euclidRecursiveRadioButton";
            this.euclidRecursiveRadioButton.Size = new System.Drawing.Size(105, 17);
            this.euclidRecursiveRadioButton.TabIndex = 1;
            this.euclidRecursiveRadioButton.TabStop = true;
            this.euclidRecursiveRadioButton.Text = "Euclid Recursive";
            this.euclidRecursiveRadioButton.UseVisualStyleBackColor = true;
            // 
            // euclidRadioButton
            // 
            this.euclidRadioButton.AutoSize = true;
            this.euclidRadioButton.Location = new System.Drawing.Point(7, 20);
            this.euclidRadioButton.Name = "euclidRadioButton";
            this.euclidRadioButton.Size = new System.Drawing.Size(54, 17);
            this.euclidRadioButton.TabIndex = 0;
            this.euclidRadioButton.TabStop = true;
            this.euclidRadioButton.Text = "Euclid";
            this.euclidRadioButton.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(385, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(101, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Time in miliseconds:";
            // 
            // milisecondsLabel
            // 
            this.milisecondsLabel.AutoSize = true;
            this.milisecondsLabel.Location = new System.Drawing.Point(492, 13);
            this.milisecondsLabel.Name = "milisecondsLabel";
            this.milisecondsLabel.Size = new System.Drawing.Size(0, 13);
            this.milisecondsLabel.TabIndex = 2;
            // 
            // goButton
            // 
            this.goButton.Location = new System.Drawing.Point(219, 13);
            this.goButton.Name = "goButton";
            this.goButton.Size = new System.Drawing.Size(107, 100);
            this.goButton.TabIndex = 3;
            this.goButton.Text = "GO!";
            this.goButton.UseVisualStyleBackColor = true;
            this.goButton.Click += new System.EventHandler(this.goButton_Click);
            // 
            // timeAnalysisChart
            // 
            chartArea1.Name = "ChartArea1";
            this.timeAnalysisChart.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.timeAnalysisChart.Legends.Add(legend1);
            this.timeAnalysisChart.Location = new System.Drawing.Point(13, 143);
            this.timeAnalysisChart.Name = "timeAnalysisChart";
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "Euclid";
            series2.ChartArea = "ChartArea1";
            series2.Legend = "Legend1";
            series2.Name = "Euclid Recursive";
            series3.ChartArea = "ChartArea1";
            series3.Legend = "Legend1";
            series3.Name = "Inefficient";
            this.timeAnalysisChart.Series.Add(series1);
            this.timeAnalysisChart.Series.Add(series2);
            this.timeAnalysisChart.Series.Add(series3);
            this.timeAnalysisChart.Size = new System.Drawing.Size(563, 403);
            this.timeAnalysisChart.TabIndex = 8;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(588, 558);
            this.Controls.Add(this.timeAnalysisChart);
            this.Controls.Add(this.goButton);
            this.Controls.Add(this.milisecondsLabel);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.algorithmGroupBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.algorithmGroupBox.ResumeLayout(false);
            this.algorithmGroupBox.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.timeAnalysisChart)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox algorithmGroupBox;
        private System.Windows.Forms.RadioButton inefficientRadioButton;
        private System.Windows.Forms.RadioButton euclidRecursiveRadioButton;
        private System.Windows.Forms.RadioButton euclidRadioButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label milisecondsLabel;
        private System.Windows.Forms.Button goButton;
        private System.Windows.Forms.DataVisualization.Charting.Chart timeAnalysisChart;
    }
}

