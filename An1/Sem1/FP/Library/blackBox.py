'''
Created on Dec 5, 2012

@author: mihai
'''
from repository.Storage import BookList

def blackBoxTest_updateBook():
    bookList = BookList()
    bookList.addNewBook("a", "a", "a")
    assert bookList.updateBook("a", "a", "a", "b", "b", "b") == True
    assert bookList.updateBook("a", "a", "a", "b", "b", "b") == False