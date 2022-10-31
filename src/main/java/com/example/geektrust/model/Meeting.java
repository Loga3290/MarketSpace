package com.example.geektrust.model;

import com.example.geektrust.exceptionhandling.IncorrecInputException;
import com.example.geektrust.util.Utility;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Meeting {

    private final List<Integer> quarterHours = Arrays.asList(0, 15, 30, 45);
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Meeting(String fromDateTime, String toDateTime, List<BufferTime> bufferTimes) throws IncorrecInputException {
        //Validate meeting
        try {
            this.fromDateTime = Utility.getDateTime(fromDateTime);
            this.toDateTime = Utility.getDateTime(toDateTime);
        } catch (Exception ex) {
            throw new IncorrecInputException("INCORRECT_INPUT");
        }

        //If From date > to date
        if (!this.fromDateTime.isBefore(this.toDateTime)) {
            throw new IncorrecInputException("INCORRECT_INPUT");
        }

        //if fromDate or toDate falls between 23:45 and 23:59
        LocalDateTime windowStartDate = Utility.getDateTime("23:45");
        LocalDateTime windowEndDate = Utility.getDateTime("00:00").plusDays(1);
        if (this.fromDateTime.isAfter(windowStartDate)
                && this.fromDateTime.isBefore(windowEndDate)) {
            throw new IncorrecInputException("INCORRECT_INPUT ");
        }
        if (this.toDateTime.isAfter(windowStartDate)
                && this.toDateTime.isBefore(windowEndDate)) {
            throw new IncorrecInputException("INCORRECT_INPUT ");
        }

        //If fromDate and toDate falls in every quarter-hour
        if (!quarterHours.contains(this.fromDateTime.getMinute()) || !quarterHours.contains(this.toDateTime.getMinute())) {
            throw new IncorrecInputException("INCORRECT_INPUT ");
        }

        //Check if fromDate falls between buffered dates
        bufferTimes.forEach(this::checkIfItFallsBetweenBufferWindow);


    }

    private void checkIfItFallsBetweenBufferWindow(BufferTime bufferTime) throws IncorrecInputException {
        if (this.fromDateTime.isAfter(bufferTime.getFromDate())
                && this.fromDateTime.isBefore(bufferTime.getToDate())) {
            throw new IncorrecInputException("NO_VACANT_ROOM");
        }
        if (this.toDateTime.isAfter(bufferTime.getFromDate())
                && this.toDateTime.isBefore(bufferTime.getToDate())) {
            throw new IncorrecInputException("NO_VACANT_ROOM");
        }
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }


    public LocalDateTime getToDateTime() {
        return toDateTime;
    }



    boolean anyMeetingsExistsBetween(Meeting newMeeting) {
        LocalDateTime fromDateTime = newMeeting.getFromDateTime();
        LocalDateTime toDateTime = newMeeting.getToDateTime();

        if (checkIfAnyMeetingsScheduled(fromDateTime, toDateTime)) return true;
        return false;
    }

    private boolean checkIfAnyMeetingsScheduled(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        if ((fromDateTime.isAfter(getFromDateTime())
                || fromDateTime.isEqual(getFromDateTime()))
                && fromDateTime.isBefore(getToDateTime())) {
            return true;
        }

        if (toDateTime.isAfter(getFromDateTime())
                && (toDateTime.isBefore(getToDateTime()))
                || toDateTime.isEqual(getToDateTime())) {
            return true;
        }
        return false;
    }
}
