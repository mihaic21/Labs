'''
Created on Jan 25, 2013

@author: Mihai
'''
from Repository.Repository import StudentRepository, LabRepository
from UI.Console import Console
from UI.Controller import LabController

if __name__ == '__main__':
    studentRepository=StudentRepository("student.txt")
    labRepository=LabRepository("labs.txt")
    labController=LabController(studentRepository,labRepository)
    labConsole=Console(labController)
    labConsole.run()