package com.example.geektrust.model;

import com.example.geektrust.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Tower implements Room {

    private final Integer capacity = Constants.TOWER_CAPACITY;
    private final String name = Constants.TOWER_ROOM_NAME;
    private List<Meeting> meetingsScheduled = new ArrayList<>();
    private Room nextRoomToCheckAvl;

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setNextAvailableRoom(Room room) {
        nextRoomToCheckAvl = room;
    }

    @Override
    public Room getNextAvailableRoom() {
        return nextRoomToCheckAvl;
    }

    @Override
    public List<Meeting> getMeetingsSchdeduled() {
        return meetingsScheduled;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingsScheduled.add(meeting);
    }
}
