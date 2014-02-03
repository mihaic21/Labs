using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MAP_Lab10.Repository;
using MAP_Lab10.Model;
using System.Collections;
using MAP_Lab10.Utils;
using System.IO;

namespace MAP_Lab10.Controller
{
    class Controller
    {
        private Validator val;
        private RepoInterface<Student> repo;

        public Controller(RepoInterface<Student> repo, Validator val)
        {
            this.repo = repo;
            this.val = val;
        }

        public void addStudent(int id, String name, int grade)
        {
            Student s = new Student(id, name, grade);
            this.val.validateStudent(s);
            repo.addElement(s);
        }

        public void addStudent(int id, String name, int grade, int grade2)
        {
            Student s = new UndergraduateStudent(id, name, grade, grade2);
            this.val.validateStudent(s);
            repo.addElement(s);
        }

        public void addStudent(int id, String name, int grade, String supervisor, int grade2, int grade3)
        {
            Student s = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
            this.val.validateStudent(s);
            repo.addElement(s);
        }

        public void addStudent(int id, String name, int grade, String supervisor, String thesis, int grade2)
        {
            Student s = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
            this.val.validateStudent(s);
            repo.addElement(s);
        }

        public static ArrayList elementsFromDict<T>(IDictionary<int, T> dict)
        {
            ArrayList strings = new ArrayList();

            foreach (T element in dict.Values)
            {
                strings.Add(element.ToString());
            }
            return strings;
        }

        public ArrayList getAllStudents()
        {
            return Controller.elementsFromDict(this.repo.getAllElements());
        }

        public IList<Student> getAllStudentObjects()
        {
            IDictionary<int, Student> students = this.repo.getAllElements();
            List<Student> list = new List<Student>();

            foreach (Student student in students.Values)
            {
                list.Add(student);
            }

            return list;
        }


        public void readFromFile(String fileName)
        {
            StreamReader sr = new StreamReader(fileName);
            String line;
            String[] tokens;
            IDictionary<int, Student> newDict = new Dictionary<int, Student>();

            while ((line = sr.ReadLine()) != null)
            {
                tokens = line.Split(new String[] { " " }, StringSplitOptions.None);
                //System.Console.Out.WriteLine(tokens[0] + "\n" + tokens[1] + "\n" + tokens[2]);

                if ("Student".Equals(tokens[0]))
                    newDict.Add(Convert.ToInt32(tokens[1]), new Student(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3])));
                else if ("UndergraduateStudent".Equals(tokens[0]))
                    newDict.Add(Convert.ToInt32(tokens[1]), new UndergraduateStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), Convert.ToInt32(tokens[4])));
                else if ("GraduateStudent".Equals(tokens[0]))
                    newDict.Add(Convert.ToInt32(tokens[1]), new GraduateStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), tokens[4], Convert.ToInt32(tokens[5]), Convert.ToInt32(tokens[6])));
                else if ("PhDStudent".Equals(tokens[0]))
                    newDict.Add(Convert.ToInt32(tokens[1]), new PhDStudent(Convert.ToInt32(tokens[1]), tokens[2], Convert.ToInt32(tokens[3]), tokens[4], tokens[5], Convert.ToInt32(tokens[6])));
                else
                    throw new MyException("Error reading from file!");
            }

            repo.replaceContent(newDict);
            sr.Close();
        }

        public void saveStudentsInFile(String fileName)
        {
            repo.writeToFile(fileName);
        }


    }
}
