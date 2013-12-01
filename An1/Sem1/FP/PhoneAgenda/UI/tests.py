'''
Created on 29.01.2013

@author: mihai_000
'''
from UI.Controller import Controller

class test_Controller:
    
    def __init__(self,agendaRepository):
        self.__controller = Controller (agendaRepository)
        self.test_addContact()
        self.test_searchContactByName()
        self.test_searchContactByGroup()
        self.test_exportContacts()
        
    def test_addContact(self):
        assert self.__controller.addContact("1", "Marian", "13", "Friends") == "Contact added successfully!"
        assert self.__controller.addContact("2", "dada", "12", "Others") == "Contact added successfully!"
    
    def test_searchContactByName(self):
        assert self.__controller.searchContactByName("izabel") == False
        assert self.__controller.searchContactByName("Marian") != None
        
    def test_searchContactByGroup(self):
        assert self.__controller.searchContactByGroup("Friends") != None
        

        
        