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
                1, "Perth", "Melbourne", "QUP", "Quantus",
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


}
