'''
Created on Jan 25, 2013

@author: Mihai
'''
from Domain.Domain import Student, Lab
from Domain.Validator import Validator


class StudentRepository:
    
    def __init__(self,fName):
        self.__fName = fName
        self.validator = Validator()
        self.studentList = list()
        try:
            self.__studentFile = open (self.__fName,"r")
        except IOError:
            self.__studentFile = open (self.__fName,"w")
            self.__studentFile.close()
            self.__studentFile = open (self.__fName,"r")
        self.readFile()
        
        
    def readFile(self):
        line = "nonempty"
        while line != "":
            line = self.__studentFile.readline()
            if line != "":
                elements = line.split(",")
                student = Student(int(elements[0].strip()),elements[1].strip())
                self.studentList.append(student)
        self.__studentFile.close()
        
    def searchStudentId(self,id):
        for student in self.studentList:
            if student.getId() == id:
                return student
        return False



class LabRepository:
    
    def __init__(self,fName):
        self.__fName = fName
        self.validator = Validator()
        self.labList = list()
        try:
            self.__labFile = open (self.__fName,"r")
        except IOError:
            self.__labFile = open (self.__fName,"w")
            self.__labFile.close()
            self.__labFile = open (self.__fName,"r")
        self.readFile()
        
    def readFile(self):
        line = "nonempty"
        while line != "":
            line = self.__labFile.readline()
            if line != "":
                elements = line.split(",")
                lab = Lab (int(elements[0].strip()), int(elements[1].strip()), elements[2].strip())
                self.labList.append(lab)
        self.__labFile.close()
    
    def storeLab(self,studentId,labNumber,problemNumber):
        self.__labFile = open (self.__fName,"w")
        lab = Lab (studentId,labNumber,problemNumber)
        self.labList.append(lab)
        for thelab in self.labList:
            self.__labFile.write(str(thelab.getStudentId()) + "," + str(thelab.getLabNumber()) + "," + thelab.getProblemNumber() + "\n")
        self.__labFile.close()
    
    def findStudentActivities(self,studentId):
        studentLabList=list()
        for lab in self.labList:
            if lab.getStudentId() == studentId:
                studentLabList.append(lab)
        if len(studentLabList) == 0:
            return False
        else:
            return studentLabList
