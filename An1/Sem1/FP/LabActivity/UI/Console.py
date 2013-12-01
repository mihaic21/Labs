'''
Created on Jan 25, 2013

@author: Mihai
'''


class Console:
    
    def __init__(self,labController):
        self.__labController=labController
        
    def mainMenu(self):
        print \
        """
        [1] Show all students
        [2] Find student by ID
        [3] Assign lab problem to student
        [4] Show lab activities for given student
        [5] Show all students with lab assignments for given lab
        
        [x] Exit
        
        """
        command=raw_input("Please insert your command: ")
        if command == "x":
            exit()
        elif command == "1":
            for student in self.__labController.showStudents():
                print student
        elif command == "2":
            self.menuFindStudent()
        elif command == "3":
            self.menuAssignLab()
        elif command == "4":
            self.menuShowActivitiesStudent()
        elif command == "5":
            self.menuShowStudentsLab()
        else:
            print "Invalid choice!"
    
    def menuFindStudent(self):
        try:
            id=int(raw_input("Please insert student ID: "))
            message = self.__labController.findStudentById(id)
            if message == False:
                print "There is no student with given ID"
            else:
                print message
        except ValueError:
            print "ID not valid"
    
    def menuAssignLab(self):
        try:
            id = int(raw_input("Please insert student ID: "))
            labno = int(raw_input("Please insert lab number: "))
            problemno = raw_input("Please insert problem number: ")
            print str (self.__labController.assignLab(id,labno,problemno))
        except ValueError:
            print "Student ID and lab number must be integer"
    
    def menuShowActivitiesStudent(self):
        try:
            id = int(raw_input ("Please insert student ID: "))
            if self.__labController.findStudentById(id) == False:
                print "There is no student with given ID"
            else:
                message = self.__labController.showStudentActivities(id)
                if message == False:
                    print "The given student has no lab activities assigned"
                else:
                    for lab in message:
                        print lab
        except ValueError:
            print "Student ID must be integer"
    
    def menuShowStudentsLab(self):
        try:
            labNumber = int(raw_input("Please insert lab number: "))
            message = self.__labController.showStudentsForLab(labNumber)
            if message == False:
                print "There are no students with given lab number"
            else:
                for student in message:
                    print student
        except ValueError:
            print "Lab number must be integer"
        
    def run(self):
        while True:
            self.mainMenu()
    