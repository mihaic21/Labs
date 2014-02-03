using MAP_Lab10.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MAP_Lab10.UI
{
    partial class HomeForm : Form
    {
        private Controller.Controller ctrl;
        private Button exitButton = new Button();
        private Button viewAllStudentsButton = new Button();
        private Button addStudentButton = new Button();
        private ListView listView = new ListView();
        private ComboBox filterComboBox = new ComboBox();


        public HomeForm(Controller.Controller ctrl)
        {
            this.ctrl = ctrl;
            this.Text = "Students Manager";
            this.Size = new Size(600, 600);
            this.CenterToScreen();

            this.addListView();
            this.addButtons();
            this.addComboBox();

        }

        private void addListView()
        {
            this.listView.View = View.Details;
            this.listView.Columns.Add("ID", -2);
            this.listView.Columns.Add("Name", -2);
            this.listView.Columns.Add("Type", -2);
            this.listView.Columns.Add("Grade", -2);
            this.listView.Columns.Add("Grade2", -2);
            this.listView.Columns.Add("Grade3", -2);
            this.listView.Columns.Add("Supervisor", -2);
            this.listView.Columns.Add("Thesis", -2);
            
            this.listView.FullRowSelect = true;
            this.listView.GridLines = true;

            this.fillListView();

            this.listView.Size = new Size(350, 350);
            this.listView.Location = new Point(30, 20);

            this.Controls.Add(this.listView);

        }

        private void fillListView(IList<Student> list = null)
        {
            if (list == null)
            {
                list = this.ctrl.getAllStudentObjects();
            }

            foreach (Student student in list)
            {
                ListViewItem item = new ListViewItem();
                item.SubItems.Add(student.getName());
                item.SubItems.Add(student.GetType().Name);
                item.SubItems.Add(student.getGrade().ToString());
                item.Text = student.getID().ToString();

                if (student.GetType().Equals(new GraduateStudent().GetType()))
                {
                    item.SubItems.Add(((GraduateStudent)student).grade2.ToString());
                    item.SubItems.Add(((GraduateStudent)student).grade3.ToString());
                    item.SubItems.Add(((GraduateStudent)student).supervisor);
                }
                else if (student.GetType().Equals(new UndergraduateStudent().GetType()))
                {
                    item.SubItems.Add(((UndergraduateStudent)student).grade2.ToString());
                }
                else if (student.GetType().Equals(new PhDStudent().GetType()))
                {
                    item.SubItems.Add(((PhDStudent)student).grade2.ToString());
                    item.SubItems.Add("");
                    item.SubItems.Add(((PhDStudent)student).supervisor);
                    item.SubItems.Add(((PhDStudent)student).thesis);
                }

                this.listView.Items.Add(item);
            }
        }



        private void addButtons()
        {
            this.exitButton.Text = "Exit";
            this.exitButton.Size = new Size(50, 30);
            this.exitButton.Location = new Point(300, 500);
            this.exitButton.Click += exitButton_Click;
            this.Controls.Add(exitButton);

            this.viewAllStudentsButton.Text = "View All";
            this.viewAllStudentsButton.Size = new Size(100, 30);
            this.viewAllStudentsButton.Location = new Point(150, 500);
            this.viewAllStudentsButton.Click += viewAllStudentsButton_Click;
            this.Controls.Add(viewAllStudentsButton);

            this.addStudentButton.Text = "Add Student";
            this.addStudentButton.Size = new Size(100, 30);
            this.addStudentButton.Location = new Point(450, 500);
            this.addStudentButton.Click += addStudentButton_Click;
            this.Controls.Add(this.addStudentButton);
        }

        void addStudentButton_Click(object sender, EventArgs e)
        {
            Form addStudentForm = new AddStudentForm(this.ctrl);
            //addStudentForm.ShowDialog();
            if (addStudentForm.ShowDialog() == DialogResult.Yes)
            {
                this.listView.Items.Clear();
                this.fillListView();
            }
        }

        private void addComboBox()
        {
            this.filterComboBox.Size = new Size(100, 20);
            this.filterComboBox.Location = new Point(450, 50);

            Dictionary<string, string> filterTypes = new Dictionary<string, string>();
            filterTypes.Add("1", "avg >= 5");
            filterTypes.Add("2", "avg < 5");
            filterTypes.Add("3", "avg = 10");

            this.filterComboBox.DataSource = new BindingSource(filterTypes, null);
            this.filterComboBox.DisplayMember = "Value";
            this.filterComboBox.ValueMember = "Key";

            this.filterComboBox.SelectedValueChanged += filterComboBox_SelectedValueChanged;

            this.Controls.Add(this.filterComboBox);
        }

        void filterComboBox_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.filterComboBox.SelectedValue.Equals("1"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.ctrl.getAllStudentObjects())
                {
                    if (student.average() >= 5)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
            else if (this.filterComboBox.SelectedValue.Equals("2"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.ctrl.getAllStudentObjects())
                {
                    if (student.average() < 5)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
            else if (this.filterComboBox.SelectedValue.Equals("3"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.ctrl.getAllStudentObjects())
                {
                    if (student.average() == 10)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
        }


/*
        private void indexChanged(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection selected = this.listView.SelectedItems;

            if (selected.Count < 0)
                return;

            Student selectedStudent = null;
            foreach (Student student in this.ctrl.getAllStudentObjects())
            {
                if (student.getID().ToString().Equals(selected[0].Text))
                {
                    selectedStudent = student;
                    break;
                }
            }

            if (selectedStudent != null)
            {
                //continue
                
            }

        }

        private void addLabels()
        {
            this.gradeLabel.Text = "Grade: ";
            this.grade2Label.Text = "Grade 2: ";
            this.grade3Label.Text = "Grade 3: ";
            this.supervisorLabel.Text = "Supervisor: ";
            this.thesisLabel.Text = "Thesis: ";

            this.gradeLabel.Size = new Size(50, 20);
            this.grade2Label.Size = new Size(50, 20);
            this.grade3Label.Size = new Size(50, 20);
            this.supervisorLabel.Size = new Size(50, 20);
            this.thesisLabel.Size = new Size(50, 20);

            this.gradeLabel.Location = new Point(300, 50);
            this.grade2Label.Location = new Point(300, 100);
            this.grade3Label.Location = new Point(300, 150);
            this.supervisorLabel.Location = new Point(300, 200);
            this.thesisLabel.Location = new Point(300, 250);

            this.Controls.Add(this.gradeLabel);
            this.Controls.Add(this.grade2Label);
            this.Controls.Add(this.grade3Label);
            this.Controls.Add(this.supervisorLabel);
            this.Controls.Add(this.thesisLabel);
        }

        private void addTextBoxes()
        {
            this.gradeTextBox.Size = new Size(180, 20);
            this.grade2TextBox.Size = new Size(180, 20);
            this.grade3TextBox.Size = new Size(180, 20);
            this.supervisorTextBox.Size = new Size(180, 20);
            this.thesisTextBox.Size = new Size(180, 20);

            this.gradeTextBox.Location = new Point(360, 50);
            this.grade2TextBox.Location = new Point(360, 100);
            this.grade3TextBox.Location = new Point(360, 150);
            this.supervisorTextBox.Location = new Point(360, 200);
            this.thesisTextBox.Location = new Point(360, 250);

            this.Controls.Add(this.gradeTextBox);
            this.Controls.Add(this.grade2TextBox);
            this.Controls.Add(this.grade3TextBox);
            this.Controls.Add(this.supervisorTextBox);
            this.Controls.Add(this.thesisTextBox);
        }

        private void clearTextBoxes()
        {
            foreach (Control control in this.Controls)
            {
                if (Controls.GetType().Equals(new TextBox().GetType()))
                {
                    control.Text = "";
                }
            }
        }
*/
        private void viewAllStudentsButton_Click(object sender, EventArgs e)
        {
            this.listView.Items.Clear();
            this.fillListView();
        }


        private void exitButton_Click(object sender, EventArgs e)
        {
            System.Environment.Exit(0);
        }

    }
}
