using MAP_Lab10.Model;
using MAP_Lab10.Repository;
using MAP_Lab10.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MAP_Lab10.Controller
{
    class Validator
    {
        private RepoInterface<Student> repo;

        public Validator(RepoInterface<Student> repo)
        {
            this.repo = repo;
        }

        private bool studentExists(int id)
        {
            IDictionary<int, Student> students = repo.getAllElements();

            foreach (Student student in students.Values)
                if (student.getID() == id)
                    return true;

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
