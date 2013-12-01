'''
Created on 23.02.2013

@author: mihai_000
'''

class Console:
    def __init__(self,transController):
        self.__transController = transController
        
    def mainMenu(self):
        print \
        """
        
        [1] Add word and corresponding translation
        [2] Translate text
        [3] Export translation from given file
        
        [x] Exit
        
        """
        command = raw_input("Please insert your command: ").strip()
        if command == "x":
            exit()
        elif command == "1":
            self.menuAddWord()
        elif command == "2":
            self.menuTranslateText()
        elif command == "3":
            self.menuExportTranslation()
        else:
            print "Invalid command"
  
    def menuAddWord(self):
        sLang = raw_input ("Insert word language: ")
        word = raw_input ("Insert word: ")
        dLang = raw_input ("Insert translation language: ")
        transWord = raw_input ("Insert translation: ")
        print str(self.__transController.addWord(sLang,word,dLang,transWord))
        
    def menuTranslateText(self):
        text = raw_input ("Insert text: ")
        sLang = raw_input ("Insert source language: ")
        dLang = raw_input ("Insert destination language: ")
        print self.__transController.translateText(text,sLang,dLang)
        
    def menuExportTranslation(self):
        sFile = raw_input ("Insert input file name: ")
        sLang = raw_input ("Insert input language: ")
        dFile = raw_input ("Insert output file name: ")
        dLang = raw_input ("Insert output language: ")
        print str(self.__transController.exportTranslation (sFile,sLang,dFile,dLang))

  
        
    def run(self):
        cont = True
        while cont:
            self.mainMenu()
