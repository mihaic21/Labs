using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PracticeForPractical.Utils
{
    class MyException : Exception
    {
        public MyException(string message)
            : base(message)
        { }

        public String getMessage()
        {
            return this.Message;
        }

    }
}
