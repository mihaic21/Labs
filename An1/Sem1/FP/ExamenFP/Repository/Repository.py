'''
Created on 03.02.2013

@author: mihai_000
'''
from Domain.Domain import Translation

class Repository:
    """
    Stores and reads translations from memory and file
    """
    
    def __init__(self,fName):
        """
        Constructor
        parameter fName - string, file name
        translationList - list where all the translations will be memorized
        try to open the file for reading
        if IOError, then create the file, close it and then reopen it with reading capabilities
        run function readFile
        """
        self.__fileName = fName
        self.translationList = list()
        try:
            self.__translationFile = open (self.__fileName,"r")
        except IOError:
            self.__translationFile = open (self.__fileName,"w")
            self.__translationFile.close()
            self.__translationFile = open (self.__fileName,"r")
        self.readFile()
        
        
    def readFile(self):
        """
        Reads translations from file and stores them into local memory
        
        line - string
        translation - object of class Translation
        """
        
        line = "nonempty"
        while line != "":
            line = self.__translationFile.readline()
            if line != "":
                elems = line.split(",")
                translation = Translation (elems[0].strip(), elems[1].strip(), elems[2].strip(), elems[3].strip())
                self.translationList.append(translation)
        self.__translationFile.close()
        
    def storeTranslation(self,sLang,word,dLang,transWord):
        """
        Stores translation of given parameters
        
        sLang - source language, string
        word - source word, string
        dLang - destination language, string
        transWord - translated word, string
        
        open file with writing capabilities
        write translations from memory to file, one on a row
        return message
        """
        trans = Translation (sLang,word,dLang,transWord)
        self.translationList.append(trans)
        self.__translationFile = open (self.__fileName,"w")
        for trans in self.translationList:
            self.__translationFile.write(str(trans.getSLang()) + "," + str(trans.getWord()) + "," + str(trans.getDLang()) + "," + str(trans.getTransWord()) + "\n")
        self.__translationFile.close()
        return "Translation added successfully!"  
    
      
                
   
class RepositoryForText:
    """
    A repository class made only for reading and writing strings
    """
         
    def readsFile (self,fileName):
        """
        fileName - string, name of a file
        try to open the file with reading capabilities
        if error, return false, else return the row on which the text is
        
        text = string
        file - the file of name fileName
        """
        try:
            file = open (fileName, "r") 
            text = file.readline()
            file.close()
            return text
        except IOError:
            return False
        
    def storeText(self,text,dFile):
        """
        Stores a given string in a given file
        
        text - string, the text we have to write in file
        dFile - string, name of the destination file
        """
        file = open (dFile,"w")
        file.write(text)
        file.close()
        
        