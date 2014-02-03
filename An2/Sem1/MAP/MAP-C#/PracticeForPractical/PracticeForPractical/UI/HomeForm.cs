using PracticeForPractical.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PracticeForPractical.UI
{
    partial class HomeForm : Form
    {
        private Controller.Controller ctrl;
        private ListView listView = new ListView();
        private Button exitButton = new Button();
        private Button viewAllPropertiesButton = new Button();
        private Button addPropertyButton = new Button();

        public HomeForm(Controller.Controller ctrl)
        {
            this.ctrl = ctrl;
            this.Text = "Real Estate Agency";
            this.Size = new Size(600, 600);
            this.CenterToScreen();

            this.addListView();
            this.addButtons();
        }

        private void addListView()
        {
            this.listView.View = View.Details;
            this.listView.Columns.Add("ID", -2);
            this.listView.Columns.Add("Type", -2);
            this.listView.Columns.Add("Surface", -2);
            this.listView.Columns.Add("Rental Price", -2);
            this.listView.Columns.Add("No Of Rooms", -2);
            this.listView.Columns.Add("No Of Floors", -2);
            this.listView.Columns.Add("Zone", -2);

            this.listView.FullRowSelect = true;
            this.listView.GridLines = true;

            this.fillListView();

            this.listView.Size = new Size(400, 350);
            this.listView.Location = new Point(30, 20);

            this.Controls.Add(this.listView);

            try
            {
                this.ctrl.readFromFile("properties.txt");
            }
            catch (IOException ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void fillListView(IList<Property> list = null)
        {
            if (list == null)
            {
                list = this.ctrl.getAllPropertyObjects();
            }

            foreach (Property property in list)
            {
                ListViewItem item = new ListViewItem();
                item.Text = property.getID().ToString();
                item.SubItems.Add(property.GetType().Name);
                item.SubItems.Add(property.surface.ToString());
                item.SubItems.Add(property.rentalPrice.ToString());

                if (property.GetType().Equals(new Apartament().GetType()))
                {
                    item.SubItems.Add(((Apartament)property).noOfRooms.ToString());
                }
                else if (property.GetType().Equals(new House().GetType()))
                {
                    item.SubItems.Add(((House)property).noOfRooms.ToString());
                    item.SubItems.Add(((House)property).noOfFloors.ToString());
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
