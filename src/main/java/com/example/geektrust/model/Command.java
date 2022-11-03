package com.example.geektrust.model;

import java.util.Arrays;
import java.util.List;

public abstract class Command {

    Room room;

    Command(Room room){
        this.room =  room;
    }

    List<BufferTime> bufferTimes = Arrays.asList(new BufferTime("09:00","09:15"),
            new BufferTime("13:15","13:45"),
            new BufferTime("18:45","19:00"));

    public abstract String executeCommand(List<String> args);
}
