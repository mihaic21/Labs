'''
Created on 16.01.2013

@author: mihai_000
'''

def swap(listToSort, x, y):
    aux = listToSort[x]
    listToSort[x] = listToSort[y]
    listToSort[y] = aux

def bingoSort(listToSort, compareFunction):
    """
    Bingo Sort is a variation of the selection sort. While the Selection sorting algorithm checks
    for each item and puts it in the right place, the Bingo Sort checks for each value and puts
    all the other equal values in their right place. This makes it faster on lists with duplicate
    elements.
    
    Best case: O(n)
    Worst case: O(n*n)
    Average case: 
    
    Algorithm is in place!
    """
    maximum = len(listToSort) - 1
    nextValue = listToSort[maximum];
    for i in reversed(range(maximum-1)):
        if compareFunction(listToSort[i], nextValue) == 1:
            nextValue = listToSort[i]
            
    while maximum>0 and listToSort[maximum] == nextValue:
        maximum = maximum-1
    
    while maximum > 0:
        value = nextValue
        nextValue = listToSort[maximum]
        for i in reversed(range(maximum-1)):
            if listToSort[i] == value:
                swap(listToSort, i , maximum)
                maximum = maximum - 1
            elif listToSort[i] > nextValue:
                nextValue = listToSort[i]
        while maximum > 0 and listToSort[maximum] == nextValue:
            maximum = maximum-1
