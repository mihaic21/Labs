using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Numerics;
using System.Threading;

namespace Lab2
{
    public partial class Form1 : Form
    {
        private Dictionary<BigInteger, BigInteger> numbers = new Dictionary<BigInteger, BigInteger>();

        public Form1()
        {
            InitializeComponent();
            this.initializeNumbers();
        }

        private void initializeNumbers()
        {
            this.numbers.Add(1234567890123563, 9876543212345678);
            this.numbers.Add(88, 43);
            this.numbers.Add(29, 32);
            this.numbers.Add(123523, 534234);
            this.numbers.Add(12341234, 13454123);
            this.numbers.Add(12354231234567, 1232541234567);
            this.numbers.Add(12312541234567, 123112345673);
            this.numbers.Add(1234567898765432, 987654321234567);
            this.numbers.Add(12354231234567412, 1232541234567412);
            this.numbers.Add(1231254123456741, 1231123456739);
        }

        private void goButton_Click(object sender, EventArgs e)
        {
            if (this.euclidRadioButton.Checked)
            {
                List<TimeSpan> timeList = new List<TimeSpan>();
                TimeSpan time = new TimeSpan();
                foreach (var pair in this.numbers)
                {
                    DateTime d1 = DateTime.Now;
                    BigInteger result = Code.euclidAlgorithm(pair.Key, pair.Value);
                    Console.Out.Write(result.ToString() + "\n");
                    Thread.Sleep(10);
                    DateTime d2 = DateTime.Now;
                    TimeSpan d = d2.Subtract(d1);
                    timeList.Add(d);
                }

                for (var i = 0; i < timeList.Count; i++)
                {
                    this.timeAnalysisChart.Series["Euclid"].Points.AddXY(i, Convert.ToString(timeList[i].Milliseconds));
                    time += timeList[i];
                }
                this.milisecondsLabel.Text = time.Milliseconds.ToString();
            }
            else if (this.euclidRecursiveRadioButton.Checked)
            {
                List<TimeSpan> timeList = new List<TimeSpan>();
                TimeSpan time = new TimeSpan();
                foreach (var pair in this.numbers)
                {
                    DateTime d1 = DateTime.Now;
                    BigInteger result = Code.euclidRecursiveAlgorithm(pair.Key, pair.Value);
                    Console.Out.Write(result.ToString() + "\n");
                    Thread.Sleep(10);
                    DateTime d2 = DateTime.Now;
                    TimeSpan d = d2.Subtract(d1);
                    timeList.Add(d);
                }

                for (var i = 0; i < timeList.Count; i++)
                {
                    this.timeAnalysisChart.Series["Euclid Recursive"].Points.AddXY(i, Convert.ToString(timeList[i].Milliseconds));
                    time += timeList[i];
                }
                this.milisecondsLabel.Text = time.Milliseconds.ToString();
            }

            else if (this.inefficientRadioButton.Checked)
            {
                List<TimeSpan> timeList = new List<TimeSpan>();
                TimeSpan time = new TimeSpan();
                foreach (var pair in this.numbers)
                {
                    DateTime d1 = DateTime.Now;
                    BigInteger result = Code.inefficientAlgorithm(pair.Key, pair.Value);
                    Console.Out.Write(result.ToString() + "\n");
                    Thread.Sleep(10);
                    DateTime d2 = DateTime.Now;
                    TimeSpan d = d2.Subtract(d1);
                    timeList.Add(d);
                }

                for (var i = 0; i < timeList.Count; i++)
                {
                    this.timeAnalysisChart.Series["Inefficient"].Points.AddXY(i, Convert.ToString(timeList[i].Milliseconds));
                    time += timeList[i];
                }
                this.milisecondsLabel.Text = time.Milliseconds.ToString();
            }
            else
            {
                MessageBox.Show("Please select algorithm");
            }
        }



    }
}
