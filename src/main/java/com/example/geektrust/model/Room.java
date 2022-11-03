package com.example.geektrust.model;

import com.example.geektrust.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

public interface Room {

    Integer getCapacity();
    String getName();
    List<Meeting> getMeetingsSchdeduled();
    void setNextAvailableRoom(Room room);
    Room getNextAvailableRoom();
    void addMeeting(Meeting meeting);
    default boolean validate(Integer capacity){
        return capacity < Constants.LOWER_LIMIT ? false : capacity > Constants.UPPER_LIMIT ? false : true;
    }

    default void getAvailability(List<Meeting> meetingsSchdeduled, List<String> availableRooms, Meeting meeting){
        if(meetingsSchdeduled.stream().filter(existingMeeting -> existingMeeting.anyMeetingsExistsBetween(meeting))
                .collect(Collectors.toList()).isEmpty()){
            availableRooms.add(this.getName());
        }
    }



}
