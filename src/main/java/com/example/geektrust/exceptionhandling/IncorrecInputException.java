package com.example.geektrust.exceptionhandling;

public class IncorrecInputException extends RuntimeException {

    public IncorrecInputException(String incorrectInput) {
        super(incorrectInput);
    }
}
