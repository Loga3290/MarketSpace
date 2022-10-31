package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Mansion implements Room{

    private final Integer capacity = 20;
    private final String name = "G-Mansion";
    private List<Meeting> meetingsScheduled = new ArrayList<>();
    private static Mansion mansion = null;
    private Room nextRoomToCheckAvl;
    private Mansion(){

    }
    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Room getInstance() {
        if(mansion == null){
            mansion = new Mansion();
        }
        return mansion;
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
    public String addMeeting(Meeting meeting, String capacity) {
        return Room.super.addMeeting(meeting, capacity, meetingsScheduled);
    }
}
