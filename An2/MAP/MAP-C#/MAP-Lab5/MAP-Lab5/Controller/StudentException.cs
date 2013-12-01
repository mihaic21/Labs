using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab5.Domain;
using MAP_Lab5.Repository;

namespace MAP_Lab5.Controller
{
    [Serializable]
    class StudentException : Exception
    {
        public StudentException(string message) : base (message)
        {}

        public string getMessage()
        {
            return this.Message;
        }
    }
}
