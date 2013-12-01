'''
Created on 29.01.2013

@author: mihai_000
'''

class Contact:
    def __init__(self,id,name,phoneNr,group):
        self.__id = id
        self.__name = name
        self.__phoneNr = phoneNr
        self.__group = group
        
    def getId(self):
        return self.__id
    
    def getName(self):
        return self.__name
    
    def getPhoneNr(self):
        return self.__phoneNr
    
    def getGroup(self):
        return self.__group
    
    def __str__(self):
        return self.getId() + " " + self.getName() + " " + self.getPhoneNr() + " " + self.getGroup()
    
