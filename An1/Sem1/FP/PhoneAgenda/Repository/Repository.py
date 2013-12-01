'''
Created on 29.01.2013

@author: mihai_000
'''
from Domain.Domain import Contact
from Domain.Validator import Validator

class Repository:
    
    def __init__(self,fName):
        self.__fileName = fName
        self.contactList = list()
        self.validator = Validator()
        try:       
            self.__contactsFile = open (self.__fileName,"r")
        except IOError:
            self.__contactsFile = open (self.__fileName,"w")
            self.__contactsFile.close()
            self.__contactsFile = open (self.__fileName,"r")
        self.readFile()
        
    def readFile(self):
        line = "nonempty"
        while line != "":
            line = self.__contactsFile.readline()
            if line != "":
                elements = line.split(",")
                contact = Contact (elements[0].strip(), elements[1].strip(), elements[2].strip(), elements[3].strip())
                self.contactList.append(contact)              
        self.__contactsFile.close()
        
    def storeContact(self,id,name,phoneNr,group):
        contact = Contact (id,name,phoneNr,group)
        errorList = self.validator.validateContact(contact)
        if len(errorList) != 0:
            return errorList
        self.contactList.append(contact)
        self.__contactsFile = open (self.__fileName,"w")
        for contact in self.contactList:
            self.__contactsFile.write(str(contact.getId()) + "," + contact.getName() + "," + contact.getPhoneNr() + "," + contact.getGroup() + "\n")
        self.__contactsFile.close()
        return "Contact added successfully!"
        


class RepositoryExport:
    
    def __init__(self,fName,contacts):
        self.__fileName = fName
        self.__exportFile = open (fName,"w")
        self.__contactList = contacts
        
    def exportContacts(self):
        for contact in self.__contactList:
            self.__exportFile.write(contact.getName() + " " + contact.getPhoneNr() + "\n")
        self.__exportFile.close()
        return "Contacts exported successfully!"
            