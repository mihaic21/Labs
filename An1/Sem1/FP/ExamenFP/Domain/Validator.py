'''
Created on 03.02.2013

@author: mihai_000
'''

class TranslationError (Exception):
    """
    Extension of the class Exception
    """
    
    def __init__(self,errorList):
        """
        constructor
        errorList - list
        """
        self.__errorList = errorList
        
    def getErrorList(self):
        """
        return the list of errors
        """
        return self.__errorList
    
class Validator:
    """
    class for validating translations
    """
    
    def __validateSLang(self,translation):
        """
        validate source language
        if not valid, raise TranslationError
        """
        if translation.getSLang() not in ["En","Ro","Fr"]:
            raise TranslationError ("Invalid input language!")
        
    def __validateDLang(self,translation):
        """
        validate destination language
        if not valid, raise TranslationError
        """
        if translation.getDLang() not in ["En","Ro","Fr"]:
            raise TranslationError ("Invalid output language!")
        
    def __validateSameLang(self,translation):
        """
        check if source language and destination language are different
        if not valid, raise TranslationError
        """
        if translation.getSLang() == translation.getDLang():
            raise TranslationError ("Input language must be different than output language")
        
    def __validateWord(self,translation):
        """
        validate source word
        if not valid, raise TranslationError
        """
        if len(translation.getWord()) == 0:
            raise TranslationError ("Word cannot be empty")
    
    def __validateTranslationWord(self,translation):
        """
        validate translation word
        if not valid, raise TranslationError
        """
        if len(translation.getTransWord()) == 0:
            raise TranslationError ("Translation cannot be empty")
        
        
        
           
    def validateTranslation(self,translation):
        """
        Validate a translation
        
        translation - object of class Translation
        
        errorList - list of errors
        
        after applying all validate functions, return the errorList
        
        """
        
        errorList = list()
        
        try:
            self.__validateSLang(translation)
        except TranslationError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateDLang(translation)
        except TranslationError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateSameLang(translation)
        except TranslationError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateWord(translation)
        except TranslationError as error:
            errorList.append(error.getErrorList())
            
        try:
            self.__validateTranslationWord(translation)
        except TranslationError as error:
            errorList.append(error.getErrorList())        
        
        return errorList