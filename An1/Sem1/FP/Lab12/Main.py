'''
Created on 16.01.2013

@author: mihai_000
'''

if __name__ == '__main__':
    
    
    def generate(solution, dim):
        """
        Backtracking exercise
        """
        if len(solution) == 2*dim + 1: # the solution is complete
            if solution[len(solution) - 1] == 0: # only print out the complete solution that has the last element 0
                print solution
        
        if len(solution) > 2*dim + 1: # the solution is too long
            return
        
        # when the program reaches here, the solution is still valid, but it has less elements than needed
        solution.append(0) # add another element to the solution
        
        for i in {-1, 0, 1}: # try each available value
            solution[len(solution) - 1] = i # assign it at the end of the array
            
            if solution[0] == 0 and  \
            abs(solution[len(solution) - 1] - solution[len(solution) - 2]) in {1, 2}: # test if this solution is correct so far
            
                generate(solution[:], dim) # if it is then go on
            if len(solution) < 2: # if the solution is very small (a new solution for example) generate more values even if they might not be correct
                generate(solution[:], dim) # if this is omitted, the program will loop the for 3 times and exit because it won't generate any solutions
            
            
    generate([], 5)