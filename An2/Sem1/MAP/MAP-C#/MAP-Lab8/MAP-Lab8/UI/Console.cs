using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace MAP_Lab8.UI
{
    class Console
    {
        Controller.Controller ctrl;
        
        private String command;
        private String menu =
            "\n"
            + "[1] Add student\n"
            + "[2] Delete students until grade 10 is found\n"
            + "[3] Print students\n"
            + "[4] Print number of students\n"
            + "[5] Save students in text file\n"
            + "[6] Read students from text file\n\n"
            + "[x] Exit\n\n";

        private String addStudentMenu =
            "\n"
            + "[1] Add Regular Student\n"
            + "[2] Add Undergraduate Student\n"
            + "[3] Add Graduate Student \n"
            + "[4] Add PhD Student\n\n";

        public Console(Controller.Controller ctrl)
        {
            this.ctrl = ctrl;
            this.run();
        }

        public void run()
        {
            while (true)
            {
                System.Console.Out.WriteLine(menu);
                command = System.Console.In.ReadLine();
                if ("1".Equals(command)) addStudent();
                else if ("2".Equals(command)) deleteStudents();
                else if ("3".Equals(command)) printStudents();
                else if ("4".Equals(command)) printNoOfStudents();
                else if ("5".Equals(command)) saveStudents();
                else if ("6".Equals(command)) importStudents();
                else if ("x".Equals(command)) System.Environment.Exit(0);
                else System.Console.Out.WriteLine("Invalid command!");
            }
        }

        private void importStudents()
        {
            System.Console.Out.WriteLine("Give file name: ");
            String fileName = System.Console.In.ReadLine();

            System.Console.Out.WriteLine(ctrl.readFromFile(fileName));
        }

        private void saveStudents()
        {
            System.Console.Out.WriteLine("Give file name: ");
            String fileName = System.Console.In.ReadLine();

            System.Console.Out.WriteLine(ctrl.saveStudentsInFile(fileName));
        }

        private void addStudent()
        {
            System.Console.Out.WriteLine(this.addStudentMenu);
            command = System.Console.In.ReadLine();
            if ("1".Equals(command)) this.addRegularStudent();
            else if ("2".Equals(command)) this.addUndergraduateStudent();
            else if ("3".Equals(command)) this.addGraduateStudent();
            else if ("4".Equals(command)) this.addPhDStudent();
            else System.Console.Out.WriteLine("Invalid command!");

        }

        private void addPhDStudent()
        {
            System.Console.Out.WriteLine("Student name: ");
            String name = System.Console.In.ReadLine();
            int id, grade, grade2;
            try
            {
                System.Console.Out.WriteLine("Student id: ");
                id = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade: ");
                grade = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student supervisor: ");
                String supervisor = System.Console.In.ReadLine();
                System.Console.Out.WriteLine("Student thesis: ");
                String thesis = System.Console.In.ReadLine();
                System.Console.Out.WriteLine("Student grade2: ");
                grade2 = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine(this.ctrl.addStudent(id, name, grade, supervisor, thesis, grade2));
            }
            catch (FormatException)
            {
                System.Console.Out.WriteLine("Invalid id/grade format!");
            }
        }

        private void addGraduateStudent()
        {
            System.Console.Out.WriteLine("Student name: ");
            String name = System.Console.In.ReadLine();
            int id, grade, grade2, grade3;
            try
            {
                System.Console.Out.WriteLine("Student id: ");
                id = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade: ");
                grade = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade2: ");
                grade2 = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade3: ");
                grade3 = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student supervisor: ");
                String supervisor = System.Console.In.ReadLine();
                System.Console.Out.WriteLine(this.ctrl.addStudent(id, name, grade, supervisor, grade2, grade3));
            }
            catch (FormatException)
            {
                System.Console.Out.WriteLine("Invalid id/grade format!");
            }
        }

        private void addUndergraduateStudent()
        {
            System.Console.Out.WriteLine("Student name: ");
            String name = System.Console.In.ReadLine();
            int id, grade, grade2;
            try
            {
                System.Console.Out.WriteLine("Student id: ");
                id = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade: ");
                grade = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade2: ");
                grade2 = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine(this.ctrl.addStudent(id, name, grade, grade2));
            }
            catch (FormatException)
            {
                System.Console.Out.WriteLine("Invalid id/grade format!");
            }
        }

        private void addRegularStudent()
        {
            System.Console.Out.WriteLine("Student name: ");
            String name = System.Console.In.ReadLine();
            int id, grade;
            try
            {
                System.Console.Out.WriteLine("Student id: ");
                id = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine("Student grade: ");
                grade = Convert.ToInt32(System.Console.In.ReadLine());
                System.Console.Out.WriteLine(this.ctrl.addStudent(id, name, grade));
            }
            catch (FormatException)
            {
                System.Console.Out.WriteLine("Invalid id/grade format!");
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
