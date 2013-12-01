'''
Created on 22.02.2013

@author: mihai_000
'''

class Console:
    
    def __init__(self,bookController):
        self.__bookController = bookController
    
    def __mainMenu(self):
        print \
        """
        
        [1] Add Book
        [2] View Books
        [3] Remove Book
        
        [x] Exit
        
        """
        
        command = raw_input ("Please input your command: ")
        if command == "x":
            exit()
        elif command == "1":
            self.__menuAddBook()
        elif command == "2":
            pass
        elif command == "3":
            pass
        else:
            print "Invalid command!"
    
    def __menuAddBook(self):
        title = raw_input ("Insert title of the book: ")
        author = raw_input ("Insert book author: ")
        year = raw_input ("Insert book year: ")
        publisher = raw_input ("Insert book publisher: ")
        print self.__bookController.addNewBook(title,author,year,publisher)
    
    
    def run(self):
        while True:
            self.__mainMenu()
        