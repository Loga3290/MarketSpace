package com.example.geektrust.model;

import java.util.List;

public class BookCommand extends Command{

    @Override
    public String executeCommand(List<String> args) {
        Room room = this.room;
        Meeting meeting = new Meeting(args.get(0), args.get(1), bufferTimes);
        return room.addMeeting(meeting, args.get(2));
    }
}
