package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VacancyCommand extends Command {

    @Override
    public String executeCommand(List<String> args) {
        Meeting meeting = new Meeting(args.get(0), args.get(1), this.bufferTimes);

        List<String> availableRooms = new ArrayList<>();
        Room room = this.room;
        do{
            room.getAvailability(room.getMeetingsSchdeduled(), availableRooms, meeting);
            room = room.getNextAvailableRoom();
        }
        while(room != null);
        return availableRooms.stream()
                .collect(Collectors.joining(" "));
    }

}
