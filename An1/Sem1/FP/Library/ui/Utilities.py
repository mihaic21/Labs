'''
Created on Nov 8, 2012

@author: mihai
'''

class Utilities:
    """
    Miscellaneous methods for printing messages to the user
    """
    
    def printMessage(self, status, operation, operand):
        """
        Method for printing status messages
        status can be: "success" "not found" "borrowed" "not borrowed"
        operation can be: "remove" "update" "add" "lend" "return" "search"
        operand can be: "book" "client"
        O(1)
        """
        if operand == "book":
            if status == "success":
                if operation == "remove":
                    raw_input ("*Book was removed successfully!*\nPress any key to return")
                elif operation == "update":
                    raw_input ("*Book was updated successfully!*\nPress any key to return")
                elif operation == "add":
                    raw_input ("*Book was added successfully!*\nPress any key to return")
                elif operation == "lend":
                    raw_input ("*Book was successfully set to 'borrowed'*\nPress any key to return")
                elif operation == "return":
                    raw_input ("*Book was successfully set to 'returned'*\nPress any key to return")
            elif status == "not found":
                if operation == "search":
                    raw_input ("*No books were found!*\nPress any key to return")
                else:
                    raw_input ("*Book was not found!*\nPress any key to return")
            elif status == "borrowed":
                raw_input ("*All books are borrowed!*\nPress any key to return")
            elif status == "not borrowed":
                raw_input ("*Book was not borrowed by this client!*\nPress any key to return")
        
        if operand == "client":
            if status == "success":
                if operation == "remove":
                    raw_input ("*Client was removed successfully!*\nPress any key to return")
                elif operation == "update":
                    raw_input ("*Client was updated successfully!*\nPress any key to return")
                elif operation == "add":
                    raw_input ("*Client was added successfully!*\nPress any key to return")
            elif status == "not found":
                if operation == "search":
                    raw_input ("*No clients were found!*\nPress any key to return")
                else:
                    raw_input ("*Client was not found!*\nPress any key to return")
                
    def printObjectList(self, listToPrint):
        """
        Prints the objects in a list
        listToPrint is a list of objects
        O(n)
        """
        print listToPrint
        raw_input("Press any key to return")
        
    def printList(self, listToPrint):
        """
        Prints a list of strings
        listToPrint is a list of strings
        O(n)
        """
        for item in listToPrint:
            print item
        raw_input("Press any key to return")
