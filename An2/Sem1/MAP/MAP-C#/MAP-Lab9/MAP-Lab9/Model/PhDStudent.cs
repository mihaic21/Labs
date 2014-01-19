using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab9.Model
{
    [Serializable()]
    class PhDStudent : Student
    {
        public String supervisor;
        public String thesis;
        public int grade2;

        public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2)
            : base(id, name, grade)
        {
            this.supervisor = supervisor;
            this.thesis = thesis;
            this.grade2 = grade2;
        }

        public override float average()
        {
            return (this.getGrade() + this.grade2) / 2;
        }


        public override String ToString()
        {
            return this.getID() + " " + this.getName() + " " + this.getGrade() + " " + this.supervisor + " " + this.thesis + " " + this.grade2;
        }
    }
}
