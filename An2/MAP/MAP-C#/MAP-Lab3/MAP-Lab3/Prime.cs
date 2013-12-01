using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab3
{
    class Prime
    {
        static public bool isPrime(int x)
        {
            if (x == 1) return false;
            if (x == 2) return true;
            if (x % 2 == 0) return false;
            for (int i = 3; i * i <= x; i += 2)
            {
                if (x % i == 0) return false;
            }
            return true;
        }
    }
}
