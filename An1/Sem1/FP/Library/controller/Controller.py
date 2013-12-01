'''
Created on Nov 1, 2012

@author: Mihai Costea
'''

from domain.Types import Book
from ui.Utilities import Utilities

class LibraryController:
    '''
    This is the controller class.
    All messages from the ui to the domain and vice-versa must pass through this controller.
    '''

    def __init__(self, bookList, clientList):
        '''
        Constructor
        '''
        self.__bookList = bookList
        self.__clientList = clientList
        self.__uiUtilities = Utilities()
    
    #------Books
    
    def searchBooks(self, searchQuery):
        """
        Searches for books with a given detail (title, author, description)
        searchQuery is a string
        Returns a list of books based on the search query
        O(n)
        """
        return self.__bookList.findBook(searchQuery)
    
    def searchClients(self, searchQuery):
        """
        Searches for clients with a given detail (name, cnp)
        searchClients is a string
        Returns a list of books based on the search query
        O(n)
        """
        return self.__clientList.findClient(searchQuery)
    
    def lendBook(self, bookTitle, bookAuthor, bookDescription, clientName, clientCnp):
        """
        Controller method to lend a book
        Returns "book not found" or "client not found" if the book/client was not found in the lists
        Returns "all books borrowed" if all the books with those details are borrowed
        Returns True if everything is ok
        O(n+m)
        """
        booksInList = self.__bookList.bookInList(bookTitle, bookAuthor, bookDescription)
        if booksInList == []:
            return "book not found"
        clientInList = self.__clientList.clientInList(clientName, clientCnp)
        if clientInList == False:
            return "client not found"
        book = self.__bookList.lendBook(booksInList, clientInList)
        if not book:
            return "all books borrowed"
        self.__clientList.borrowBook(book, clientInList)
        return True
            
    
    def returnBook(self, bookTitle, bookAuthor, bookDescription, \
                   clientName, clientCnp):
        """
        Controller method to return a book
        Returns "book not found" if the book with those details was not found in the list
        Returns "client not found" if the client with those details does not exist
        Returns "book is not borrowed" if the book with those details is not borrowed
        Returns True if everything is ok
        O(n+m)
        """
        booksInList = self.__bookList.bookInList(bookTitle, bookAuthor, bookDescription)
        if booksInList == []:
            return "book not found"
        clientInList = self.__clientList.clientInList(clientName, clientCnp)
        if clientInList == False:
            return "client not found"
        book = self.__bookList.returnBook(booksInList, clientInList)
        if not book:
            return False
        self.__clientList.returnBook(book, clientInList)
        return True
    
    def addNewBook(self, title, author, description):
        """
        Controller method to add a new book
        the parameters are all strings
        O(1)
        """
        return self.__bookList.addNewBook(title, author, description)

    def removeBook(self, title, author, description):
        """
        Controller Method to remove a book
        the parameters are all strings
        Returns False if the book was not found, True otherwise
        Best case O(1)
        Worst case O(n)
        Average case O(n)
        """
        book = Book(title, author, description)
        if not self.__bookList.removeBook(book, 0):
            return False
        return True
     
    def updateBook(self, oldTitle, oldAuthor, oldDescription, \
                   newTitle, newAuthor, newDescription):
        """
        Updates the details of a book from the list
        the parameters are all strings
        Returns False if the book is not found
        Returns True if everything is ok
        O(n)
        """
        if newTitle == "":
            newTitle = oldTitle
        if newAuthor == "":
            newAuthor = oldAuthor
        if newDescription == "":
            newDescription = oldDescription
        if not self.__bookList.updateBook(oldTitle, oldAuthor, oldDescription, \
                   newTitle, newAuthor, newDescription):
            return False
        return True
        
    def listBooks(self):
        """
        Prints out the entire list of books
        O(n)
        """
        self.__uiUtilities.printObjectList(self.__bookList)
        
    def orderBooks(self, direction):
        """
        Orders the book list by popularity ascending or descending.
        direction is a string "asc" or "desc"
        O(n)
        """
        self.__uiUtilities.printList(self.__bookList.orderBooks(direction))

    #------Clients

    def addNewClient(self, name, cnp):
        """
        Controller method to add a new client
        name and cnp are strings
        O(n)
        """
        return self.__clientList.addNewClient(name, cnp)

    def removeClient(self, name, cnp):
        """
        Private method to remove a client
        name and cnp are strings
        Returns False if the client was not found, True otherwise
        O(n)
        """
        if not self.__clientList.removeClient(name, cnp):
            return False
        return True

    def updateClient(self, oldName, oldCnp, newName, newCnp):
        """
        Updates the details of a client from the list
        All parameters are strings
        Returns False if the client was not found, True otherwise
        O(n)
        """
        if newName == "":
            newName = oldName
        if newCnp == "":
            newCnp = oldCnp
        if not self.__clientList.updateClient(oldName, oldCnp, newName, newCnp):
            return False
        return True     
            
    def listClients(self):
        """
        Prints out the entire list of clients
        O(n)
        """
        self.__uiUtilities.printObjectList(self.__clientList)
        
    def orderClients(self, direction):
        """
        Orders the client list by activity ascending or descending.
        direction is a string "asc" or "desc"
        O(n)
        """
        self.__uiUtilities.printList(self.__clientList.orderClients(direction))
    
    
        
    
    