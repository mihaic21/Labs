using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab10.Model
{
    class Student : Comparable<Student>, HasID
    {
        private string name;
        private int id;
        private int grade;

        public Student()
        {

        }

        public Student(int id, string name, int grade)
        {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        public int getID()
        {
            return this.id;
        }

        public int getGrade()
        {
            return this.grade;
        }

        public String getName()
        {
            return this.name;
        }

        public virtual float average()
        {
            return grade;
        }

        bool Comparable<Student>.isGreaterThan(Student student)
        {
            return (this.grade > student.grade);
        }

        public override String ToString()
        {
            return this.id + " " + this.name + " " + this.grade;
        }
    }
}
