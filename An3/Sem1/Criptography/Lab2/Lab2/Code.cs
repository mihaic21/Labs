using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace Lab2
{
    class Code
    {

        public static BigInteger euclidAlgorithm(BigInteger firstNumber, BigInteger secondNumber)
        {
            if ((firstNumber == 0) || (secondNumber == 0))
            {
                return 0;
            }
            else if ((firstNumber < 0) || (secondNumber < 0))
            {
                return -1;
            }

            BigInteger r;

            while (true)
            {
                r = firstNumber % secondNumber;
                if (r == 0)
                {
                    break;
                }

                firstNumber = secondNumber;
                secondNumber = r;
            }

            return secondNumber;
        }

        public static BigInteger euclidRecursiveAlgorithm(BigInteger firstNumber, BigInteger secondNumber)
        {
            if (firstNumber == 0)
            {
                return secondNumber;
            }

            return euclidRecursiveAlgorithm(secondNumber % firstNumber, firstNumber);
        }

        public static BigInteger inefficientAlgorithm(BigInteger firstNumber, BigInteger secondNumber)
        {
            BigInteger count;
            BigInteger div = 1;

            BigInteger minValue;
            BigInteger maxValue;

            if (firstNumber > secondNumber)
            {
                minValue = secondNumber;
                maxValue = firstNumber;
            }
            else
            {
                minValue = firstNumber;
                maxValue = secondNumber;
            }
            
            for (count = 2; count <= BigInteger.Divide(minValue, 2); count++)
            {
                if ((firstNumber % count == 0) && (secondNumber % count == 0))
                {
                    div = count;
                }
            }

            if (maxValue % minValue == 0)
            {
                return minValue;
            }

            return div;
        }

    }
}
