package com.example.geektrust.service;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.model.Room;
import com.example.geektrust.util.Constants;


public class CommandFactory {

    public static Command getCommandImpl(String command, Room cave){
        switch (command){
            case Constants.BOOK: return new BookCommand(cave);
            case Constants.VACANCY: return new VacancyCommand(cave);
            default:throw new IncorrecInputException(Constants.INCORRECT_INPUT);
        }

    }
}
