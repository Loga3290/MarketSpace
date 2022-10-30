package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;

import java.util.List;
import java.util.stream.Collectors;

public interface Room {
    Integer getCapacity();
    String getName();
    List<Meeting> getMeetingsSchdeduled();
    void setNextAvailableRoom(Room room);
    Room getNextAvailableRoom();

    default boolean validate(Integer capacity){
        return capacity < 2 ? false : capacity > 20 ? false : true;
    }

    default String addMeeting(Meeting meeting, String capacity, List<Meeting> meetingsScheduled) {
        //Validate the requirements
        if(!validate(Integer.parseInt(capacity))){
            throw new IncorrecInputException("NO_VACANT_ROOM");
        }

        //If room capacity fits
        if(this.getCapacity() >= Integer.parseInt(capacity)){
            //Check if the room is available
            if(meetingsScheduled.stream()
                    .filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                this.getMeetingsSchdeduled().add(meeting);
                return this.getName();
            }
        }
        Room nextRoomToCheckAvl = this.getNextAvailableRoom();
        if(nextRoomToCheckAvl != null){
            return nextRoomToCheckAvl.addMeeting(meeting, capacity, nextRoomToCheckAvl.getMeetingsSchdeduled());
        }
        return "NO_VACANT_ROOM";
    }

    default void getAvailability(List<Meeting> meetingsSchdeduled, List<String> availableRooms, Meeting meeting){
        if(meetingsSchdeduled.stream().filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                .collect(Collectors.toList()).isEmpty()){
            availableRooms.add(this.getName());
        }
    }
}
