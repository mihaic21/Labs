using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab7.Repository;
using MAP_Lab7.Utils;
using MAP_Lab7.Model;
using System.IO;


namespace MAP_Lab7.Controller
{
    class StudentController
    {
        private Repository<Student> repo;
        private Validator val;

        public StudentController(Repository<Student> repo, Validator val)
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

        public static String elementsFromStack<T>(MAP_Lab7.Repository.Stack<T> stack)
        {
            MAP_Lab7.Repository.Stack<T> copy = stack.copy();
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

        public int numberOfStudentsGreaterThan(Student student)
        {
            Repository.Stack<Student> allStudents = this.repo.getAllElements();
            int number = 0;

            while (!allStudents.isEmpty())
                try
                {
                    Model.Comparable<Student> comparableStudent = allStudents.pop();
                    if (comparableStudent.isGreaterThan(student))
                        number++;
                }
                catch (MyException ex)
                {
                    System.Console.Out.WriteLine(ex.getMessage());
                }
                    
            return number;
        }

        public String getAllStudents()
        {
            return StudentController.elementsFromStack(this.repo.getAllElements());
        }

        public String saveStudentsInFile(String fileName)
        {
            try
            {
                repo.writeToFile(fileName);
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
            return "Students saved in file successfully!";
        }

        public String readFromFile(String fileName)
        {
            try
            {
                StreamReader sr = new StreamReader(fileName);
                String line;
                String[] tokens;
                Repository.Stack<Student> newStack = new Repository.Stack<Student>();

                while ((line = sr.ReadLine()) != null)
                {
                    tokens = line.Split(new String[] {" "}, StringSplitOptions.None);
                    //System.Console.Out.WriteLine(tokens[0] + "\n" + tokens[1] + "\n" + tokens[2]);

                    if ("Student".Equals(tokens[0]))
                        newStack.push(new Student(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3])));
                    else if ("UndergraduateStudent".Equals(tokens[0]))
                        newStack.push(new UndergraduateStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), Convert.ToInt32(tokens[4])));
                    else if ("GraduateStudent".Equals(tokens[0]))
                        newStack.push(new GraduateStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), tokens[4], Convert.ToInt32(tokens[5]), Convert.ToInt32(tokens[6])));
                    else if ("PhDStudent".Equals(tokens[0]))
                        newStack.push(new PhDStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), tokens[4], tokens[5], Convert.ToInt32(tokens[6])));
                    else
                        throw new MyException("Error reading from file!");
                }

                this.repo.replaceContent(newStack);

                //Repository<Student> repo = new Repository<Student>(newStack);
                //this.repo = repo;
            }
            catch (MyException ex)
            {
                return ex.getMessage();
            }
            catch (IOException)
            {
                return "Error reading from file!";
            }
            catch (FormatException)
            {
                return "Invalid format in file!";
            }
            return "Students read from file successfully!";
        }

    }
}
