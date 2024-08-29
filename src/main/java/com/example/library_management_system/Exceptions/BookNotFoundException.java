package com.example.library_management_system.Exceptions;

public class BookNotFoundException extends  Exception{

    public BookNotFoundException(String message){
        super(message);
    }
}
