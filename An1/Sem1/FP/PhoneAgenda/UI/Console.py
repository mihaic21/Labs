'''
Created on 29.01.2013

@author: mihai_000
'''

class Console:

    def __init__(self,agendaController):
        self.__agendaController=agendaController
        
    def mainMenu(self):
        print \
        """
        
        [1] Add new contact
        [2] Search contact by name
        [3] Show all contacts for given group
        [4] Export all contacts for given group
        
        [x] Exit
        
        """
        command = raw_input("Please insert your choice: ")
        if command == "x":
            exit()
        elif command == "1":
            self.menuAddContact()
        elif command == "2":
            self.menuSearchContact()
        elif command == "3":
            self.menuSearchGroupMembers()
        elif command == "4":
            self.menuExportContacts()
        else:
            print "Invalid choice"

    def menuAddContact(self):
        id = raw_input ("Please enter contact ID: ")
        name = raw_input ("Please enter contact name: ")
        phoneNr = raw_input ("Please enter contact phone number: ")
        group = raw_input ("Please enter contact group: ")
        if self.__agendaController.searchContactByName(name) != False:
            print "Contact already exists"
        else:
            print str(self.__agendaController.addContact(id,name,phoneNr,group))
        
    def menuSearchContact(self):
        name = raw_input ("Please enter contact name: ")
        if self.__agendaController.searchContactByName(name) == False:
            print "There is no contact with given name"
        else:
            print str(self.__agendaController.searchContactByName(name))
            
    def menuSearchGroupMembers(self):
        group = raw_input ("Please insert group: ")
        if group in ["Family","Others","Friends"]:
            message = self.__agendaController.searchContactByGroup(group)
            if len(message) == 0:
                print "No contacts in given group"
            else:
                for contact in message:
                    print contact
        else:
            print "Invalid group"
            
    def menuExportContacts(self):
        group = raw_input ("Please insert group: ")
        fileName = raw_input ("Please insert export file name, without extension: ")
        fileName += ".txt"
        if group in ["Family","Others","Friends"]:
            message = self.__agendaController.searchContactByGroup(group)
            if len(message) == 0:
                print "No contacts in given group"
            else:
                print str(self.__agendaController.exportContacts(fileName,message))
        else:
            print "Invalid group"
            
            
    def run(self):
        while True:
            self.mainMenu()