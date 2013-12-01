'''
Created on Nov 1, 2012

@author: Mihai Costea
'''
from blackBox import blackBoxTest_updateBook
from controller.Controller import LibraryController
from controller.tests import test_controller
from repository.RepositoryInFiles import BookListInFile, ClientListInFile
from repository.Storage import BookList, ClientList
from ui.Console import Console

if __name__ == '__main__':
    
    #bookList = BookList()
    #clientList = ClientList()
    bookList = BookListInFile("books.dat")
    clientList = ClientListInFile("clients.dat")
    testBookList = BookList()
    testClientList = ClientList()
    test_controller(testBookList, testClientList)
    blackBoxTest_updateBook()
    libraryController = LibraryController(bookList, clientList)
    console = Console(libraryController)