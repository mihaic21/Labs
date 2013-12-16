using Partial.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Partial
{
    class Program
    {
        static void Main(string[] args)
        {
            Repository.Repository<Leguma> repo = new Repository.Repository<Leguma>();
            Controller.Controller ctrl = new Controller.Controller(repo);
        }
    }
}
