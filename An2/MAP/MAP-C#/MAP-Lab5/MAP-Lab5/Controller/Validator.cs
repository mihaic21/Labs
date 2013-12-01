using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab5.Repository;
using MAP_Lab5.Domain;

namespace MAP_Lab5.Controller
{
    class Validator
    {
        private StudentRepository repo;

        public Validator(StudentRepository repo)
        {
            this.repo = repo;
        }

        private bool studentExists(int id)
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

        private bool nameIsEmpty(String name)
        {
            if (name.Length < 1) return true;
            else return false;
        }

        public void validateStudent(Student s)
        {
            if (this.nameIsEmpty(s.getName()))
                throw new StudentException("Name cannot be empty!\n");
            if (this.studentExists(s.getId()))
                throw new StudentException("Student id already exists!\n");
        }
    }
}
