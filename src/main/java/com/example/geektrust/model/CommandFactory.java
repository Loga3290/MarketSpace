package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;


public class CommandFactory {

    public static Command getCommandImpl(String command, Room cave){
        switch (command){
            case "BOOK" : return new BookCommand(cave);
            case "VACANCY" : return new VacancyCommand(cave);
            default:throw new IncorrecInputException("INCORRECT_INPUT");
        }

    }
}
