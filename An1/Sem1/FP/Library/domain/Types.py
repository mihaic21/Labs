'''
Created on Nov 1, 2012

@author: Mihai Costea
'''

class Book:
    """
    This class contains all the information needed to store books
    Properties: title (str), description (str), author (str), id (int, read only), borrowed (boolean)
    """
    __bookId = 0
    
    def __init__(self, title, author, description):
        """
        Constructor
        """
        self.setAuthor(author)
        self.setDescription(description)
        self.setTitle(title)
        self.__id = Book.__bookId
        self.__popularity = 0
        self.setBorrowed(False)
        Book.__bookId += 1
    
    def isBorrowed(self):
        """
        Gets the borrowed property
        O(1)
        """
        return self.__borrowed
    
    def setBorrowed(self, borrowed):
        """
        Sets the borrowed property and also increments the number of borrows
        borrowed can be True or False
        O(1)
        """
        self.__borrowed = borrowed
        if borrowed == True:
            self.__popularity += 1
    
    def setTitle(self, title):
        """
        Sets the title
        title is a string
        O(1)
        """
        self.__title = title 
    
    def getTitle(self):
        """
        Gets the title
        O(1)
        """
        return self.__title
    
    def setDescription(self, description):
        """
        Sets the description
        description is a string
        O(1)
        """
        self.__description = description
        
    def getDescription(self):
        """
        Gets the description
        O(1)
        """
        return self.__description
    
    def setAuthor(self, author):
        """
        Sets the author
        author is a string
        O(1)
        """
        self.__author = author
    
    def getAuthor(self):
        """
        Gets the author
        O(1)
        """
        return self.__author

    def getPopularity(self):
        """
        Gets the popularity of the book
        O(1)
        """
        return self.__popularity
    
    def setPopularity(self, popularity):
        """
        Sets the popularity of the book
        popularity is an int
        Only used with repository in file
        O(1)
        """
        self.__popularity = popularity
    
    def getId(self):
        """
        Gets the id
        O(1)
        """
        return self.__id
    
    def setId(self, newId):
        """
        Sets the id
        newId is an int
        SHOULD NOT BE CALLED FROM ANY OTHER PARTS OF THE APP EXCEPT TEST FUNCTIONS!!!
        O(1)
        """
        Book.__bookId = newId
    
    def __str__(self):
        return self.getTitle() + ", " + self.getAuthor() + ", " + self.getDescription() + \
            ", " + str(self.isBorrowed()) + ", " + str(self.getPopularity())
    
    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.getAuthor() == other.getAuthor() and self.getTitle() == other.getTitle() and \
                self.getDescription() == other.getDescription()
        else:
            return False

class Client:
    """
    This class contains all the information needed to store clients
    Properties: name (str), cnp (str), id (int, read only), borrowedBooks (list)
    """
    __clientId = 0
    def __init__(self, name, cnp):
        """
        Constructor
        """
        self.setName(name)
        self.setCnp(cnp)
        self.__Id = Client.__clientId
        self.__borrowedBooks = list()
        self.__activity = 0
        Client.__clientId += 1
    
    def setName(self, name):
        """
        Sets the name
        name is a string
        O(1)
        """
        self.__name = name
        
    def changeBorrowedBooks(self, borrowedBooks):
        """
        Replaces the borrowed books list with borrowedBooks
        borrowedBooks is a list of Books
        O(1)
        """
        self.__borrowedBooks = borrowedBooks
        
    def addBorrowedBook(self, borrowedBook):
        """
        Adds a book to the borrowed books list
        borrowedBook is an instance of a book
        Also increments the activity
        O(1)
        """
        self.__borrowedBooks.append(borrowedBook)
        self.__activity += 1
        
    def removeBorrowedBook(self, borrowedBook):
        """
        Removes a book from the borrowed books list
        borrowedBook is an instance of a book
        O(1)
        """
        self.__borrowedBooks.pop(self.__borrowedBooks.index(borrowedBook))
    
    def getBorrowedBooks(self):
        """
        Gets the borrowed books list
        O(1)
        """
        return self.__borrowedBooks
    
    def getName(self):
        """
        Gets the name
        O(1)
        """
        return self.__name
    
    def setCnp(self, cnp):
        """
        Sets the cnp
        cnp is a string and it must only contain digits
        O(1)
        """
        self.__cnp = cnp
    
    def getCnp(self):
        """
        Gets the cnp
        O(1)
        """
        return self.__cnp
    
    def getId(self):
        """
        Gets the id
        O(1)
        """
        return self.__id
    
    def setId(self, newId):
        """
        Sets the id
        newId is an int
        SHOULD NOT BE CALLED FROM ANY OTHER PARTS OF THE APP EXCEPT TEST FUNCTIONS!!!
        O(1)
        """
        Client.__clientId = newId
    
    def getActivity(self):
        """
        Gets the client's activity
        O(1)
        """
        return self.__activity
    
    def setActivity(self, activity):
        """
        Sets the client's activity
        activity is an int
        O(1)
        """
        self.__activity = activity
        
    def __str__(self):
        return self.getName() + ", " + self.getCnp() + ", " + str(self.getActivity())
    
    def __eq__(self, other):
        if isinstance(other, self.__class__):
            return self.getName() == other.getName() and self.getCnp() == other.getCnp()
        else:
            return False
    
