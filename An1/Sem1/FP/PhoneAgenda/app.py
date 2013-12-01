'''
Created on 29.01.2013

@author: mihai_000
'''
from Repository.Repository import Repository
from UI.Console import Console
from UI.Controller import Controller

if __name__ == '__main__':
    agendaRepository = Repository ("contacts.txt")
    agendaController = Controller (agendaRepository)
    agendaConsole = Console (agendaController)
    agendaConsole.run()