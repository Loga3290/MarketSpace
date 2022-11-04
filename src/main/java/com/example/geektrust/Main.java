package com.example.geektrust;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.model.*;
import com.example.geektrust.service.Command;
import com.example.geektrust.service.CommandFactory;
import com.example.geektrust.util.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static final int COMMAND_INDEX = 0;
    private static Room cave;
    public static void main(String[] args) throws IncorrecInputException {


        try {
            initializeRooms();
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[COMMAND_INDEX]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                executeCommand(sc.nextLine());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {

        }

    }

    public static void executeCommand(String next) throws IncorrecInputException {
        try{
            List<String> args = Arrays.asList(next.split(Constants.SPACE));
            Command command = CommandFactory.getCommandImpl(args.get(COMMAND_INDEX), cave);
            System.out.println(command.executeCommand(args.stream().skip(1).collect(Collectors.toList())));
        } catch (IncorrecInputException ex){
            System.out.println(ex.getMessage());
        }


    }
    public static void initializeRooms() {
        Room mansion = new Mansion();
        Room tower = new Tower();
        cave = new Cave();

        cave.setNextAvailableRoom(tower);
        tower.setNextAvailableRoom(mansion);
    }
}
