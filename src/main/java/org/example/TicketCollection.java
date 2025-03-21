package org.example;

import java.util.ArrayList;

public class TicketCollection {

    public static ArrayList<org.example.Ticket> tickets;

    public static ArrayList<org.example.Ticket> getTickets() {
        return tickets;
    }

    public static void addTickets(ArrayList<org.example.Ticket> tickets_db) {
        TicketCollection.tickets.addAll(tickets_db);
    }

    public static void getAllTickets() {
        //display all available tickets from the Ticket collection
    }
    public static org.example.Ticket getTicketInfo(int ticket_id) {
        //SELECT a ticket where ticket id = ticket_id
        return null;

    }


}
