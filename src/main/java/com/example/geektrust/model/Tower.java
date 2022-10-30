package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Tower implements Room {

    private final Integer capacity = 7;
    private final String name = "D-Tower";
    private List<Meeting> meetingsScheduled = new ArrayList<>();
    private static Tower tower = null;
    private Room nextRoomToCheckAvl;

    @Override
    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Room getInstance() {
        if (tower == null) {
            tower = new Tower();
        }
        return tower;
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
}
