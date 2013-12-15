using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab7.Repository;
using MAP_Lab7.Model;
using MAP_Lab7.Utils;

namespace MAP_Lab7.Controller
{
    class Validator
    {
        private Repository<Student> repo;

        public Validator(Repository<Student> repo)
        {
            this.repo = repo;
        }

        private bool studentExists(int id)
        {
            MAP_Lab7.Repository.Stack<Student> students = repo.getAllElements();
            while (!students.isEmpty())
            {
                Student currStudent;
                currStudent = students.pop();
                if (currStudent.getID() == id)
                    return true;
            }
            return false;
        }

        private bool nameIsEmpty(String name)
        {
            if (name.Length < 1) return true;
            else return false;
        }

        public void validateStudent(Student s)
        {
            if (this.nameIsEmpty(s.getName()))
                throw new MyException("Name cannot be empty!\n");
            if (this.studentExists(s.getID()))
                throw new MyException("Student id already exists!\n");
        }
    }
}
