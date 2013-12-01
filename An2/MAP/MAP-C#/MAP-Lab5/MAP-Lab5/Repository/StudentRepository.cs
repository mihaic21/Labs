using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab5.Domain;

namespace MAP_Lab5.Repository
{
    class StudentRepository
    {
        private int capacity;
        private Stack stack;

        public StudentRepository(int capacity)
        {
            this.capacity = capacity;
            this.stack = new Stack(capacity);
        }

        public void addStudent(Student s)
        {
            /*
             * pre cond: s of type Student
            * post cond: -
            * 
            * Adds on top of the stack a student object
            */

            stack.push(s);
        }

        public Student getStudent()
        {
            /*
            * pre cond: -
            * post cond: a Student type object
            * 
            * Removes the element on top of the stack and returns it
            */

            return stack.pop();
        }

        public int getStackSize()
        {
            return stack.getStackSize();
        }

        public int getStackCapacity()
        {
            return stack.getStackCapacity();
        }
    }
}
