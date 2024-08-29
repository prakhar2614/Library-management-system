package com.example.library_management_system.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    private String authorName;

    private int authorAge;

    @Column(unique = true, nullable = false)
    private String emailid;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    public Author(String authorName, int authorAge, String emailid) {
        this.authorAge=authorAge;
        this.authorName = authorName;
        this.emailid = emailid;
    }
}
