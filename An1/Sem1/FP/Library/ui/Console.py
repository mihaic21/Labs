'''
Created on Nov 1, 2012

@author: Mihai Costea
'''
from ui.Utilities import Utilities

class Console:
    def __init__(self, libraryController):
        """
        Constructor for the UI class
        Adds the corresponding functions to the menu options
        libraryController is an instance of the controller used in the application
        O(1)
        """
        self.__menu_options = {
                      '1':self.__menu1,
                      '2':self.__menu2,
                      '3':self.__menu3,
                      '4':self.__menu4,
                      '5':self.__menu5,
                      '6':self.__menu6,
                      '7':self.__menu7
                      }
        self.__submenu1_options = {
                                   '1':self.__addNewBook,
                                   '2':self.__removeBook,
                                   '3':self.__updateBook,
                                   '4':self.__listBooks}
        self.__submenu2_options = {
                                   '1':self.__addNewClient,
                                   '2':self.__removeClient,
                                   '3':self.__updateClient,
                                   '4':self.__listClients}
        self.__submenu7_options = {
                                   '1':self.__orderBooksByMostRented,
                                   '2':self.__orderBooksByLeastRented,
                                   '3':self.__orderClientsByMostActive,
                                   '4':self.__orderClientsByLeastActive}
        
        self.__libraryController = libraryController
        
        self.showMenu()
        
    __menu = "(1) Manage books\n" \
            "(2) Manage clients\n" \
            "(3) Search for a book\n" \
            "(4) Search for a client\n" \
            "(5) Lend a book\n" \
            "(6) Return a book\n" \
            "(7) Reports"
    __submenu1 = "(1) Add a new book\n" \
                "(2) Remove a book\n" \
                "(3) Update a book\n" \
                "(4) List books"
    __submenu2 = "(1) Add a new client\n" \
                "(2) Remove a client\n" \
                "(3) Update a client\n" \
                "(4) List clients"
    __submenu7 = "(1) Order books by most rented\n" \
                "(2) Order books by least rented\n" \
                "(3) Order clients by most active\n" \
                "(4) Order clients by least active"
    __back = "(x) Back"
    __exit = "(x) Exit app"
    __divider = "---------------------"
    __exitValue = "x"
    __backValue = "x"
   
    __uiUtilities = Utilities()
        
    def __menu1(self):
        """
        Menu entry 1
        Prints out the submenu for menu 1
        O(1)
        """
        user_input = ""
        while user_input != self.__backValue:
            print self.__submenu1
            print self.__divider
            print self.__back
            user_input = raw_input ("Please enter your choice:")
            try:
                self.__submenu1_options[user_input]()
            except KeyError:
                if user_input != 'x':
                    raw_input ("*INVALID CHOICE!*")
    
    def __menu2(self):
        """
        Menu entry 2
        Prints out the submenu for menu 2
        O(1)
        """
        user_input = ""
        while user_input != self.__backValue:
            print self.__submenu2
            print self.__divider
            print self.__back
            user_input = raw_input ("Please enter your choice:")
            try:
                self.__submenu2_options[user_input]()
            except KeyError:
                if user_input != 'x':
                    raw_input ("*INVALID CHOICE!*")
            
    def __menu3(self):
        """
        Menu entry 3
        O(n)
        """
        searchQuery = raw_input ("Please enter the title or author or description of the book:")
        foundBooks = self.__libraryController.searchBooks(searchQuery)
        if foundBooks == []:
            self.__uiUtilities.printMessage("not found", "search", "book")
        else:
            self.__uiUtilities.printList(foundBooks)
        
    def __menu4(self):
        """
        Menu entry 4
        O(n)
        """
        searchQuery = raw_input ("Please enter the name or the cnp of the client:")
        foundClients = self.__libraryController.searchClients(searchQuery)
        if foundClients == []:
            self.__uiUtilities.printMessage("not found", "search", "client")
        else:
            self.__uiUtilities.printList(foundClients)
        
    def __menu5(self):
        """
        Menu entry 5
        O(n)
        """
        bookName = raw_input ("Please enter the title:")
        bookAuthor = raw_input ("Please enter the author:")
        bookDescription = raw_input ("Please enter the description:")
        clientName = raw_input ("Please enter the client's name:")
        clientCnp = raw_input ("Please enter the client's CNP:")
        status = self.__libraryController.lendBook(bookName, bookAuthor, bookDescription, \
                                              clientName, clientCnp)
        if status == "book not found":
            self.__uiUtilities.printMessage("not found", "lend", "book")
        elif status == "client not found":
            self.__uiUtilities.printMessage("not found", "lend", "client")
        elif status == "all books borrowed":
            self.__uiUtilities.printMessage("borrowed", "lend", "book")
        elif status == True:
            self.__uiUtilities.printMessage("success", "lend", "book")
        
    def __menu6(self):
        """
        Menu entry 6
        O(n)
        """
        bookTitle = raw_input ("Please enter the title:")
        bookAuthor = raw_input ("Please enter the author:")
        bookDescription = raw_input ("Please enter the description:")
        clientName = raw_input ("Please enter the client's name:")
        clientCnp = raw_input ("Please enter the client's cnp:")
        status = self.__libraryController.returnBook(bookTitle, bookAuthor, bookDescription, \
                                            clientName, clientCnp)
        if status == "book not found":
            self.__uiUtilities.printMessage("not found", "return", "book")
        elif status == "client not found":
            self.__uiUtilities.printMessage("not found", "return", "client")
        elif status == False:
            self.__uiUtilities.printMessage("not borrowed", "return", "book")
        elif status == True:
            self.__uiUtilities.printMessage("success", "return", "book")

    def __menu7(self):
        """
        Menu entry 7
        Prints out the submenu for menu entry 7
        O(1)
        """
        user_input = ""
        while user_input != self.__backValue:
            print self.__submenu7
            print self.__divider
            print self.__back
            user_input = raw_input ("Please enter your choice:")
            try:
                self.__submenu7_options[user_input]()
            except KeyError:
                if user_input != 'x':
                    raw_input ("*!INVALID CHOICE!*")
                 
    def __addNewBook(self):
        """
        Private UI method for adding a new book
        O(1)
        """
        title = raw_input ("Book title:")
        author = raw_input ("Book author:")
        description = raw_input ("Book description")
        status = self.__libraryController.addNewBook(title, author, description)
        if status == None:
            self.__uiUtilities.printMessage("success", "add", "book")
        else:
            self.__uiUtilities.printList(status)
        
        
    def __removeBook(self):
        """
        Private UI method for removing a book
        Best case O(1)
        Worst case O(n)
        Average case O(n)
        """
        title = raw_input ("Book title:")
        author = raw_input ("Book author:")
        description = raw_input ("Book description")
        if not self.__libraryController.removeBook(title, author, description):
            self.__uiUtilities.printMessage("not found", "remove", "book")
        else:
            self.__uiUtilities.printMessage("success", "remove", "book")
        
        
    def __updateBook(self):
        """
        Private UI method to update a book
        O(n)
        """
        title = raw_input ("Book title:")
        author = raw_input ("Book author:")
        description = raw_input ("Book description")
        newTitle = raw_input ("New book title (leave blank if not changed):")
        newAuthor = raw_input ("New book author (leave blank if not changed):")
        newDescription = raw_input ("New book description (leave blank if not changed):")
        if not self.__libraryController.updateBook(title, author, description, \
                                                    newTitle, newAuthor, newDescription):
            self.__uiUtilities.printMessage("not found", "update", "book")
        else:
            self.__uiUtilities.printMessage("success", "update", "book")
        
    def __listBooks(self):
        """
        Private UI method to print out the book list
        O(1)
        """
        self.__libraryController.listBooks()
    
    def __addNewClient(self):
        """
        Private UI method to add a new client
        O(n)
        """
        name = raw_input ("Client name:")
        cnp = raw_input ("Client cnp:")
        status = self.__libraryController.addNewClient(name, cnp)
        if status == None:
            self.__uiUtilities.printMessage("success", "add", "client")
        else:
            self.__uiUtilities.printList(status)
        
    def __removeClient(self):
        """
        Private UI method to remove a client
        O(n)
        """
        name = raw_input ("Client name:")
        cnp = raw_input ("Client cnp:")
        if not self.__libraryController.removeClient(name, cnp):
            self.__uiUtilities.printMessage("not found", "remove", "client")
        else:
            self.__uiUtilities.printMessage("success", "remove", "client")
        
    def __updateClient(self):
        """
        Private UI method to update a client
        O(n)
        """
        name = raw_input ("Client name:")
        cnp = raw_input ("Client cnp:")
        newName = raw_input ("New client name (leave blank if unchanged):")
        newCnp = raw_input ("New client cnp (leave blank if unchanged):")
        if not self.__libraryController.updateClient(name, cnp, newName, newCnp):
            self.__uiUtilities.printMessage("not found", "update", "client")
        else:
            self.__uiUtilities.printMessage("success", "update", "client")
        
    def __listClients(self):
        """
        Private UI method to print out the list of clients
        O(1)
        """
        self.__libraryController.listClients()
    
    def __orderBooksByMostRented(self):
        """
        Private UI method to order books by most rented
        O(n)
        """
        self.__libraryController.orderBooks("desc")
    
    def __orderBooksByLeastRented(self):
        """
        Private UI method to order books by least rented
        O(n)
        """
        self.__libraryController.orderBooks("asc")
     
    def __orderClientsByMostActive(self):
        """
        Private UI method to order clients by most active
        O(n)
        """
        self.__libraryController.orderClients("desc")
     
    def __orderClientsByLeastActive(self):
        """
        Private UI method to order clients by least active
        O(n)
        """
        self.__libraryController.orderClients("asc")
    
    def showMenu(self):
        """
        UI method to print out and handle the main menu
        O(1)
        """
        user_input = ""
        while user_input != self.__exitValue:
            print self.__menu
            print self.__divider
            print self.__exit
            user_input = raw_input ("Please enter your choice:")
            try:
                self.__menu_options[user_input]()
                user_input = ""
            except KeyError:
                if user_input != 'x':
                    raw_input ("*INVALID CHOICE!*")
