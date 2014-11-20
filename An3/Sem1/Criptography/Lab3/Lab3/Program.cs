using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = 221;
            List<int> result = pseudoPrimesBases(n);
            foreach (int element in result)
            {
                if (element == 1 || element == n - 1)
                {
                    continue;
                }
                Console.WriteLine(element);
            }
            Console.Read();

        }

        static long euclidAlgorithm(long a, long b)
        {
            // Computes the GCD using euclid algorithm
            return b == 0 ? a : euclidAlgorithm(b, a % b);
        }

        static int stepsMod(int theBase, int power, int n)
        {
            int aux = theBase;
            while (power > 1)
            {
                theBase *= aux;
                power--;
                if (theBase >= n)
                {
                    theBase %= n;
                }
            }
            return theBase;
        }

        static List<int> getBase(int n)
        {
            List<int> baseList = new List<int>();

            for (int i = 1; i < n; i++)
            {
                if (euclidAlgorithm(i, n) == 1)
                {
                    baseList.Add(i);
                }
            }
            return baseList;
        }
        static List<int> pseudoPrimesBases(int n)
        {
            List<int> basesL = getBase(n);
            List<int> pseudoPrimeBasesL = new List<int>();
            foreach (int i in basesL)
            {
                if (stepsMod(i, n - 1, n) == 1)
                {
                    pseudoPrimeBasesL.Add(i);
                }
            }
            return pseudoPrimeBasesL;
        }
    }
}
