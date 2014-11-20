using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Exam
{
    public partial class Form1 : Form
    {

        string connectionString;
        SqlConnection dbConn;
        SqlDataAdapter usersDA, runsDA;
        DataSet dataSet;
        DataRelation dataRelation;
        BindingSource usersBS, runsBS;
        SqlCommandBuilder runsCB;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            connectionString = "Data Source=(local);Initial Catalog=Exam-UserRuns;Integrated Security=SSPI;";
            dbConn = new SqlConnection(connectionString);

            usersDA = new SqlDataAdapter("select * from Users", dbConn);
            runsDA = new SqlDataAdapter("select * from Runs", dbConn);

            runsCB = new SqlCommandBuilder(runsDA);

            dataSet = new DataSet();
            usersDA.Fill(dataSet, "Users");
            runsDA.Fill(dataSet, "Runs");

            dataRelation = new DataRelation("User_Runs", dataSet.Tables["Users"].Columns["userID"], dataSet.Tables["Runs"].Columns["userID"]);
            dataSet.Relations.Add(dataRelation);

            usersBS = new BindingSource();
            runsBS = new BindingSource();

            usersBS.DataSource = dataSet;
            usersBS.DataMember = "Users";

            runsBS.DataSource = usersBS;
            runsBS.DataMember = "User_Runs";

            usersComboBox.DataSource = usersBS;
            usersComboBox.DisplayMember = "userName";

            runsDataGridView.DataSource = runsBS;

        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            runsDA.Update(dataSet, "Runs");
        }

    }
}
