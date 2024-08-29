package com.example.library_management_system.Services;

import com.example.library_management_system.Entities.Book;
import com.example.library_management_system.Entities.LibraryCard;
import com.example.library_management_system.Entities.Transaction;
import com.example.library_management_system.Enum.TransactionStatus;
import com.example.library_management_system.Enum.TransactionType;
import com.example.library_management_system.Exceptions.BookNotAvailableException;
import com.example.library_management_system.Exceptions.BookNotFoundException;
import com.example.library_management_system.Exceptions.CardNotFoundException;
import com.example.library_management_system.Exceptions.MaxLimitOfBooksIssuedException;
import com.example.library_management_system.Repositories.BookRepository;
import com.example.library_management_system.Repositories.LibraryCardRepository;
import com.example.library_management_system.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryCardRepository cardRepository;
    public String issueBook(int bookId, int cardId) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUE);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("Invalid Book Id");
        }
        Optional<LibraryCard> optionalCard = cardRepository.findById(cardId);
        if(optionalCard.isEmpty()){
            throw new CardNotFoundException("Invalid Card Id");
        }
        Book book = optionalBook.get();
        LibraryCard card = optionalCard.get();

        if(book.getIsAvailable() == Boolean.FALSE){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            Transaction t = transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book not available. Transaction failed with transaction Id -"+t.getTransactionId());
        }
        if(card.getNoOfBooksIssued() >= LibraryCard.MAX_NUMBER_OF_BOOKS_ISSUE){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            Transaction t = transactionRepository.save(transaction);
            throw new MaxLimitOfBooksIssuedException("Student has already issue "+ LibraryCard.MAX_NUMBER_OF_BOOKS_ISSUE+" books. First submit them to library to issue new book. Transaction failed with transaction Id -"+t.getTransactionId());
        }

        transaction.setBook(book);
        transaction.setLibraryCard(card);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIsAvailable(Boolean.FALSE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);
        Transaction t = transactionRepository.save(transaction);
        return "Book -"+book.getName()+" with book Id - "+bookId+" has been issued to -" +card.getStudent().getName()+" with card Id -"+cardId+". Transaction ID -"+t.getTransactionId();
    }

    public String returnBook(int bookId, int cardId) throws Exception{
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("Invalid Book Id");
        }

        Optional<LibraryCard> optionalCard= cardRepository.findById(cardId);
        if(optionalCard.isEmpty()){
            throw new CardNotFoundException("Invalid Card ID");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransactionType(TransactionType.RETURN);

        LibraryCard card= optionalCard.get();
        Book book= optionalBook.get();

        if(book.getIsAvailable() ) {
            throw new Exception("Book is already available in liberary");
        }
        if(card.getNoOfBooksIssued() == 0){
            throw new Exception("Student who has issued this book can return it");
        }

        transaction.setBook(book);
        transaction.setLibraryCard(card);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction = transactionRepository.save(transaction);
        return "Transaction with transaction id -"+transaction.getTransactionId()+" is successful";
    }
}
