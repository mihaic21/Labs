using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab6.Utils
{
    class MyException : Exception
    {
        public MyException(string message)
            : base(message)
        { }

        public string getMessage()
        {
            return this.Message;
        }
    }
}
