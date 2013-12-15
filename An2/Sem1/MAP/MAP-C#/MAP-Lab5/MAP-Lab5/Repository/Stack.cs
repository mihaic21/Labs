using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab5.Domain;
using MAP_Lab5.Controller;

namespace MAP_Lab5.Repository
{
    class Stack
    {
        private int size;
        private int capacity;
        private Student[] students;

        public Stack(int capacity)
        {
            /*
            * pre cond: int sCapacity - maximum number of students to be added
            * post cond: object Stack
            * 
            * creates Stack, allocates memory
            */

            students = new Student[capacity];
            size = -1;
            this.capacity = capacity;
        }

        public void push(Student s)
        {
            /*
            * pre cond: s of type Student
            * post cond: -
            * 
            * adds object on top of stack; if maximum capacity is reached,
            * it doubles it
            */


            size++;
            if (size == capacity)
                increaseCapacity();
            students[size] = s;
        }

        public Student pop()
        {
            /*
            * pre cond: -
            * post cond: the last added Student
            * 
            * removes the object on top of the stack and returns it
            */
            if (isEmpty())
                throw new StudentException("Cannot pop from empty stack!");
            else
            {
                Student student = students[size];
                students[size] = null;
                size--;
                return student;
            }
        }

        private void increaseCapacity()
        {
            /*
            * pre cond: -
            * post cond: the capacity is doubled
            * 
            * doubles the current capacity of the stack
            */

            capacity *= 2;
        }

        private bool isEmpty()
        {
            if (this.size < 0)
                return true;
            return false;
        }

        public int getStackSize()
        {
            return this.size;
        }

        public int getStackCapacity()
        {
            return this.capacity;
        }

    }
}
