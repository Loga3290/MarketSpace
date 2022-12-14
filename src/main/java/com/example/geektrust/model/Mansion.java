package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Mansion extends Room {

    public Mansion(Room nextRoom) {
        super(20, "G-Mansion", new ArrayList<>(), nextRoom);
    }

    @Override
    protected String addRoomMeeting(Meeting meeting, Integer requiredCapacity) {
        return super.addMeeting(meeting, requiredCapacity);
    }

    @Override
    protected String getRoomAvailability(List<String> availableRooms, Meeting meeting, Room room){
        return super.getAvailability(availableRooms, meeting, room);
    }

}
