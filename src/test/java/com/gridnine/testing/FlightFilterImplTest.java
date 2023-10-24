package com.gridnine.testing;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class for testing
 */

public class FlightFilterImplTest {

    private final List<Flight> flights = new ArrayList<>();
    private final FlightFilter flightFilter = new FlightFilterImpl(flights);
    LocalDateTime time = LocalDateTime.now();

    @Test
    public void departureToTheCurrentPointBeforeTimeTest() {
        System.out.println("Test of the deviation display method for the current time.");
        List<Flight> filteredFlights = flightFilter.departureToTheCurrentPointBeforeTime();
        assertTrue(filteredFlights.isEmpty());

    }

    @Test
    public void existSegmentsWithArrivalDateEarlierThanDepartureDateTest() {
        System.out.println("Test of the method of displaying flights with arrival date earlier than departure date. ");
        List<Flight> filteredFlights = flightFilter.existSegmentsWithArrivalDateEarlierThanDepartureDate();
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void filterTotalTimeOnGroundExceedingTwoHours() {
        System.out.println("Method test for filtering flights with a total time on the ground of more than two hours");
        Segment segment1 = new Segment(time.plusHours(2), time);
        Segment segment2 = new Segment(time.plusHours(2).plusMinutes(2), time.plusHours(6));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        List<Flight> filteredFlights = flightFilter.totalTimeSpentOnTheGroundExceedsTwoHours();
        assertTrue(filteredFlights.isEmpty());
    }
}