package com.example.library_management_system.RequestDTOs;

import com.example.library_management_system.Entities.Book;
import com.example.library_management_system.Entities.Transaction;
import com.example.library_management_system.Enum.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetBookResponse {

    private int bookId;

    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre bookGenre;

    private int noOfPages;

    private Boolean isAvailable;

    private Date publishDate;

    private int price;

    private Integer authorId;

    private List<String> transactionIds=new ArrayList<>();

    public GetBookResponse(Book book){
        this.name = book.getName();
        this.bookGenre = book.getBookGenre();
        this.noOfPages = book.getNoOfPages();
        this.isAvailable = book.getIsAvailable();
        this.publishDate = book.getPublishDate();
        this.authorId = book.getAuthor().getAuthorId();
        for(Transaction t: book.getTransactionList()){
            transactionIds.add(t.getTransactionId());
        }
    }
}
