'''
Created on Nov 8, 2012

@author: mihai
'''
from repository.RepositoryInFiles import ClientListInFile, BookListInFile
from repository.Storage import BookList, ClientList

def test_BookList():
    bookList = BookList()
    bookList.addNewBook("title", "author", "description")
    assert bookList.numberOfBooks() == 1
    assert bookList.lastAddedBook().getTitle() == "title"
    assert bookList.lastAddedBook().getAuthor() == "author"
    assert bookList.lastAddedBook().getDescription() == "description"
    bookList.addNewBook("a", "a", "a")
    bookList.updateBook("a", "a", "a", "b", "b", "b")
    assert bookList.lastAddedBook().getTitle() == "b"
    assert bookList.lastAddedBook().getAuthor() == "b"
    assert bookList.lastAddedBook().getDescription() == "b"
    """
    bookList.removeBook("title", "author", "description")
    assert bookList.numberOfBooks() == 1
    assert bookList.lastAddedBook().getTitle() == "b"
    assert bookList.lastAddedBook().getAuthor() == "b"
    assert bookList.lastAddedBook().getDescription() == "b"
    """
    
def test_ClientList():
    clientList = ClientList()
    clientList.addNewClient("name", "1910207205571")
    assert clientList.numberOfClients() == 1
    assert clientList.lastAddedClient().getName() == "name"
    assert clientList.lastAddedClient().getCnp() == "1910207205571"
    clientList.addNewClient("New Name", "1910207205111")
    clientList.updateClient("name", "1910207205571", "a", "1111111111111")
    assert clientList.lastAddedClient().getName() == "New Name"
    assert clientList.lastAddedClient().getCnp() == "1910207205111"
    clientList.removeClient("New Name", "1910207205111")
    assert clientList.numberOfClients() == 1
    assert clientList.lastAddedClient().getName() == "a" 
    assert clientList.lastAddedClient().getCnp() == "1111111111111"
    
def test_ClientListInFile():
    clientList = ClientListInFile("clientTest.dat")
    if clientList.numberOfClients() == 0:
        clientList.addNewClient("name", "1910207205571")
        assert clientList.numberOfClients() == 1
        assert clientList.lastAddedClient().getName() == "name"
        assert clientList.lastAddedClient().getCnp() == "1910207205571"
        clientList.addNewClient("New Name", "1910207205111")
        clientList.updateClient("name", "1910207205571", "a", "1111111111111")
        assert clientList.lastAddedClient().getName() == "New Name"
        assert clientList.lastAddedClient().getCnp() == "1910207205111"
        clientList.removeClient("New Name", "1910207205111")
        assert clientList.numberOfClients() == 1
        assert clientList.lastAddedClient().getName() == "a" 
        assert clientList.lastAddedClient().getCnp() == "1111111111111"
    else:
        assert clientList.numberOfClients() == 1
        
def test_BookListInFile():
    bookList = BookListInFile("bookTest.dat")
    if bookList.numberOfBooks() == 0:
        bookList.addNewBook("title", "author", "description")
        assert bookList.numberOfBooks() == 1
        assert bookList.lastAddedBook().getTitle() == "title"
        assert bookList.lastAddedBook().getAuthor() == "author"
        assert bookList.lastAddedBook().getDescription() == "description"
        bookList.addNewBook("a", "a", "a")
        bookList.updateBook("a", "a", "a", "b", "b", "b")
        assert bookList.lastAddedBook().getTitle() == "b"
        assert bookList.lastAddedBook().getAuthor() == "b"
        assert bookList.lastAddedBook().getDescription() == "b"
        bookList.removeBook("title", "author", "description")
        assert bookList.numberOfBooks() == 1
        assert bookList.lastAddedBook().getTitle() == "b"
        assert bookList.lastAddedBook().getAuthor() == "b"
        assert bookList.lastAddedBook().getDescription() == "b"
    else:
        assert bookList.numberOfBooks() == 1