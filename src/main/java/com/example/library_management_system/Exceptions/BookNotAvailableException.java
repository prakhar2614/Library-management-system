package com.example.library_management_system.Exceptions;

public class BookNotAvailableException extends Exception{

    public BookNotAvailableException(String message){
        super(message);
    }
}
