package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;


public class CommandFactory {

    public static Command getCommandImpl(String command){
        switch (command){
            case "BOOK" : return new BookCommand();
            case "VACANCY" : return new VacancyCommand();
            default:throw new IncorrecInputException("INCORRECT_INPUT");
        }

    }
}
