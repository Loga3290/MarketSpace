package com.example.geektrust;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceManagementIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUp() {
        Main.initializeRooms();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void after() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void testBookAvailableRoom() {
        Main.executeCommand("BOOK 11:00 11:45 2");
        assertEquals("C-Cave", outContent.toString().trim());
    }

    @Test
    public void testThrowNoVacantRoomWhenMeetingFallsInBufferTime() {
        Main.executeCommand("BOOK 13:00 13:45 2");
        assertEquals("NO_VACANT_ROOM", outContent.toString().trim());
    }

    @Test
    public void testThrowIncorrectInputWhenMeetingIsNotAPerfectQuarterTime() {
        Main.executeCommand("BOOK 13:05 13:45 2");
        assertEquals("INCORRECT_INPUT", outContent.toString().trim());
    }

    @Test
    public void testThrownNoVacantRoomIfAllRoomsAreBooked() {
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");
        assertEquals("C-Cave\r\nD-Tower\r\nG-Mansion\r\nNO_VACANT_ROOM", outContent.toString().trim());
    }

    @Test
    public void testBookTowerIfCaveHasAnotherMeetingTheSameTime() {
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");

        assertEquals("C-Cave\r\nD-Tower", outContent.toString().trim());
    }

    @Test
    public void testVacancyGivesAllTheRoomsAvailable() {
        Main.executeCommand("VACANCY 11:00 11:45");

        assertEquals("C-Cave D-Tower G-Mansion", outContent.toString().trim());
    }

    @Test
    public void testVacancyGivesOnlyTowerAndMansionAsCaveIsOccupiedTheSameTime() {
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("VACANCY 11:00 11:45");

        assertEquals("C-Cave\r\nD-Tower G-Mansion", outContent.toString().trim());
    }

    @Test
    public void testThrowNoVacantRoomWhenMeetingFallsInBufferTimeForVacancyEnquiry() {
        Main.executeCommand("VACANCY 13:00 13:45");
        assertEquals("NO_VACANT_ROOM", outContent.toString().trim());
    }

    @Test
    public void testThrowIncorrectInputWhenMeetingIsNotAPerfectQuarterTimeForVacancyEnquiry() {
        Main.executeCommand("VACANCY 13:05 13:45");
        assertEquals("INCORRECT_INPUT", outContent.toString().trim());
    }

    @Test
    public void testThrownNoVacantRoomIfAllRoomsAreBookedForVacancyEnquiry() {
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("BOOK 11:00 11:45 2");
        Main.executeCommand("VACANCY 11:00 11:45");
        assertEquals("C-Cave\r\nD-Tower\r\nG-Mansion\r\nNO_VACANT_ROOM", outContent.toString().trim());
    }

    @Test
    public void testThrowIncorrectInputWhenCommandIsUnexpected() {
        Main.executeCommand("TEST 13:05 13:45 2");
        assertEquals("INCORRECT_INPUT", outContent.toString().trim());
    }
}