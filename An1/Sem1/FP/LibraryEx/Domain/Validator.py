'''
Created on 22.02.2013

@author: mihai_000
'''


class BookError (Exception):
    
    def __init__(self,errorList):
        self.__errorList = errorList
        
    def getErrorList(self):
        return self.__errorList
    
    
class Validator:
        
    def __validateTitle(self,book):
        if len(book.getTitle()) == 0:
            raise BookError("Title cannot be empty")
        
    def __validateAuthor(self,book):
        if len(book.getAuthor()) == 0:
            raise BookError("Author name cannot be empty")
        
    def __validateYear(self,book):
        try:
            theYear = int(book.getYear())
            book.setYear(theYear)
        except:
            raise BookError("Year must be an integer number")
        if book.getYear() < 0:
            raise BookError("Year must be greater than 0")
        
    def __validatePublisher(self,book):
        if len(book.getPublisher()) == 0:
            raise BookError("Publisher name cannot be empty")
        
    def validateBook(self,book):
        errorList = list()
        try:
            self.__validateTitle(book)
        except BookError as error:
            errorList.append(error.getErrorList())
        
        try:
            self.__validateAuthor(book)
        except BookError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateYear(book)
        except BookError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validatePublisher(book)
        except BookError as error:
            errorList.append(error.getErrorList())
            
        return errorList