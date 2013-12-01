'''
Created on 23.02.2013

@author: mihai_000
'''

class Translation:
    """
    The class that defines a translation object
    """
    
    def __init__(self,sLang,word,dLang,transWord):
        """
        Constructor
        
        sLang - string, source language
        word - string, source word
        dLang - string, destination language
        transWord - string, translated word
        
        """
        
        self.__sLang = sLang
        self.__word = word
        self.__dLang = dLang
        self.__transWord = transWord
        
    def getSLang(self):
        """
        return the source language of translation
        """
        return self.__sLang
    
    def getWord(self):
        """
        return the source word of translation
        """
        return self.__word
    
    def getDLang(self):
        """
        return the destination language of translation
        """
        return self.__dLang
    
    def getTransWord(self):
        """
        return the translated word of translation
        """
        return self.__transWord

