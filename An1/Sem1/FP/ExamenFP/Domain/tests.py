'''
Created on 03.02.2013

@author: mihai_000
'''
from Domain import Translation
from Validator import Validator

def test_translation():
    translation = Translation ("En","apple","Fr","hahaha")
    assert translation.getSLang() == "En"
    assert translation.getWord() == "apple"
    assert translation.getDLang() == "Fr"
    assert translation.getTransWord() == "hahaha"
    
#test_translation()

def test_validator():
    translation = Translation ("En","apple","Fr","hahaha")
    validator = Validator()
    assert len(validator.validateTranslation (translation)) == 0
    translation = Translation ("a","apple","Fr","hahaha")
    assert len(validator.validateTranslation(translation)) == 1