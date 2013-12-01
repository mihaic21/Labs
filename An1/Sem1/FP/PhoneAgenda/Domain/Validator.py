'''
Created on 29.01.2013

@author: mihai_000
'''


class ContactError (Exception):
    
    def __init__(self,errorList):
        self.__errorList = errorList
        
    def getErrorList(self):
        return self.__errorList



class Validator:
    
        
    def __validateName(self,name):
        if len(name) == 0:
            raise ContactError ("Name cannot be empty")
    
    def __validateGroup(self,group):
        if group not in ("Friends","Family","Others"):
            raise ContactError ("Invalid group")
    
    def __validatePhoneNr(self,phoneNr):
        if len(phoneNr) == 0:
            raise ContactError ("Phone number cannot be empty")
        try:
            number = int(phoneNr)
        except:
            raise ContactError ("Phone number must contain only numbers") 
    
    def validateContact(self,contact):
        errorList = list()
        
        try:
            self.__validateName(contact.getName())
        except ContactError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateGroup(contact.getGroup())
        except ContactError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validatePhoneNr(contact.getPhoneNr())
        except ContactError as error:
            errorList.append(error.getErrorList())
            
        return errorList
       

