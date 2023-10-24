package com.gridnine.testing;


import java.util.List;

public interface FlightFilter {
    List<Flight> allFlightSegments();

    List<Flight> departureToTheCurrentPointBeforeTime();

    List<Flight> existSegmentsWithArrivalDateEarlierThanDepartureDate();

    List<Flight> totalTimeSpentOnTheGroundExceedsTwoHours();
}
