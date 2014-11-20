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


namespace Practice_SystemTracking
{
    public partial class Form1 : Form
    {

        string connectionString;
        SqlConnection dbConn;
        SqlDataAdapter projectsDA, tasksDA;
        DataSet dataSet;
        DataRelation dataRelation;
        BindingSource projectsBS, tasksBS;
        SqlCommandBuilder tasksCB;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            connectionString = "Data Source=(local);Initial Catalog=Practice-TrackingSystem;Integrated Security=SSPI;";
            dbConn = new SqlConnection(connectionString);

            projectsDA = new SqlDataAdapter("SELECT * FROM Project", dbConn);
            tasksDA = new SqlDataAdapter("SELECT * FROM Task", dbConn);

            tasksCB = new SqlCommandBuilder(tasksDA);

            dataSet = new DataSet();
            projectsDA.Fill(dataSet, "Project");
            tasksDA.Fill(dataSet, "Task");

            dataRelation = new DataRelation("Project_Tasks", dataSet.Tables["Project"].Columns["ProjID"],
                                                            dataSet.Tables["Task"].Columns["ProjID"]);
            dataSet.Relations.Add(dataRelation);

            projectsBS = new BindingSource();
            tasksBS = new BindingSource();

            projectsBS.DataSource = dataSet;
            projectsBS.DataMember = "Project";

            tasksBS.DataSource = projectsBS;
            tasksBS.DataMember = "Project_Tasks";


            projectsComboBox.DataSource = projectsBS;
            projectsComboBox.DisplayMember = "ProjName";

            tasksDataGridView.DataSource = tasksBS;
        }

        private void saveTasksButton_Click(object sender, EventArgs e)
        {
            tasksDA.Update(dataSet, "Task");
        }

    }
}
