package com.example.geektrust.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utility {
    public static LocalDateTime getDateTime(String arg) {
        return LocalDateTime.now().with(
                LocalTime.of(
                        Integer.parseInt(arg.split(Constants.COLON)[0]),
                        Integer.parseInt(arg.split(Constants.COLON)[1])
                ));
    }
}
