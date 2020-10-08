package com.parkhaus.jsp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

enum FilterAbstände { Tag, Woche, Monat, Jahr }

public class ParkhausModel
{
    public ParkhausTicket[] aktuelleBesucher = new ParkhausTicket[14];
    public ArrayList<ParkhausBezahlung> insgesamteBesucher = new ArrayList<ParkhausBezahlung>();
    public int maxBesucher = 8;
    public BigDecimal currentParkingFeePerHour = new BigDecimal("1.50");
    
    public int derzeitigeBesucherzahl() 
    {
        int besucher = 0;
        
        for (int i = 0; i < aktuelleBesucher.length; i++) 
        {
            if(aktuelleBesucher[i] != null)
                besucher++;
        }
        
        return besucher;
    }
    
    public BigDecimal calculateTicketPrice(LocalDateTime enterTime, LocalDateTime exitTime)
    {
        return currentParkingFeePerHour.multiply(new BigDecimal(enterTime.until( exitTime, ChronoUnit.SECONDS )).setScale(0, RoundingMode.UP).max(new BigDecimal("1")));
    }
}

class ParkhausBezahlung
{
    public ParkhausBezahlung(LocalDateTime timeEnter,LocalDateTime timeExit,BigDecimal moneyPaid)
    {
        this.timeEnter = timeEnter;
        this.timeExit = timeExit;
        this.moneyPaid = moneyPaid;
    }

    public LocalDateTime timeEnter;
    public LocalDateTime timeExit;
    public BigDecimal moneyPaid;
}