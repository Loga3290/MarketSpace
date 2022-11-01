package com.example.geektrust.model;

import java.util.List;

public class BookCommand extends Command{

    @Override
    public String executeCommand(List<String> args) {
        //Getting the fromDateTime from args
        String fromDateTime = args.get(0);
        //Getting the toDateTime from args
        String toDateTime = args.get(1);
        //Getting the capacity from args
        String capacity = args.get(2);
        Room room = this.room;
        Meeting meeting = new Meeting(fromDateTime, toDateTime, bufferTimes);
        return room.addMeeting(meeting, capacity);
    }
}
