using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using MAP_Lab4.Repository;
using MAP_Lab4.Domain;

namespace MAP_Lab4.Controller
{
    class StudentController
    {
        private StudentRepository repo;
        private Validator val;

        public StudentController(StudentRepository repo, Validator val)
        {
            this.repo = repo;
            this.val = val;
        }

        public String addStudent(String name, int id, int grade)
        {        
         /*
         * Asks validator if the student id is already used
         * returns string if so
         * if not, repository is called and student object is added
         * 
         * pre cond: string name, int id, int grade (of the student)
         * post cond: string with status message
         */

            if (this.val.nameIsEmpty(name))
                return "Name cannot be empty!";
            if (this.val.studentExists(id))
                return "Student id already exists!";
            Student s = new Student(name, id, grade);
            this.repo.addStudent(s);
            return "Student added successfully!";
        }

        public String getAllStudents()
        {        
         /*
         * pre cond: -
         * post cond: a string with all the students
         *          or a message if no students have been added
         *
         */

            String studentList = "";
            Student student;
            if (repo.getStackSize() < 0)
                return "No students!";
            Stack newStack = new Stack(this.repo.getStackCapacity());
            while (this.repo.getStackSize() > -1)
            {
                student = this.repo.getStudent();
                studentList += student.toString() + "\n";
                newStack.push(student);
            }

            while (newStack.getStackSize() > -1)
                this.repo.addStudent(newStack.pop());
            return studentList;
        }

        public String deleteStudentsUntilGrade(int grade)
        {      
         /*
         * pre cond: int grade (the limit grade)
         * post cond: specific string
         * 
         * deletes all students until 'grade' is found
         */

            Stack newStack = new Stack(this.repo.getStackCapacity());
            Student student;
            while (this.repo.getStackSize() > -1)
            {
                student = this.repo.getStudent();
                if (student.getGrade() == 10)
                {
                    this.repo.addStudent(student);
                    return "Students deleted successfully!";
                }
                newStack.push(student);
            }
            return "No students with the grade 10 found; all students have been deleted!";
        }

        public int getNoOfStudents()
        {        
         /*
         * pre cond: -
         * post cond: int with the number of students
         * 
         * gets the stack size
         */

            return this.repo.getStackSize() + 1;
        }

    }
}
