'''
Created on 16.01.2013

@author: mihai_000
'''

def mergeSort(listToSort, compareFunction):
    if len(listToSort) == 1:
        return listToSort
    left = list()
    right= list()
    middle = len(listToSort) / 2
    
    for i in range(middle):
        left.append(listToSort[i])
        
    for i in range(middle, len(listToSort)):
        right.append(listToSort[i])
        
    left = mergeSort(left, compareFunction)
    right = mergeSort(right, compareFunction)
    
    return merge(left, right, compareFunction)

def merge(left, right, compareFunction):
    result = list()
    while len(left) > 0 or len(right) > 0:
        if len(left) > 0 and len(right) > 0:
            if compareFunction(left[0], right[0]) in {-1, 0}:
                result.append(left[0])
                left.pop(0)
            else:
                result.append(right[0])
                right.pop(0)
        elif len(left) > 0:
            result.append(left[0])
            left.pop(0)
        elif len(right) > 0:
            result.append(right[0])
            right.pop(0)
    return result