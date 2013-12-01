'''
Created on 22.02.2013

@author: mihai_000
'''

class Book:
    
    def __init__(self,title,author,year,publisher):
        self.__title = title
        self.__author = author
        self.__year = year
        self.__publisher = publisher
        
    def getTitle(self):
        return self.__title
    
    def getAuthor (self):
        return self.__author
    
    def getYear(self):
        return self.__year
    
    def getPublisher(self):
        return self.__publisher
    
    def setYear(self,year):
        self.__year = year
    
    def __str__(self):
        return self.getTitle() + self.getAuthor() + self.getYear() + self.getPublisher()