﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab4.Controller;
using MAP_Lab4.Repository;
using MAP_Lab4.UI;

namespace MAP_Lab4
{
    class Program
    {
        static void Main(string[] args)
        {
            StudentRepository repo = new StudentRepository(100);
            Validator val = new Validator(repo);
            StudentController ctrl = new StudentController(repo, val);
            UI.Console cons = new UI.Console(ctrl);
            cons.run();
        }
    }
}
