package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Tower implements Room {

    private final Integer capacity = 7;
    private final String name = "D-Tower";
    private List<Meeting> meetingsScheduled = new ArrayList<>();
    private Room nextRoomToCheckAvl;

    @Override
    public Integer getCapacity() {
        return this.capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setNextAvailableRoom(Room room) {
        this.nextRoomToCheckAvl = room;
    }

    @Override
    public Room getNextAvailableRoom() {
        return nextRoomToCheckAvl;
    }

    @Override
    public List<Meeting> getMeetingsSchdeduled() {
        return this.meetingsScheduled;
    }

    @Override
    public String addMeeting(Meeting meeting, String capacity) {
        return Room.super.addMeeting(meeting, capacity, this.getMeetingsSchdeduled());
    }

    @Override
    public void addMeeting(Meeting meeting) {
        this.meetingsScheduled.add(meeting);
    }
}
