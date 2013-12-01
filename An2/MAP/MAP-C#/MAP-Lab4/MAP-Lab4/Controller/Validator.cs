using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab4.Domain;
using MAP_Lab4.Repository;

namespace MAP_Lab4.Controller
{
    class Validator
    {
        private StudentRepository repo;

        public Validator(StudentRepository repo)
        {
            this.repo = repo;
        }

        public bool studentExists(int id)
        {        
         /*
         * pre cond: int id
         * post cond: true - if student id is found in stack
         *            false - otherwise
         * 
         * checks if a student id was already used
         */

            Student student;
            Stack newStack = new Stack(this.repo.getStackCapacity());
            bool ok = false;
            while (!ok && this.repo.getStackSize() > -1)
            {
                student = this.repo.getStudent();
                if (student.getId() == id)
                    ok = true;
                newStack.push(student);
            }
            while (newStack.getStackSize() > -1)
                this.repo.addStudent(newStack.pop());
            if (ok) return true;
            else return false;
        }

        public bool nameIsEmpty(String name)
        {
            if (name.Length < 1) return true;
            else return false;
        }

    }
}
