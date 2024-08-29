package com.example.library_management_system.Exceptions;

public class MaxLimitOfBooksIssuedException extends Exception{

    public MaxLimitOfBooksIssuedException(String message){
        super(message);
    }
}
