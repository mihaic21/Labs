using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab6.Repository;
using MAP_Lab6.Model;
using MAP_Lab6.Utils;



namespace MAP_Lab6.Controller
{
    class StudentController
    {
        private StudentRepository<Student> repo;
        private Validator val;

        public StudentController(StudentRepository<Student> repo, Validator val)
        {
            this.repo = repo;
            this.val = val;
        }

        public String addStudent(int id, String name, int grade)
        {
            try
            {
                Student s = new Student(id, name, grade);
                val.validateStudent(s);
                repo.addElement(s);
                return "Student added successfully!";
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }

        }

        public String addStudent(int id, String name, int grade, int grade2)
        {
            try
            {
                Student s = new UndergraduateStudent(id, name, grade, grade2);
                val.validateStudent(s);
                repo.addElement(s);
                return "Student added successfully!";
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
        }

        public String addStudent(int id, String name, int grade, String supervisor, int grade2, int grade3)
        {
            try
            {
                Student s = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
                val.validateStudent(s);
                repo.addElement(s);
                return "Student added successfully!";
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
        }

        public String addStudent(int id, String name, int grade, String supervisor, String thesis, int grade2)
        {
            try
            {
                Student s = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
                val.validateStudent(s);
                repo.addElement(s);
                return "Student added successfully!";
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
        }

        public String deleteStudentsUntilGrade(int grade)
        {
            try
            {
                Student student;
                while (this.repo.getNoOfElements() > 0)
                {
                    student = (Student)this.repo.getTopElement();
                    if (student.average() == grade)
                        return "Students deleted successfully!";
                    else
                        this.repo.removeElement(student);
                }
                return "No students with the grade 10 found; all students have been deleted!";
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
        }

        public int getNoOfStudents()
        {
            return this.repo.getNoOfElements();
        }

        public static String elementsFromStack<T>(Repository.Stack<T> stack)
        {
            Repository.Stack<T> copy = stack.copy();
            String result = "\n";

            while (!copy.isEmpty())
                try
                {
                    T element = copy.pop();
                    result += element.ToString();
                    result += "\n";
                }
                catch (MyException ex)
                {
                    return ex.getMessage();
                }

            return result;
        }


        public static void moveElements<W, T>(Repository.Stack<W> source, Repository.Stack<T> destination) where W : T
        {
            Repository.Stack<T> temp = new Repository.Stack<T>();
            try
            {
                while (!source.isEmpty())
                    temp.push(source.pop());
                while (!temp.isEmpty())
                    destination.push(temp.pop());
            }
            catch (MyException e)
            {
                System.Console.WriteLine(e.getMessage());
            }
        }

        public String getAllStudents()
        {
            return StudentController.elementsFromStack(this.repo.getAllElements());
        }
    }
}
