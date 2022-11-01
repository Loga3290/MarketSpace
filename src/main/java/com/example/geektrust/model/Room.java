package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

public interface Room {

    Integer getCapacity();
    String getName();
    List<Meeting> getMeetingsSchdeduled();
    void setNextAvailableRoom(Room room);
    Room getNextAvailableRoom();
    String addMeeting(Meeting meeting, String capacity);
    void addMeeting(Meeting meeting);
    default boolean validate(Integer capacity){
        return capacity < Constants.LOWER_LIMIT ? false : capacity > Constants.UPPER_LIMIT ? false : true;
    }

    default String addMeeting(Meeting meeting, String capacity, List<Meeting> meetingsScheduled) {
        //Validate the requirements
        if(!validate(Integer.parseInt(capacity))){
            throw new IncorrecInputException(Constants.NO_VACANT_ROOM);
        }

        //If room capacity fits
        if(this.getCapacity() >= Integer.parseInt(capacity)){
            //Check if the room is available
            if(meetingsScheduled.stream()
                    .filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                    .collect(Collectors.toList()).isEmpty()){
                this.addMeeting(meeting);
                return this.getName();
            }
        }
        Room nextRoomToCheckAvl = this.getNextAvailableRoom();
        if(nextRoomToCheckAvl != null){
            return nextRoomToCheckAvl.addMeeting(meeting, capacity, nextRoomToCheckAvl.getMeetingsSchdeduled());
        }
        return Constants.NO_VACANT_ROOM;
    }

    default void getAvailability(List<Meeting> meetingsSchdeduled, List<String> availableRooms, Meeting meeting){
        if(meetingsSchdeduled.stream().filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                .collect(Collectors.toList()).isEmpty()){
            availableRooms.add(this.getName());
        }
    }



}
