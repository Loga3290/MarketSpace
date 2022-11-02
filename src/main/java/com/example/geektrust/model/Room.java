package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Room {

    private  Integer capacity;
    private  String name;
    private List<Meeting> meetingsScheduled;
    private Room nextRoom;

    public Room(Integer capacity, String name, List<Meeting> meetingsScheduled, Room nextRoom) {
        this.capacity = capacity;
        this.name = name;
        this.meetingsScheduled = meetingsScheduled;
        this.nextRoom = nextRoom;
    }

    boolean validate(Integer capacity){
        return capacity < Constants.LOWER_LIMIT ? false : capacity > Constants.UPPER_LIMIT ? false : true;
    }

    String addMeeting(Meeting meeting, Integer requiredCapacity) {
        //Validate the requirements
        if(!validate(requiredCapacity)){
            throw new IncorrecInputException(Constants.NO_VACANT_ROOM);
        }

        //If room capacity fits
        if(this.capacity >= requiredCapacity){
            //Check if the room is available
            if(this.meetingsScheduled.stream()
                    .filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                this.meetingsScheduled.add(meeting);
                return this.name;
            }
        }
        if(this.nextRoom != null){
            return nextRoom.addMeeting(meeting, requiredCapacity);
        }
        return Constants.NO_VACANT_ROOM;
    }

    String getAvailability(List<String> availableRooms, Meeting meeting, Room room){
        do{
            if(room.meetingsScheduled.stream().filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                availableRooms.add(room.name);
            }
            room = room.nextRoom;
        }
        while(room != null);
        return availableRooms.isEmpty() ? Constants.NO_VACANT_ROOM : availableRooms.stream()
                .collect(Collectors.joining(Constants.SPACE));

    }

}
