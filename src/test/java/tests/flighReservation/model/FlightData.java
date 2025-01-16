package tests.flighReservation.model;

public record FlightData(String firstName,
                         String lastName,
                         String email,
                         String password,
                         String street,
                         String city,
                         String zip,
                         String passengersCount,
                         String expectedPrice) {
}
