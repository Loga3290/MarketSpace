package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class BookCommand extends Command{

    public BookCommand(Room room) {
        super(room);
    }

    @Override
    public String executeCommand(List<String> args) {
        //Getting the fromDateTime from args
        String fromDateTime = args.get(0);
        //Getting the toDateTime from args
        String toDateTime = args.get(1);
        //Getting the capacity from args
        String capacity = args.get(2);
        Meeting meeting = new Meeting(fromDateTime, toDateTime, bufferTimes);
        return addMeeting(meeting, Integer.valueOf(capacity), room);
    }

    private String addMeeting(Meeting meeting, Integer capacity, Room room){
        //Validate the requirements
        if(!room.validate(capacity)){
            throw new IncorrecInputException(Constants.NO_VACANT_ROOM);
        }

        //If room capacity fits
        if(room.getCapacity() >= capacity){
            //Check if the room is available
            if(room.getMeetingsSchdeduled().stream()
                    .filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                room.addMeeting(meeting);
                return room.getName();
            }
        }
        Room nextRoomToCheckAvl = room.getNextAvailableRoom();
        if(nextRoomToCheckAvl != null){
            return addMeeting(meeting, capacity, nextRoomToCheckAvl);
        }
        return Constants.NO_VACANT_ROOM;
    }
}
