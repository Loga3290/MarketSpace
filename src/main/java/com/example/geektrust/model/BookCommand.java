package com.example.geektrust.model;

import java.util.List;

public class BookCommand extends Command{

    @Override
    public String executeCommand(List<String> args) {

        String fromDateTime = args.get(0);
        String toDateTime = args.get(1);
        String capacity = args.get(2);
        Room room = this.room;
        Meeting meeting = new Meeting(fromDateTime, toDateTime, bufferTimes);
        return room.addMeeting(meeting, capacity);
    }
}
