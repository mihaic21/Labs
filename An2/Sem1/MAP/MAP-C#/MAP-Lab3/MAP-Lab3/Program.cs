using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab3;

namespace MAP_Lab3
{
    class Program
    {
        static void Main(string[] args)
        {
            int number = Convert.ToInt32(args[0]) + 1;
            while (!Prime.isPrime(number)) number++;
            Console.WriteLine(number);
            Console.ReadLine();
        }
    }
}
