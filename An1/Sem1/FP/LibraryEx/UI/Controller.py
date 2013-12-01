'''
Created on 22.02.2013

@author: mihai_000
'''
from Domain.Domain import Book

class Controller:
    
    def __init__(self,bookRepository,bookValidator):
        self.__bookRepository = bookRepository
        self.__bookValidator = bookValidator
        
    def addNewBook(self,title,author,year,publisher):
        book = Book (title,author,year,publisher)
        if len(self.__bookValidator.validateBook(book)) != 0:
            return self.__bookValidator.validateBook(book)
        return self.__bookRepository.addNewBook(book)