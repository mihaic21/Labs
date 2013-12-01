'''
Created on Nov 25, 2012

@author: mihai
'''
from domain.Types import Book, Client
from repository.Storage import BookList, ClientList

class BookListInFile(BookList):
    """
    Book repository class which uses files for storage
    """
    
    def __init__(self, filename):
        """
        Constructor method
        Opens the file and loads the entries in memory
        filename is a string, the path to the file used for storage
        O(n)
        """
        BookList.__init__(self)
        self.__filename = filename
        try:
            self.__bookFile = open(self.__filename, "r")
        except IOError:
            # File does not exist. Create it.
            self.__bookFile = open(self.__filename, "w")
            self.__bookFile.close()
            self.__bookFile = open(self.__filename, "r")
        line = "Nonempty String"
        while (line != ""):
            line = self.__bookFile.readline()
            if (line == "" or line == "\n"):
                break
            details = line.split("|")
            book = Book(details[0].strip(), details[1].strip(), details[2].strip())
            if details[3] == "True":
                book.setBorrowed(True)
            book.setPopularity(int(details[4]))
            self.bookList.append(book)
        self.__bookFile.close()
        
    def __listToFile(self):
        """
        Private method that writes the list from memory to the file
        O(n)
        """
        self.__bookFile = open(self.__filename, "w")
        for bookInList in self.bookList:
            self.__bookFile.write(bookInList.getTitle() + "|" + bookInList.getAuthor() + "|" + bookInList.getDescription()+ "|" + \
                                  str(bookInList.isBorrowed()) + "|" + str(bookInList.getPopularity()) +  "\n")
        self.__bookFile.close()
    
    def addNewBook(self, title, author, description):
        """
        Public method that overwrites the one in the BookList
        Extra functionality: Appends the newly added Book to the file
        O(1)
        """
        self.bookFile = open(self.__filename, "a")
        book = Book(title, author, description)
        errorList = self.validator.validateBook(book)
        if errorList != []:
            return errorList
        self.bookList.append(book)
        self.bookFile.write(book.getTitle() + "|" + book.getAuthor() + "|" + book.getDescription() + "|" + \
                            "False" + "|" + "0" +"\n")
        self.bookFile.close()
    
    def removeBook(self, book, p):
        """
        Public method that overwrites the one in BookList
        Extra functionality: Writes the entire list to the file
        Best case O(1)
        Worst case O(n)
        Average case O(n) = (1/n) * 1 + (1/n) * 2 + ... + (1/n) * n = (1/n)(1+2+....+n) = (1/n)*((n(n+1))/2) = (n+1)/2 = O(n)
        """
        if self.bookList[p] == book:
            self.bookList.pop(p)
            self.__listToFile()
            return True
        else:
            ret = self.removeBook(book, p+1)
        if ret != True:
            return False
        else:
            return True
    
    def updateBook(self, oldTitle, oldAuthor, oldDescription, \
                   newTitle, newAuthor, newDescription):
        """
        Public method that overwrites the one in BookList
        Extra functionality: Writes the entire list to the file
        O(n)
        """
        book = Book(oldTitle, oldAuthor, oldDescription)
        newBook = Book(newTitle, newAuthor, newDescription)
        found = False
        for bookInList in reversed(self.bookList):
            if bookInList == book:
                found = True
                self.bookList[self.bookList.index(bookInList)] = newBook
        self.__listToFile()
        return found
    
    def lendBook(self, books, client):
        """
        Public method that overwrites the one in BookList
        Extra functionality: Writes the entire list to the file
        O(n)
        """
        for book in books:
            if not book.isBorrowed():
                book.setBorrowed(True)
                self.__listToFile()
                return book
        return False
    
    def returnBook(self, books, client):
        """
        Public method that overwrites the one in BookList
        Extra functionality: Writes the entire list to the file
        O(n)
        """
        for book in books:
            if book.isBorrowed():
                book.setBorrowed(False)
                self.__listToFile()
                return book
        return False
    
class ClientListInFile(ClientList):
    """
    Client repository class which uses files for storage
    """
    
    def __init__(self, filename):
        """
        Constructor method
        Opens the file and loads the entries in memory
        filename is a string, the path to the file used for storage
        O(n)
        """
        ClientList.__init__(self)
        self.__filename = filename
        try:
            self.__clientFile = open(filename, "r")
        except IOError:
            # File does not exist. Create it.
            self.__clientFile = open(filename, "w")
            self.__clientFile.close()
            self.__clientFile = open(filename, "r")
        line = "Nonempty String"
        while line != "":
            line = self.__clientFile.readline()
            if line == "" or line == "\n":
                break
            details = line.split("|")
            client = Client(details[0], details[1])
            if details[2] != "":
                borrowedBooks = details[2].split(";")
                for borrowedBook in borrowedBooks:
                    if borrowedBook != "":
                        bookDetails = borrowedBook.split()
                        book = Book(bookDetails[0], bookDetails[1], bookDetails[2])
                        client.addBorrowedBook(book)
            client.setActivity(int(details[3]))
            self.clientList.append(client)
        self.__clientFile.close()
    
    def __listToFile(self):
        """
        Private method that writes the list from memory to the file
        O(n)
        """
        self.__clientFile = open(self.__filename, "w")
        for client in self.clientList:
            self.__clientFile.write(client.getName() + "|" + client.getCnp() + "|")
            borrowedBooks = client.getBorrowedBooks()
            for borrowedBook in borrowedBooks:
                self.__clientFile.write(borrowedBook.getTitle() + " " + borrowedBook.getAuthor() + " " + \
                                        borrowedBook.getDescription() + ";")
            self.__clientFile.write("|")
            self.__clientFile.write(str(client.getActivity()) + "\n")
        self.__clientFile.close()
    
    def addNewClient(self, name, cnp):
        """
        Public method that overwrites the one in the ClientList
        Extra functionality: Appends the newly added Client to the file
        O(n)
        """
        client = Client(name, cnp)
        self.__clientFile = open(self.__filename, "a")
        errorList = self.validator.validateClient(self.clientList, client)
        if errorList != []:
            return errorList
        self.clientList.append(client)
        self.__clientFile.write(client.getName() + "|" + client.getCnp() + "|" + "|" + "0" + "\n")
        self.__clientFile.close()
    
    def removeClient(self, name, cnp):
        """
        Public method that overwrites the one in the ClientList
        Extra functionality: Appends the newly added Client to the file
        O(n)
        """
        client = Client(name, cnp)
        found = False
        for clientInList in reversed(self.clientList):
            if clientInList == client:
                found = True
                self.clientList.pop(self.clientList.index(clientInList))
        self.__listToFile()
        return found
    
    def updateClient(self, oldName, oldCnp, newName, newCnp):
        """
        Public method that overwrites the one in the ClientList
        Extra functionality: Appends the newly added Client to the file
        O(n)
        """
        client = Client(oldName, oldCnp)
        newClient = Client(newName, newCnp)
        found = False
        for clientInList in reversed(self.clientList):
            if clientInList == client:
                found = True
                self.clientList[self.clientList.index(clientInList)] = newClient
        self.__listToFile()
        return found
    
    def borrowBook(self, book, client):
        """
        Public method that overwrites the one in the ClientList
        Extra functionality: Appends the newly added Client to the file
        O(n)
        """
        client.addBorrowedBook(book)
        self.__listToFile()
        
    def returnBook(self, book, client):
        """
        Public method that overwrites the one in the ClientList
        Extra functionality: Appends the newly added Client to the file
        O(n)
        """
        client.removeBorrowedBook(book)
        self.__listToFile()