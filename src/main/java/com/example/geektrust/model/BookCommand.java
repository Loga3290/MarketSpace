package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class BookCommand extends Command{

    public static final int CAPACITY_INDEX = 2;
    public static final int TO_DATE_INDEX = 1;
    public static final int FROM_DATE_INDEX = 0;

    public BookCommand(Room room) {
        super(room);
    }

    @Override
    public String executeCommand(List<String> args) {
        //Getting the fromDateTime from args
        String fromDateTime = args.get(FROM_DATE_INDEX);
        //Getting the toDateTime from args
        String toDateTime = args.get(TO_DATE_INDEX);
        //Getting the capacity from args
        String capacity = args.get(CAPACITY_INDEX);
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
