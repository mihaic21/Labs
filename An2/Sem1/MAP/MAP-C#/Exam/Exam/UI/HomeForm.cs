using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Exam.Controller;
using Exam.Model;
using Exam.Utils;

namespace Exam.UI
{
    partial class HomeForm : Form
    {
        private Controller.Controller ctrl;
        private Button exitButton = new Button();
        private ListView listView = new ListView();


        public HomeForm(Controller.Controller ctrl)
        {
            this.ctrl = ctrl;
            try
            {
                ctrl.readFromFile("transactions.txt");
            }
            catch (MyException ex)
            {
                MessageBox.Show(ex.getMessage());
            }

            if (this.ctrl.errorCounter > 0)
            {
                MessageBox.Show("No of errors: " + this.ctrl.errorCounter);
            }
            

            this.Text = "Real Estate Agency";
            this.Size = new Size(600, 600);
            this.CenterToScreen();

            this.addListView();
            this.addButtons();
        }

        private void addListView()
        {
            this.listView.View = View.Details;
            this.listView.Columns.Add("Type", -2);
            this.listView.Columns.Add("Floors", -2);
            this.listView.Columns.Add("Rooms", -2);
            this.listView.Columns.Add("Surface", -2);
            this.listView.Columns.Add("Zone", -2);
            this.listView.Columns.Add("Year", -2);
            this.listView.Columns.Add("Month", -2);

            this.listView.FullRowSelect = true;
            this.listView.GridLines = true;

            this.fillListView();

            this.listView.Size = new Size(350, 350);
            this.listView.Location = new Point(30, 20);

            this.Controls.Add(this.listView);

        }

        private void fillListView(IList<Transaction> list = null)
        {
            if (list == null)
            {
                list = this.ctrl.getAllTransactionObjects();
            }

            foreach (Transaction trans in list)
            {
                ListViewItem item = new ListViewItem();
                item.Text = trans.property.GetType().Name;

                if (trans.property.GetType().Equals(new House().GetType()))
                {
                    item.SubItems.Add(((House)trans.property).noOfFloors.ToString());
                    item.SubItems.Add(((House)trans.property).noOfRooms.ToString());
                    item.SubItems.Add(((House)trans.property).surface.ToString());
                    item.SubItems.Add(((House)trans.property).zone);
                    item.SubItems.Add(trans.year.ToString());
                    item.SubItems.Add(trans.month.ToString());

                }
                else if (trans.property.GetType().Equals(new Flat().GetType()))
                {
                    item.SubItems.Add("");
                    item.SubItems.Add(((Flat)trans.property).noOfRooms.ToString());
                    item.SubItems.Add(((Flat)trans.property).surface.ToString());
                    item.SubItems.Add(((Flat)trans.property).zone);
                    item.SubItems.Add(trans.year.ToString());
                    item.SubItems.Add(trans.month.ToString());
                }
                else if (trans.property.GetType().Equals(new CommercialSpace().GetType()))
                {
                    item.SubItems.Add("");
                    item.SubItems.Add("");
                    item.SubItems.Add(((CommercialSpace)trans.property).surface.ToString());
                    item.SubItems.Add(((CommercialSpace)trans.property).zone);
                    item.SubItems.Add(trans.year.ToString());
                    item.SubItems.Add(trans.month.ToString());
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
        }

        void exitButton_Click(object sender, EventArgs e)
        {
            System.Environment.Exit(0);
        }

    }
}
