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

namespace PracticeProgram
{
    public partial class Form1 : Form
    {

        SqlConnection dbConn;
        string connectionString;
        SqlDataAdapter categoriesDA, moviesDA;
        DataSet ds;
        DataRelation dr;
        SqlCommandBuilder moviesCB;
        BindingSource categoriesBS, moviesBS;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            connectionString = "Data Source=(local);Initial Catalog=Practice-MovieFestival;Integrated Security=SSPI;";
            dbConn = new SqlConnection(connectionString);
            categoriesDA = new SqlDataAdapter("select * from Category", dbConn);
            moviesDA = new SqlDataAdapter("select * from Movie", dbConn);
            moviesCB = new SqlCommandBuilder(moviesDA);
            ds = new DataSet();
            categoriesDA.Fill(ds, "Category");
            moviesDA.Fill(ds, "Movie");
            dr = new DataRelation("CategoriesMovies", ds.Tables["Category"].Columns["CategoryID"], ds.Tables["Movie"].Columns["CategoryID"]);
            ds.Relations.Add(dr);

            categoriesBS = new BindingSource();
            moviesBS = new BindingSource();

            categoriesBS.DataSource = ds;
            categoriesBS.DataMember = "Category";
            moviesBS.DataSource = categoriesBS;

            categoriesComboBox.DataSource = categoriesBS;
            categoriesComboBox.DisplayMember = "CategoryName";
            moviesDataGridView.DataSource = moviesBS;
        }

        private void saveMoviesButton_Click(object sender, EventArgs e)
        {
            moviesDA.Update(ds, "Movie");
        }

        private void categoriesComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            moviesDataGridView.DataSource = moviesBS;
        }

        

    }
}
