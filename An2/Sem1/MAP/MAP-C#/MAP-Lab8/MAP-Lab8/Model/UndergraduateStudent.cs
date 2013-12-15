using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab8.Model
{
    [Serializable()]
    class UndergraduateStudent : Student
    {
        public int grade2;

        public UndergraduateStudent(int id, String name, int grade, int grade2)
            : base(id, name, grade)
        {
            this.grade2 = grade2;
        }

        public override float average()
        {
            return (this.getGrade() + this.grade2) / 2;
        }


        public override String ToString()
        {
            return this.getID() + " " + this.getName() + " " + this.getGrade() + " " + this.grade2;
        }
    }
}
