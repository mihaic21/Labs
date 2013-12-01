'''
Created on 22.02.2013

@author: mihai_000
'''
from Domain.Validator import Validator
from Repository.Repository import Repository
from UI.Console import Console
from UI.Controller import Controller

if __name__ == '__main__':
    bookRepository = Repository ("books.txt")
    bookValidator = Validator ()
    bookController = Controller (bookRepository,bookValidator)
    bookConsole = Console (bookController)
    bookConsole.run()