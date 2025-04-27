package org.fit5171.monash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Airplane
{
    private int airplaneID;
    private String airplaneModel;
    private int businessSeatNumber;
    private int economySeatNumber;
    private int crewSeatNumber;
    private Map<String, String> seatLayout;
    private String seatNo;

    public Airplane(int airplaneID, String airplaneModel, int businessSeatNumber, int economySeatNumber, int crewSeatNumber)
    {
        if (airplaneID <= 0)
            throw new IllegalArgumentException("Airplane ID must be positive");
        if (businessSeatNumber < 0 || businessSeatNumber > 21) //A1-7 to C1-7
            throw new IllegalArgumentException("Business seats must be between 0 and 21");
        if (economySeatNumber < 0 || economySeatNumber > 28) //D1-7 to H1-7
            throw new IllegalArgumentException("Economy seats must be between 0 and 28");
        if (crewSeatNumber < 0 || crewSeatNumber > 14) //i1-7 to J1-7
            throw new IllegalArgumentException("Crew seats cannot be negative");
        this.airplaneID=airplaneID;
        this.airplaneModel = airplaneModel != null ? airplaneModel : "";
        this.businessSeatNumber = businessSeatNumber;
        this.economySeatNumber = economySeatNumber;
        this.crewSeatNumber = crewSeatNumber;
        createSeatLayout();
        if (seatLayout.size() != 70) {
            throw new IllegalStateException("Seat layout must contain exactly 70 seats");
        }
    }

    private void createSeatLayout()
    {
        seatLayout = new HashMap<>();
        for (char row = 'A'; row <= 'J'; row++)
        {
            for (int num = 1; num <= 7; num++)
            {
                String seatId = row + "-" + num;
                String seatType ="";
                seatLayout.put(seatId, seatType);
            }
        }
    }

    public void setSeatNo(boolean isVip) {
        for (Map.Entry<String, String> e : seatLayout.entrySet())
        {
            String seatId = e.getKey();
            String occupant = e.getValue();
            char row = seatId.charAt(0);

            if (!occupant.isEmpty()) continue;   // already taken

            if (isVip && row >= 'A' && row <= 'C') {
                seatNo = seatId;
                seatLayout.put(seatId, "OCCUPIED");
                return;
            }
            else if (!isVip && row >= 'D' && row <= 'J') {
                seatNo = seatId;
                seatLayout.put(seatId, "OCCUPIED");
                return;
            }
        }
        throw new IllegalStateException(
                "No " + (isVip ? "business" : "economy") + " seats remaining");
    }

    public String getSeatNo() {
        return seatNo;
    }




    public int getAirplaneID() {


        return airplaneID;
    }

    public void setAirplaneID(int airplaneID) {
        if (airplaneID <= 0)
            throw new IllegalArgumentException("Airplane ID must be positive");
        this.airplaneID = airplaneID;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public int getBusinessSeatNumber() {
        return businessSeatNumber;
    }

    public void setBusinessSeatNumber(int businessSeatNumber) {
        if (businessSeatNumber < 0 || businessSeatNumber > 21) //A1-7 to C1-7
            throw new IllegalArgumentException("Business seats must be between 0 and 21");

        this.businessSeatNumber = businessSeatNumber;
    }

    public int getEconomySeatNumber() {
        return economySeatNumber;
    }

    public void setEconomySeatNumber(int economySeatNumber) {
        if (economySeatNumber < 0 || economySeatNumber > 28) //A1-7 to C1-7
            throw new IllegalArgumentException("Economy seats must be between 0 and 21");
        this.economySeatNumber = economySeatNumber;
    }

    public int getCrewSeatNumber() {
        return crewSeatNumber;
    }

    public void setCrewSeatNumber(int crewSeatNumber) {

        if (crewSeatNumber < 0 || crewSeatNumber > 14) //A1-7 to C1-7
            throw new IllegalArgumentException("Crew seats must be between 0 and 21");

        this.crewSeatNumber = crewSeatNumber;
    }

    public String toString()
    {
        return "Airplane{" +
                "model=" + getAirplaneModel() + '\'' +
                ", business sits=" + getBusinessSeatNumber() + '\'' +
                ", economy sits=" + getEconomySeatNumber() + '\'' +
                ", crew sits=" + getCrewSeatNumber() + '\'' +
                '}';
    }

    public static Airplane getAirPlaneInfo(int airplane_id) {

            List<Flight> flights = FlightCollection.getFlights();
            for (Flight f : flights) {
                Airplane a = f.getAirplane();
                if (a != null && a.getAirplaneID() == airplane_id) {
                    return a;
                }
            }
            return null;

    }
}