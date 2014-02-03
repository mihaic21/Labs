using PracticeForPractical.Model;
using PracticeForPractical.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using PracticeForPractical.Repository;
using PracticeForPractical.Controller;
namespace PracticeForPractical
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Repository.RepoInterface<Property> repo = new Repository<Property>();
            Validator val = new Validator(repo);
            Controller.Controller ctrl = new Controller.Controller(repo, val);
            //ctrl.readFromFile("properties.txt");

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new HomeForm(ctrl));
        }
    }
}
