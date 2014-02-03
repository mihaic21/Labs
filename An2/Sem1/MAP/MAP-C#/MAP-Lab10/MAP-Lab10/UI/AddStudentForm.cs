using MAP_Lab10.Utils;
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
    partial class AddStudentForm : Form
    {
        private Controller.Controller ctrl;
        private ComboBox comboBox = new ComboBox();
        private Button backButton = new Button();
        private Button addButton = new Button();
        private Label idLabel = new Label();
        private Label nameLabel = new Label();
        private Label gradeLabel = new Label();
        private Label grade2Label = new Label();
        private Label grade3Label = new Label();
        private Label supervisorLabel = new Label();
        private Label thesisLabel = new Label();
        private TextBox idTextBox = new TextBox();
        private TextBox nameTextBox = new TextBox();
        private TextBox gradeTextBox = new TextBox();
        private TextBox grade2TextBox = new TextBox();
        private TextBox grade3TextBox = new TextBox();
        private TextBox supervisorTextBox = new TextBox();
        private TextBox thesisTextBox = new TextBox();

        private enum Type
        {
            Student,
            Graduate,
            PhD,
            Undergraduate
        }

        public AddStudentForm(Controller.Controller ctrl)
        {
            this.ctrl = ctrl;
            this.Size = new Size(600, 600);
            this.Text = "Add Student";
            this.CenterToScreen();

            this.addButtons();
            this.addComboBox();
            this.addTextBoxes();
            this.addLabels();
        }

        private void addLabels()
        {
            this.idLabel.Text = "ID: ";
            this.nameLabel.Text = "Name: ";
            this.gradeLabel.Text = "Grade: ";
            this.grade2Label.Text = "Grade 2: ";
            this.grade3Label.Text = "Grade 3: ";
            this.supervisorLabel.Text = "Supervisor: ";
            this.thesisLabel.Text = "Thesis: ";

            this.idLabel.Size = new Size(50, 20);
            this.nameLabel.Size = new Size(50, 20);
            this.gradeLabel.Size = new Size(50, 20);
            this.grade2Label.Size = new Size(50, 20);
            this.grade3Label.Size = new Size(50, 20);
            this.supervisorLabel.Size = new Size(50, 20);
            this.thesisLabel.Size = new Size(50, 20);

            this.idLabel.Location = new Point(100, 150);
            this.nameLabel.Location = new Point(100, 200);
            this.gradeLabel.Location = new Point(100, 250);
            this.grade2Label.Location = new Point(100, 300);
            this.grade3Label.Location = new Point(100, 350);
            this.supervisorLabel.Location = new Point(100, 400);
            this.thesisLabel.Location = new Point(100, 450);

            this.Controls.Add(this.idLabel);
            this.Controls.Add(this.nameLabel);
            this.Controls.Add(this.gradeLabel);
            this.Controls.Add(this.grade2Label);
            this.Controls.Add(this.grade3Label);
            this.Controls.Add(this.supervisorLabel);
            this.Controls.Add(this.thesisLabel);
        }

        private void addButtons()
        {
            this.backButton.Text = "Back";
            this.backButton.Size = new Size(50, 30);
            this.backButton.Location = new Point(320, 500);
            this.backButton.Click += backButton_Click;
            this.Controls.Add(this.backButton);

            this.addButton.Text = "Add Student";
            this.addButton.Size = new Size(80, 30);
            this.addButton.Location = new Point(220, 500);
            this.addButton.Click += addButton_Click;
            this.Controls.Add(this.addButton);
        }

        void addButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (this.comboBox.SelectedValue != null)
                {
                    if (this.comboBox.SelectedValue.Equals(Type.Student))
                    {
                        this.ctrl.addStudent(Convert.ToInt32(idTextBox.Text), nameTextBox.Text, Convert.ToInt32(gradeTextBox.Text));
                        this.ctrl.saveStudentsInFile("students.txt");
                    }
                    else if (this.comboBox.SelectedValue.Equals(Type.Graduate))
                    {
                        this.ctrl.addStudent(Convert.ToInt32(idTextBox.Text), nameTextBox.Text, Convert.ToInt32(gradeTextBox.Text), supervisorTextBox.Text, Convert.ToInt32(grade2TextBox.Text), Convert.ToInt32(grade3TextBox.Text));
                        this.ctrl.saveStudentsInFile("students.txt");
                    }
                    else if (this.comboBox.SelectedValue.Equals(Type.Undergraduate))
                    {
                        this.ctrl.addStudent(Convert.ToInt32(idTextBox.Text), nameTextBox.Text, Convert.ToInt32(gradeTextBox.Text), Convert.ToInt32(grade2TextBox.Text));
                        this.ctrl.saveStudentsInFile("students.txt");
                    }
                    else if (this.comboBox.SelectedValue.Equals(Type.PhD))
                    {
                        this.ctrl.addStudent(Convert.ToInt32(idTextBox.Text), nameTextBox.Text, Convert.ToInt32(gradeTextBox.Text), supervisorTextBox.Text, thesisTextBox.Text, Convert.ToInt32(grade2TextBox.Text));
                        this.ctrl.saveStudentsInFile("students.txt");
                    }

                    this.DialogResult = DialogResult.Yes;
                }
            }
            catch (FormatException ex)
            {
                MessageBox.Show(ex.Message);
            }
            catch (MyException ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        void backButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void addTextBoxes()
        {
            this.idTextBox.Size = new Size(180, 20);
            this.nameTextBox.Size = new Size(180, 20);
            this.gradeTextBox.Size = new Size(180, 20);
            this.grade2TextBox.Size = new Size(180, 20);
            this.grade3TextBox.Size = new Size(180, 20);
            this.supervisorTextBox.Size = new Size(180, 20);
            this.thesisTextBox.Size = new Size(180, 20);

            this.idTextBox.Location = new Point(150, 150);
            this.nameTextBox.Location = new Point(150, 200);
            this.gradeTextBox.Location = new Point(150, 250);
            this.grade2TextBox.Location = new Point(150, 300);
            this.grade3TextBox.Location = new Point(150, 350);
            this.supervisorTextBox.Location = new Point(150, 400);
            this.thesisTextBox.Location = new Point(150, 450);

            this.idTextBox.Enabled = false;
            this.nameTextBox.Enabled = false;
            this.gradeTextBox.Enabled = false;
            this.grade2TextBox.Enabled = false;
            this.grade3TextBox.Enabled = false;
            this.supervisorTextBox.Enabled = false;
            this.thesisTextBox.Enabled = false;

            this.Controls.Add(this.idTextBox);
            this.Controls.Add(this.nameTextBox);
            this.Controls.Add(this.gradeTextBox);
            this.Controls.Add(this.grade2TextBox);
            this.Controls.Add(this.grade3TextBox);
            this.Controls.Add(this.supervisorTextBox);
            this.Controls.Add(this.thesisTextBox);
        }

        private void addComboBox()
        {
            this.comboBox.Size = new Size(100, 30);
            this.comboBox.Location = new Point(150, 50);
            this.comboBox.DataSource = Enum.GetValues(typeof(Type));
            this.comboBox.Name = "Type";
            this.comboBox.SelectedValueChanged += comboBox_SelectedValueChanged;
            this.Controls.Add(this.comboBox);
        }

        void comboBox_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.comboBox.SelectedValue != null)
            {
                if (this.comboBox.SelectedValue.Equals(Type.Student))
                {
                    idTextBox.Enabled = true;
                    nameTextBox.Enabled = true;
                    gradeTextBox.Enabled = true;
                    grade2TextBox.Enabled = false;
                    grade3TextBox.Enabled = false;
                    supervisorTextBox.Enabled = false;
                    thesisTextBox.Enabled = false;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Graduate))
                {
                    idTextBox.Enabled = true;
                    nameTextBox.Enabled = true;
                    gradeTextBox.Enabled = true;
                    grade2TextBox.Enabled = true;
                    grade3TextBox.Enabled = true;
                    supervisorTextBox.Enabled = true;
                    thesisTextBox.Enabled = false;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Undergraduate))
                {
                    idTextBox.Enabled = true;
                    nameTextBox.Enabled = true;
                    gradeTextBox.Enabled = true;
                    grade2TextBox.Enabled = true;
                    grade3TextBox.Enabled = false;
                    supervisorTextBox.Enabled = false;
                    thesisTextBox.Enabled = false;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.PhD))
                {
                    idTextBox.Enabled = true;
                    nameTextBox.Enabled = true;
                    gradeTextBox.Enabled = true;
                    grade2TextBox.Enabled = true;
                    grade3TextBox.Enabled = false;
                    supervisorTextBox.Enabled = true;
                    thesisTextBox.Enabled = true;
                }
            }
        }
    }
}
