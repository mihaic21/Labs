using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab7.Controller;
using MAP_Lab7.Repository;
using MAP_Lab7.UI;
using MAP_Lab7.Model;

namespace MAP_Lab7
{
    class Program
    {
        static void Main(string[] args)
        {
            Repository<Student> repo = new Repository<Student>();
            Validator val = new Validator(repo);
            StudentController ctrl = new StudentController(repo, val);
            UI.Console cons = new UI.Console(ctrl);
        }
    }
}
