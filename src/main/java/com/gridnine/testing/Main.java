package com.gridnine.testing;


import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> departureToTheCurrentPointBeforeTime = new FlightFilterImpl(flights)
                .departureToTheCurrentPointBeforeTime();
        List<Flight> existSegmentsWithArrivalDateEarlierThanDepartureDate = new FlightFilterImpl(flights)
                .existSegmentsWithArrivalDateEarlierThanDepartureDate();
        List<Flight> totalTimeSpentOnTheGroundExceedsTwoHours = new FlightFilterImpl(flights)
                .totalTimeSpentOnTheGroundExceedsTwoHours();

        System.out.println("Unfiltered flies:\n" + flights);
        System.out.println("Departure before now:\n" + departureToTheCurrentPointBeforeTime);
        System.out.println("Arrival before departure:\n" + existSegmentsWithArrivalDateEarlierThanDepartureDate);
        System.out.println("Time on the ground is more than two hours:\n" + totalTimeSpentOnTheGroundExceedsTwoHours);
    }

}
