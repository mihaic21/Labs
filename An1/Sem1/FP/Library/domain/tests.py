'''
Created on Nov 2, 2012

@author: Mihai Costea
'''

from domain.Types import Book, Client

def test_Book():
    book = Book("title", "author", "description")
    assert book.getTitle() == "title"
    assert book.getAuthor() == "author"
    assert book.getDescription() == "description"
    book.setBorrowed(True)
    assert book.isBorrowed() == True
    book.setAuthor("new author")
    assert book.getAuthor() == "new author"
    book.setDescription(None)
    book.setTitle("new title")
    assert book.getTitle() == "new title"
    book.setPopularity(1)
    assert book.getPopularity() == 1
    assert book.getDescription() == None
    book.setId(0)
    
def test_Client():
    client = Client("name", "cnp")
    client.setName("nname")
    assert client.getName() == "nname"
    assert client.getCnp() == "cnp"
    client.setCnp("0")
    assert client.getCnp() == "0"
    client.setId(0)

    