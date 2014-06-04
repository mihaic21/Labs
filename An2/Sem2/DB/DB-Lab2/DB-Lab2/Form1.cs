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
using System.Xml;


namespace DB_Lab2
{
    public partial class Form1 : Form
    {
        private String connectionString;// = "Data Source=(local);Initial Catalog=Sports Manager;Integrated Security=SSPI;";
        private SqlDataAdapter parentDataAdapter;
        private SqlDataAdapter childDataAdapter;
        private DataSet dataSet;
        private DataRelation dataRelation;
        private SqlCommandBuilder commandBuilder;
        private BindingSource parentBindingSource = new BindingSource();
        private BindingSource childBindingSource = new BindingSource();
        private SqlConnection dbConn;

        String dataSource, dbName, authentification, parentTableName, childTableName, primaryKey, foreignKey;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.readConfigFile();
            
            this.dataGridView1.DataSource = parentBindingSource;
            this.dataGridView2.DataSource = childBindingSource;
            this.getData();
        }

        private void getData()
        {
            //this.dbConn = new SqlConnection("Data source="+this.dataSource+";Initial Catalog="+this.dbName+";"+this.authentification);
            this.dbConn = new SqlConnection(connectionString);
            this.dataSet = new DataSet();
            this.parentDataAdapter = new SqlDataAdapter("SELECT * FROM "+parentTableName, this.dbConn);
            this.childDataAdapter = new SqlDataAdapter("SELECT * FROM "+childTableName, this.dbConn);
            this.commandBuilder = new SqlCommandBuilder(this.childDataAdapter);

            this.parentDataAdapter.Fill(this.dataSet, parentTableName);
            this.childDataAdapter.Fill(this.dataSet, childTableName);

            this.dataRelation = new DataRelation("ParentChild", this.dataSet.Tables[parentTableName].Columns[primaryKey], this.dataSet.Tables[childTableName].Columns[foreignKey]);
            this.dataSet.Relations.Add(this.dataRelation);

            this.parentBindingSource.DataSource = this.dataSet;
            this.parentBindingSource.DataMember = parentTableName;

            this.childBindingSource.DataSource = this.parentBindingSource;
            this.childBindingSource.DataMember = "ParentChild";

        }

        private void readConfigFile()
        {
            XmlDocument document = new XmlDocument();
            document.Load("D:\\Programming\\Labs\\An2\\Sem2\\DB\\DB-Lab2\\DB-Lab2\\App.config");
            //document.Load("..\\..\\App.config.xml");
            XmlNode mainNode = document.SelectSingleNode("/configuration");
            XmlNode node = mainNode.SelectSingleNode("connectionStrings");

            this.connectionString = node.SelectSingleNode("ConnectionString").Value.ToString();
            /*
            this.dataSource = node.SelectSingleNode("dataSource").InnerText;
            this.dbName = node.SelectSingleNode("dbName").InnerText;
            this.authentification = node.SelectSingleNode("authentification").InnerText;
            this.parentTableName = node.SelectSingleNode("parentTableName").InnerText;
            this.childTableName = node.SelectSingleNode("childTableName").InnerText;
            this.primaryKey = node.SelectSingleNode("primaryKey").InnerText;
            this.foreignKey = node.SelectSingleNode("foreignKey").InnerText;
             */

            this.dataSource = "(local)";
            this.dbName = "Sports Manager";
            this.authentification = "Integrated Security=SSPI";
            this.parentTableName = "Teams";
            this.childTableName = "Locations";
            this.primaryKey = "Location_ID";
            this.foreignKey = "Home_Team_ID";

        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (dataGridView2.CurrentRow != null)
                {
                    this.dataGridView2.Rows.RemoveAt(this.dataGridView2.CurrentRow.Index);
                    this.childDataAdapter.Update(this.dataSet, childTableName);
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
                this.childDataAdapter.Update(this.dataSet, childTableName);
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
