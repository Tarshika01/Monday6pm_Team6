import org.fit5171.monash.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TicketBookingTest {

    private TicketSystem ticketSystem;
    private ArrayList<Ticket> tickets;
    private ArrayList<Flight> flights;
    private Airplane airplane;
    private Ticket ticket1;
    private Ticket ticket2;
    private Flight flight1;
    private Flight flight2;

    private Passenger passenger1;
    private Passenger passenger2;

    @BeforeEach
    public void setup() {

        ticketSystem = new TicketSystem();
        tickets = new ArrayList<>();
        flights = new ArrayList<>();
        TicketCollection.tickets = new ArrayList<>();
        FlightCollection.flights = new ArrayList<>();


        airplane = new Airplane(1, "Boeing 737", 12, 150, 6);
        flight1 = new Flight(
                1, "Perth", "Melbourne", "QUP", "Qantas",
                Timestamp.valueOf("2025-03-24 08:30:00"),
                Timestamp.valueOf("2025-03-24 10:00:00"),
                airplane
        );
        flight2 = new Flight(
                2, "India", "Melbourne", "CP", "Cathay Pacific",
                Timestamp.valueOf("2025-04-01 08:30:00"),
                Timestamp.valueOf("2025-04-01 10:00:00"),
                airplane
        );

        flights.add(flight1);
        flights.add(flight2);


        passenger1 = new Passenger(
                "Shreya", "Shrestha", 23, "Female", "sshresthaa8@gmail.com",
                "0452402924", "V898162", "512469072939", 17
        );

        passenger2 = new Passenger(
                "Tarshika", "D", 80, "Female", "tarshu@gmail.com",
                "0425362888", "V898992", "512289072939", 18
        );



        ticket1 = new Ticket(1, 450, flight1, false, passenger1);
        ticket2 = new Ticket(2, 950, flight2, true, passenger2);

        tickets.add(ticket1);
        tickets.add(ticket2);

    }


    @Test
    @DisplayName("Validate the addition of the Tickets in the Ticket Collection database")
    public void testAddTicket()
    {
        TicketCollection.addTickets(tickets);
        assertEquals(2, TicketCollection.tickets.size());
        assertTrue(TicketCollection.tickets.containsAll(tickets));


    }

    @Test
    @DisplayName("Validate the addition of the Flights in the Flights Collection database")
    public void testAddFlight() {
        FlightCollection.addFlights(flights);
        assertEquals(2, FlightCollection.flights.size());
        assertTrue(FlightCollection.flights.containsAll(flights));
    }


    @Test
    @DisplayName("Validate flight associated with the ticket")
    public void testGetFlightInTicket()
    {
        Flight ticketFlight = ticket1.getFlight();
        assertNotNull(ticketFlight);
        assertEquals("Melbourne", ticketFlight.getDepartFrom());
        assertEquals("QUP", ticketFlight.getCode());
    }




    @Test
    @DisplayName("Validate Airplane associated with the particular flight")
    public void testFlightDetails()
    {
        assertEquals(1, flight1.getFlightID());
        assertEquals("Perth", flight1.getDepartTo());
        assertEquals("Melbourne", flight1.getDepartFrom());
        assertEquals("QUP", flight1.getCode());
        assertEquals("Qantas", flight1.getCompany());
        assertNotNull(flight1.getAirplane());
        assertEquals("Boeing 737", flight1.getAirplane().getAirplaneModel());
    }

    @Test
    @DisplayName("Validate Airplane Getters, Setters, and toString methods")
    public void testAirplaneMethods() {
        assertEquals(1, airplane.getAirplaneID());
        assertEquals("Boeing 737", airplane.getAirplaneModel());
        assertEquals(12, airplane.getBusinessSitsNumber());
        airplane.setBusinessSitsNumber(14);
        assertEquals(14, airplane.getBusinessSitsNumber());
        String airplaneStr = airplane.toString();
        assertTrue(airplaneStr.contains("Boeing 737"));
    }

    @Test
    @DisplayName("Validate the price of the ticket based on age and sales tax")
    public void testPriceByAgeAndSalesTax()
    {
        ticket2.setPrice(ticket2.getPrice());
        ticket1.setPrice(ticket1.getPrice());

        assertEquals(504, ticket1.getPrice());
        assertEquals(0, ticket2.getPrice());
    }

}