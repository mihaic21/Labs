using MAP_Lab10.Controller;
using MAP_Lab10.Model;
using MAP_Lab10.Repository;
using MAP_Lab10.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MAP_Lab10
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            RepoInterface<Student> repo = new Repository<Student>();
            Validator val = new Validator(repo);
            Controller.Controller ctrl = new Controller.Controller(repo, val);
            ctrl.readFromFile("students.txt");

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new HomeForm(ctrl));
        }
    }
}
