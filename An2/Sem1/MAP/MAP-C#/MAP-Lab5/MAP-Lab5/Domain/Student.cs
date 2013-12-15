using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab5.Domain
{
    class Student
    {
        private String name;
        private int id;
        private int grade;

        Student()
        {
            name = null;
            id = 0;
            grade = 0;
        }

        public Student(String name, int id, int grade)
        {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        public int getId()
        {
            return this.id;
        }

        public int getGrade()
        {
            return this.grade;
        }

        public string getName()
        {
            return this.name;
        }

        public String toString()
        {
            return name + " " + id + " " + grade;
        }
    }
}
