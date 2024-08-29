package com.example.library_management_system.RequestDTOs;

import com.example.library_management_system.Enum.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBookRequest {

    private String name;

    private Genre bookGenre;

    private int noOfPages;

    private Boolean isAvailable;

    private Date publishDate;

    private int price;

    private int authorId;
}
