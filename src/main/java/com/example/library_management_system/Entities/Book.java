package com.example.library_management_system.Entities;

import com.example.library_management_system.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre bookGenre;

    private int noOfPages;

    private Boolean isAvailable;

    private Date publishDate;

    private int price;


    public Book(String name, Genre bookGenre, int price, Boolean isAvailable, int noOfPages, Date publishDate) {
        this.name = name;
        this.bookGenre = bookGenre;
        this.noOfPages = noOfPages;
        this.isAvailable = isAvailable;
        this.publishDate = publishDate;
    }

    @JoinColumn(referencedColumnName="emailId")
    @ManyToOne
    private Author author;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
