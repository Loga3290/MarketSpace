package com.example.geektrust.model;

import com.example.geektrust.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> availableRooms = new ArrayList<>();
        Room room = this.room;
        do{
            //check whether room is available and do not have any meetings scheduled
            if(room.getMeetingsSchdeduled().stream()
                    .filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                availableRooms.add(room.getName());
            }
            room = room.getNextAvailableRoom();
        }
        while(room != null);
        return availableRooms.isEmpty() ? Constants.NO_VACANT_ROOM : availableRooms.stream()
                .collect(Collectors.joining(Constants.SPACE));
    }

}
