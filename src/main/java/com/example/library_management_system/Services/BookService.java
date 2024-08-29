package com.example.library_management_system.Services;


import com.example.library_management_system.Entities.Author;
import com.example.library_management_system.Entities.Book;
import com.example.library_management_system.Repositories.AuthorRepository;
import com.example.library_management_system.Repositories.BookRepository;
import com.example.library_management_system.RequestDTOs.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    public String addBook(AddBookRequest addBookRequest) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(addBookRequest.getAuthorId());
        if(optionalAuthor.isEmpty()){
            throw new Exception(("Invalid Author ID"));
        }
        Author author = optionalAuthor.get();

        Book book = new Book(addBookRequest.getName(), addBookRequest.getBookGenre(), addBookRequest.getPrice(), addBookRequest.getIsAvailable(), addBookRequest.getNoOfPages(), addBookRequest.getPublishDate());
        author.getBookList().add(book);

        book.setAuthor(author);
        book = bookRepository.save(book);

        authorRepository.save(author);
        return "New book with book ID "+ book.getBookId()+" is saved";
    }

    public String updateBookName(int bookId, String bookName) throws Exception{
        Optional<Book> optionalBook= bookRepository.findById(bookId);

        if(optionalBook.isEmpty()){
            throw new Exception("No wbook found with book ID - "+ bookId);
        }
        Book book = optionalBook.get();
        String oldBookName = book.getName();
        book.setName(bookName);
        bookRepository.save(book);
        return "Book name for book id - "+bookId+" has been updated from "+oldBookName+" to "+book.getName()
;    }

    public String removeBook( int bookId){
        bookRepository.deleteById(bookId);
        return "Book deleted with book ID "+ bookId;
    }

    public Book getBook(int bookId) throws Exception{
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()){
            throw new Exception("No Book found with book Id - " +bookId);
        }
        Book book = optionalBook.get();
        return book;
    }
}
