'''
Created on Jan 25, 2013

@author: Mihai
'''

class StudentError(Exception):
    def __init__(self,errorList):
        self.__errorList=errorList
        
    def getErrorList(self):
        return self.__errorList
    
    
class Validator:
    
    def studentValidator(self,student):
        pass
    
    def labValidator(self,lab):
        pass