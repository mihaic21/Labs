'''
Created on 16.01.2013

@author: mihai_000
'''
from BingoSort import bingoSort
from CompareFunctions import compareInts, compareNames
from Lists import intList, nameList
from MergeSort import mergeSort


def mergeSortInts():
    print mergeSort(intList, compareInts)
    
def mergeSortNames():
    print mergeSort(nameList, compareNames)
    
def bingoSortInts():
    newIntList = intList[:]
    bingoSort(newIntList, compareInts)
    print newIntList

if __name__ == '__main__':
    mergeSortInts()
    mergeSortNames()
    bingoSortInts()