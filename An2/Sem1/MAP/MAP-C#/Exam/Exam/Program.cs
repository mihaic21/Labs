using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Exam.Repository;
using Exam.Controller;
using Exam.Model;
using Exam.UI;

namespace Exam
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            RepoInterface<Transaction> repo = new Repository<Transaction>();
            Validator val = new Validator(repo);
            Controller.Controller ctrl = new Controller.Controller(repo,val);

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new HomeForm(ctrl));
        }
    }
}
