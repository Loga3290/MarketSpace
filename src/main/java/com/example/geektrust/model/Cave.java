package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Cave implements Room {

    private final Integer capacity = 3;
    private final String name = "C-Cave";
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
