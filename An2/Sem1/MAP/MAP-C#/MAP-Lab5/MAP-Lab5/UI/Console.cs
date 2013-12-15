using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab5.Controller;

namespace MAP_Lab5.UI
{
    class Console
    {
        StudentController ctrl;
        private String menu =
            "\n"
            + "[1] Add student\n"
            + "[2] Delete students until grade 10 is found\n"
            + "[3] Print students\n"
            + "[4] Print number of students\n\n"
            + "[x] Exit\n\n";

        public Console(StudentController ctrl)
        {
            this.ctrl = ctrl;
        }

        public void run()
        {
            while (true)
            {
                System.Console.Out.WriteLine(menu);
                String command = System.Console.In.ReadLine();
                if ("1".Equals(command)) addStudent();
                else if ("2".Equals(command)) deleteStudents();
                else if ("3".Equals(command)) printStudents();
                else if ("4".Equals(command)) printNoOfStudents();
                else if ("x".Equals(command)) System.Environment.Exit(0);
                else System.Console.Out.WriteLine("Invalid command!");
            }
        }

        private void addStudent()
        {
            try
            {
                int id, grade;
                System.Console.Out.WriteLine("Student name: ");
                String name = System.Console.In.ReadLine();
                System.Console.Out.WriteLine("Student id: ");
                id = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade: ");
                grade = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine(this.ctrl.addStudent(name, id, grade));
            }
            catch (FormatException)
            {
                System.Console.Out.WriteLine("Invalid format! ");
            }
            catch (MAP_Lab5.Controller.StudentException ex)
            {
                System.Console.Out.WriteLine(ex.GetBaseException());
            }

        }

        private void deleteStudents()
        {
            System.Console.Out.WriteLine(this.ctrl.deleteStudentsUntilGrade(10));
        }

        private void printStudents()
        {
            System.Console.Out.WriteLine(this.ctrl.getAllStudents());
        }

        private void printNoOfStudents()
        {
            System.Console.Out.WriteLine("No of students: " + this.ctrl.getNoOfStudents());
        }

    }
}
