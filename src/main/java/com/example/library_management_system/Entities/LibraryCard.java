package com.example.library_management_system.Entities;


import com.example.library_management_system.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {

    public static final int MAX_NUMBER_OF_BOOKS_ISSUE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;


    @JoinColumn
    @OneToOne
    private Student student;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
