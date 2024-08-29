package com.example.library_management_system.RequestDTOs;

import com.example.library_management_system.Entities.Author;
import com.example.library_management_system.Entities.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAuthorResponse {

    public GetAuthorResponse(Author author){
        this.authorId = author.getAuthorId();
        this.authorName= author.getAuthorName();
        this.authorAge = author.getAuthorAge();
        this.emailid=author.getEmailid();
        for(Book b: author.getBookList()){
            this.bookIds.add(b.getBookId());
        }
    }

    private int authorId;

    private String authorName;

    private int authorAge;

    private String emailid;

    private List<Integer> bookIds = new ArrayList<>();
}
