'''
Created on Jan 25, 2013

@author: Mihai
'''

class LabController:
    
    def __init__(self,studentRepository,labRepository):
        self.__studentRepository=studentRepository
        self.__labRepository=labRepository
        
    def showStudents(self):
        return self.__studentRepository.studentList
    
    def findStudentById (self,id):
        return self.__studentRepository.searchStudentId(id)
    
    def assignLab(self,studentId,labNumber,problemNumber):
        ok=False
        for student in self.__studentRepository.studentList:
            if student.getId() == studentId:
                ok=True
        if ok == False:
            return "There are no students with given ID"
        for lab in self.__labRepository.labList:
            if lab.getStudentId() == studentId and lab.getLabNumber() == labNumber:
                return "This student already has a lab problem for given lab number"
        self.__labRepository.storeLab(studentId,labNumber,problemNumber)
        return "Lab successfully added"
    
    def showStudentActivities(self,studentId):
        return self.__labRepository.findStudentActivities(studentId)
    
    def showStudentsForLab(self,labNumber):
        studentsLabList = list()
        finalList = list()
        for lab in self.__labRepository.labList:
            if lab.getLabNumber() == labNumber:
                studentsLabList.append(lab.getStudentId())
        if len(studentsLabList) == 0:
            return False
        for studentId in studentsLabList:
            for student in self.__studentRepository.studentList:
                if student.getId() == studentId:
                    finalList.append(student)
        return finalList