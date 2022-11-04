package com.example.geektrust.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.geektrust.util.Constants.COLON;


public class BufferTime {

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public BufferTime(String fromDate, String toDate) {
        this.fromDate = LocalDateTime.now().with(
                LocalTime.of(
                        Integer.parseInt(fromDate.split(COLON)[0]),
                        Integer.parseInt(fromDate.split(COLON)[1])
                ));
        this.toDate = LocalDateTime.now().with(
                LocalTime.of(
                        Integer.parseInt(toDate.split(COLON)[0]),
                        Integer.parseInt(toDate.split(COLON)[1])
                ));
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

}
