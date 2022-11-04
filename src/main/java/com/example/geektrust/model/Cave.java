package com.example.geektrust.model;

import com.example.geektrust.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cave implements Room {

    private List<Meeting> meetingsScheduled = new ArrayList<>();
    private Room nextRoomToCheckAvl;

    @Override
    public Integer getCapacity() {
        return Constants.CAVE_CAPACITY;
    }

    @Override
    public String getName() {
        return Constants.CAVE_ROOM_NAME;
    }

    @Override
    public List<Meeting> getMeetingsSchdeduled() {
        return this.meetingsScheduled;
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
    public void addMeeting(Meeting meeting) {
        this.meetingsScheduled.add(meeting);
    }
}
