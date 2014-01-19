using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab9.Model
{
    [Serializable()]
    class GraduateStudent : Student
    {
        public String supervisor;
        public int grade2;
        public int grade3;

        public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3)
            : base(id, name, grade)
        {
            this.supervisor = supervisor;
            this.grade2 = grade2;
            this.grade3 = grade3;
        }

        public override float average()
        {
            return (this.getGrade() + this.grade2 + this.grade3) / 3;
        }


        public override String ToString()
        {
            return this.getID() + " " + this.getName() + " " + this.getGrade() + " " + this.supervisor + " " + this.grade2 + " " + this.grade3;
        }
    }
}
