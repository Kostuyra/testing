package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FlightFilterImpl implements FlightFilter {
    private final List<Flight> flights;

    public FlightFilterImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight>  allFlightSegments() {
        return flights;
    }

    @Override
    public List<Flight> departureToTheCurrentPointBeforeTime() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        );
        return flights;
    }

    @Override
    public List<Flight> existSegmentsWithArrivalDateEarlierThanDepartureDate() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        return flights;
    }

    @Override
    public List<Flight> totalTimeSpentOnTheGroundExceedsTwoHours() {
        flights.removeIf(flight -> {
            ArrayList<Segment> segments = new ArrayList<>(flight.getSegments());
            LocalDateTime curDeparture;
            LocalDateTime lastArrival;
            Duration duration = Duration.ZERO;

            for (int i = 1; i < segments.size(); i++) {
                curDeparture = segments.get(i).getDepartureDate();
                lastArrival = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(curDeparture, lastArrival).abs());
            }
            return duration.toHours() >= 2;
        });
        return flights;
    }

}


