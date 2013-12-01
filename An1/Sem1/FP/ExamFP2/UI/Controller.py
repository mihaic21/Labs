'''
Created on 23.02.2013

@author: mihai_000
'''

from Domain.Domain import Translation
from Repository.Repository import RepositoryForText


class Controller:
    
    def __init__(self,transRepository,transValidator):
        """
        Constructor
        Takes parameters transRepository (object of class Repository) 
        and transValidator (object of class Validator)
        """
        self.__transRepository = transRepository
        self.__transValidator = transValidator
        
    def addWord(self,sLang,word,dLang,transWord):
        """
        Adds new word
        
        sLang - source language, string
        word - source word, string
        dLang - destination language, string
        transWord - translated work, string
        
        en - counter for english apparences
        fr - counter for french
        ro - counter for ro
        en,fr,ro - integer
        
        Checks if a word appears more than once
        
        validates the translation of given parameteres
        if not valid returns error list
        if valid then searches in translationList (from Repository) if the words already
        has a translation in given language
        returns messages if word already has translation, adds if not
        """
        translation = Translation (sLang,word,dLang,transWord)
        errorList = self.__transValidator.validateTranslation(translation)
        if len(errorList) != 0:
            return errorList
        en = 0
        fr = 0
        ro = 0
        for translation in self.__transRepository.translationList:
            if translation.getWord() == word and translation.getSLang() == sLang:
                if translation.getDLang() == "En":
                    en += 1
                elif translation.getDLang() == "Ro":
                    ro += 1
                elif translation.getDLang() == "Fr":
                    fr += 1
        if en == 1 and dLang == "En":
            return "A word cannot have 2 translations in the same destination language"
        if fr == 1 and dLang == "Fr":
            return "A word cannot have 2 translations in the same destination language"
        if ro == 1 and dLang == "Ro":
            return "A word cannot have 2 translations in the same destination language"
        return self.__transRepository.storeTranslation(sLang,word,dLang,transWord)
    
    def translateText(self,text,sLang,dLang):
        """
        Translates a text file given
        
        text - given text, string
        sLang - source language, string
        dLang - destination language, string
        
        result - translated string
        
        if text, sLang and dLang are valid then computes result
        return result
        """
        if text == "":
            return "Text cannot be empty"
        if sLang not in ["En","Ro","Fr"]:
            return "Invalid source language"
        if dLang not in ["En","Ro","Fr"]:
            return "Invalid destination language"
        if sLang == dLang:
            return "Source language and destination language must be different"
        result = ""
        thetext = text.split()
        for word in thetext:
            ok = False
            for theword in self.__transRepository.translationList:
                if theword.getWord() == word and theword.getSLang() == sLang and theword.getDLang() == dLang:
                    result += theword.getTransWord() + " "
                    ok = True
                    
            if ok == False:
                result += "{" + word + "} "
        return result
         
         
    def exportTranslation(self,sFile,sLang,dFile,dLang):
        """
        exports a translation to a given file
        
        sFile - source file name, string
        sLang - source language, string
        dFile - destination file name, string
        dLang - destination language, string
        
        import file - object of class RepositoryForText
        if invalid source file, return message
        else compute the result using the translateText function from above
        if error then return error
        else export file and return message
        
        """
        
        importFile = RepositoryForText()
        text = importFile.readsFile(sFile)
        if text == False:
            return "Inexistent source file"
        if len(dFile) == 0 or ".txt" not in dFile:
            return "Invalid destination file"
        result = self.translateText(text, sLang, dLang)
        if result in ["Text cannot be empty","Invalid source language","Invalid destination language","Source language and destination language must be different"]:
            return result
        exportFile = RepositoryForText()
        exportFile.storeText(result,dFile)
        return "Text translated and exported successfully!"
            
       
class test_Controller:
    
    def __init__(self,transRepository,transValidator):
        self.__transRepository = transRepository
        self.__transValidator = transValidator
        self.__transController = Controller (self.__transRepository,self.__transValidator)
        self.test_addWord()
        
    def test_addWord(self):
        assert self.__transController.addWord("a","apple","Fr","hahaha") != "Translation added successfully!"
        assert self.__transController.addWord("Ro","apple","Fr","hahaha") != "Invalid word!"
        
    def test_translateText(self):
        assert self.__transController.translateText("","","") == "Text cannot be empty"
        
    def test_exportTranslation(self):
        assert self.__transController.exportTranslation("lala","Ro","plecam.txt","Fr") == "Inexistent source file"       
             
