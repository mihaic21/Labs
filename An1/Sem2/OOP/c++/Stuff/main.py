'''
Created on Jul 22, 2013

@author: mihai
'''


class Book:
    def __init__(self,name):
        self.name = name

    def getName(self):
        return self.name
    
a = Book("namesadfdag")
print a