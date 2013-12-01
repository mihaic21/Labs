'''
Created on 22.02.2013

@author: mihai_000
'''
from Domain.Domain import Book

class Repository:
    
    def __init__(self,fileName):
        self.__fileName = fileName
        self.bookList = list()
        try:
            self.__bookFile = open (self.__fileName, "r")
        except IOError:
            self.__bookFile = open (self.__fileName, "w")
            self.__bookFile.close()
            self.__bookFile = open (self.__fileName, "r")
        self.readFile()
    
    def readFile(self):
        line = "Nonempty"
        while len(line) != 0:
            line = self.__bookFile.readline()
            if len(line) != 0:
                elements = line.split(",")
                book = Book(elements[0].strip(), elements[1].strip(), elements[2].strip(), elements[3].strip())
                self.bookList.append(book)
        self.__bookFile.close()
        
    def addNewBook(self,book):
        self.bookList.append(book)
        self.__bookFile = open (self.__fileName, "w")
        for theBook in self.bookList:
            self.__bookFile.write(str(str(theBook.getTitle()) +","+ str(theBook.getAuthor()) +","+ str(theBook.getYear()) +","+ str(theBook.getPublisher()) +"\n"))
        self.__bookFile.close()
        return "Book added successfully!"  