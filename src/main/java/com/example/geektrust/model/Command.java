package com.example.geektrust.model;

import java.util.Arrays;
import java.util.List;

public abstract class Command {

    Room room;

    Command(){
        room = Cave.getInstance();
        Room tower = Tower.getInstance();
        Room mansion = Mansion.getInstance();
        room.setNextAvailableRoom(tower);
        tower.setNextAvailableRoom(mansion);
    }



    List<BufferTime> bufferTimes = Arrays.asList(new BufferTime("09:00","09:15"),
            new BufferTime("13:15","13:45"),
            new BufferTime("18:15","19:15"));

    public abstract String executeCommand(List<String> args);
}
