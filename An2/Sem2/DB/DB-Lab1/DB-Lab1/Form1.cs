using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DB_Lab1
{
    public partial class Form1 : Form
    {
        private String connectionString = "Data Source=(local);Initial Catalog=Sports Manager;Integrated Security=SSPI;";
        private SqlDataAdapter daSports;
        private SqlDataAdapter daLeagues;
        private DataSet dataSet;
        private DataRelation dr;
        private SqlCommandBuilder commandBuilder;
        private BindingSource bsSports = new BindingSource();
        private BindingSource bsLeagues = new BindingSource();
        private SqlConnection dbConn;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.dataGridView1.DataSource = bsSports;
            this.dataGridView2.DataSource = bsLeagues;
            this.getData();
        }

        private void getData()
        {
            this.dbConn = new SqlConnection(connectionString);
            this.dataSet = new DataSet();
            this.daSports = new SqlDataAdapter("SELECT * FROM SPORTS", this.dbConn);
            this.daLeagues = new SqlDataAdapter("SELECT * FROM LEAGUES", this.dbConn);
            this.commandBuilder = new SqlCommandBuilder(this.daLeagues);
            
            this.daSports.Fill(this.dataSet, "Sports");
            this.daLeagues.Fill(this.dataSet, "Leagues");

            this.dr = new DataRelation("SportsLeagues", this.dataSet.Tables["Sports"].Columns["League_ID"], this.dataSet.Tables["Leagues"].Columns["Sport_ID"]);
            this.dataSet.Relations.Add(this.dr);

            this.bsSports.DataSource = this.dataSet;
            this.bsSports.DataMember = "Sports";

            this.bsLeagues.DataSource = this.bsSports;
            this.bsLeagues.DataMember = "SportsLeagues";

        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (dataGridView2.CurrentRow != null)
                {
                    this.dataGridView2.Rows.RemoveAt(this.dataGridView2.CurrentRow.Index);
                    this.daLeagues.Update(this.dataSet, "Leagues");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
            
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            try
            {
                this.daLeagues.Update(this.dataSet, "Leagues");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
            
        }

        private void exitButton_Click(object sender, EventArgs e)
        {
            System.Environment.Exit(0);
        }

    }
}
