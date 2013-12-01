using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab6.Repository;
using MAP_Lab6.Controller;
using MAP_Lab6.UI;
using MAP_Lab6.Model;

namespace MAP_Lab6
{
    class Program
    {
        static void Main(string[] args)
        {
            StudentRepository<Student> repo = new StudentRepository<Student>();
            Validator val = new Validator(repo);
            StudentController ctrl = new StudentController(repo, val);
            UI.Console cons = new UI.Console(ctrl);
        }
    }
}
