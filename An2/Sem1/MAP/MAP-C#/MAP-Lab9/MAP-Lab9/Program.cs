using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab9.Repository;
using MAP_Lab9.Model;
using MAP_Lab9.Controller;

namespace MAP_Lab9
{
    class Program
    {
        static void Main(string[] args)
        {
            Repository<Student> repo = new Repository<Student>();
            Validator val = new Validator(repo);
            Controller.Controller ctrl = new Controller.Controller(repo, val);
            UI.Console cons = new UI.Console(ctrl);
        }
    }
}
