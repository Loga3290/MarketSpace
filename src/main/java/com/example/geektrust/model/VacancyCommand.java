package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class VacancyCommand extends Command {

    public VacancyCommand(Room room) {
        super(room);
    }

    @Override
    public String executeCommand(List<String> args) {
        //Getting the fromDateTime from args
        String fromDateTime = args.get(0);
        //Getting the toDateTime from args
        String toDateTime = args.get(1);
        Meeting meeting = new Meeting(fromDateTime, toDateTime, this.bufferTimes);
        Room room = this.room;
        return room.getRoomAvailability(new ArrayList<>(), meeting, room);
    }

}
