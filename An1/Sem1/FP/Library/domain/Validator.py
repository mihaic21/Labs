'''
Created on Nov 15, 2012

@author: mihai
'''
import re

class LibraryError(Exception):
    """
    Custom exception for the library application
    """
    
    def __init__(self, message):
        """
        Constructor
        message is a string containing the error message
        O(1)
        """
        self.__message = message
        
    def getMessage(self):
        """
        Returns the error message
        O(1)
        """
        return self.__message

class Validator:
    """
    Validator class
    Contains both the Book Validator and the Client Validator
    """
    #---Book validator
    def __validateAuthor(self, author):
        """
        Private method that validates the author of a book
        author is a string
        Raises a LibraryError if the author is empty
        O(1)
        """
        if author == "":
            raise LibraryError("Author can not be empty")
    
    def __validateTitle(self, title):
        """
        Private method that validates the title of a book
        title is a string
        Raises a LibraryError if the title is empty
        O(1)
        """
        if title == "":
            raise LibraryError("Title can not be empty")
        
    def validateBook(self, book):
        """
        Public method called to validate a book
        book is an instance of a Book
        Returns a list of error messages
        O(1) 
        """
        errorList = list()
        try:
            self.__validateAuthor(book.getAuthor())
        except LibraryError as error:
            errorList.append(error.getMessage())
            
        try:
            self.__validateTitle(book.getTitle())
        except LibraryError as error:
            errorList.append(error.getMessage())
    
        return errorList
    #----Client validator
    def __validateCnp(self, clientList, cnp):
        """
        Private method that validates a cnp
        clintList is an instance of a ClientList
        cnp is a string
        Raises a LibraryError if: the cnp is empty or contains letters or if the cnp already
        exists in the list
        Best case O(1)
        Worst case O(n)
        Average case O(n)
        """
        if cnp == "":
            raise LibraryError("Cnp can not be empty")
        letter = re.search("\D", cnp)
        if letter:
            raise LibraryError("Cnp can only contain digits")
        for clientInList in clientList:
            if clientInList.getCnp() == cnp:
                raise LibraryError("Cnp is already used")
            
    def __validateName(self, name):
        """
        Private method that validates a name
        name is a string
        Raises a LibraryError if the name is empty
        O(1)
        """
        if name == "":
            raise LibraryError("Name can not be empty")
                
    def validateClient(self, clientList, client):
        """
        Public method that validates a client
        clienList is an instance of ClientList
        client is an instance of Client
        Returns a list of error messages
        O(1)
        """
        errorList = list()
        try:
            self.__validateName(client.getName())
        except LibraryError as error:
            errorList.append(error.getMessage())
            
        try:
            self.__validateCnp(clientList, client.getCnp())
        except LibraryError as error:
            errorList.append(error.getMessage())
            
        return errorList