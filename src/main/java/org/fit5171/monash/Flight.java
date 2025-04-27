package org.fit5171.monash;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

public class Flight {
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    Airplane airplane;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Flight(){}

    public Flight(int flight_id, String departTo, String departFrom, String code, String company, Timestamp dateFrom,Timestamp dateTo, Airplane airplane)
    {
        if (flight_id <= 0) throw new IllegalArgumentException("Flight ID must be positive");
        if (departFrom == null || departFrom.isBlank()) throw new IllegalArgumentException("departFrom is required");
        if (departTo   == null || departTo.isBlank())   throw new IllegalArgumentException("departTo is required");
        if (code       == null || code.isBlank())       throw new IllegalArgumentException("code is required");
        if (company    == null || company.isBlank())    throw new IllegalArgumentException("company is required");
        if (dateFrom == null) throw new IllegalArgumentException("dateFrom is required");
        if (dateTo   == null) throw new IllegalArgumentException("dateTo is required");
        if (airplane == null) throw new IllegalArgumentException("airplane is required");

        validateTimestamp(dateFrom, "dateFrom");
        validateTimestamp(dateTo, "dateTo");

        this.flightID=flight_id;
        this.departTo = departTo;
        this.departFrom = departFrom;
        this.code = code;
        this.company = company;
        this.airplane = airplane;
        if (!dateFrom.before(dateTo)) {
            throw new IllegalArgumentException("Time/Date of departure of the flight  must be before Arrival Date/Time of the flight");
        }
        this.dateFrom = dateFrom;
        this.dateTo   = dateTo;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightid) {
        if (flightid <= 0) throw new IllegalArgumentException("Flight ID must be positive");
        this.flightID = flightid;
    }

    public String getDepartureDate() {
        return dateFrom.toLocalDateTime().toLocalDate().format(DATE_FMT);
    }
    public String getDepartTo() {
        return departTo;
    }

    public void setDepartTo(String departTo) {

        if (departTo == null || departTo.isBlank())
            throw new IllegalArgumentException("departFrom is required");
        this.departTo = departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {

        if (departFrom   == null || departFrom.isBlank())
            throw new IllegalArgumentException("departTo is required");
        this.departFrom = departFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code       == null || code.isBlank())       throw new IllegalArgumentException("code is required");

        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company    == null || company.isBlank())    throw new IllegalArgumentException("company is required");
        this.company = company;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom)
    {
        validateTimestamp(dateFrom, "dateFrom");

        if (dateFrom == null) throw new IllegalArgumentException("dateFrom is required");
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        validateTimestamp(dateTo, "dateTo");
        if (dateTo   != null && !dateFrom.before(dateTo))
        {
            throw new IllegalArgumentException("Time/Date of departure of the flight  must be before Arrival Date/Time of the flight");
        }

        if (dateTo   == null) throw new IllegalArgumentException("dateTo is required");
        this.dateTo = dateTo;
    }

    public void setAirplane(Airplane airplane) {


        if (airplane == null) throw new IllegalArgumentException("airplane is required");
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String toString()
    {
        return "Flight{" + airplane.toString() +
                ", date to=" +  getDateTo() + ", " + '\'' +
                ", date from='" + getDateFrom() + '\'' +
                ", depart from='" + getDepartFrom() + '\'' +
                ", depart to='" + getDepartTo() + '\'' +
                ", code=" + getCode() + '\'' +
                ", company=" + getCompany() + '\'' +
                ", code=" + getCode() + '\'' +
                '}';
    }

    private void validateTimestamp(Timestamp ts, String fieldName) {
        LocalDateTime dt = ts.toLocalDateTime();
        String datePart = dt.toLocalDate().format(DATE_FMT);
        String timePart = dt.toLocalTime().format(TIME_FMT);
        if (!datePart.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException(fieldName + " must be in YYYY-MM-DD format");
        }
        if (!timePart.matches("\\d{2}:\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException(fieldName + " time must be in HH:MM:SS format");
        }
    }
}
