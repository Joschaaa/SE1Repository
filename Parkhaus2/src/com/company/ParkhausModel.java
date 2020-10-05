package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum FilterAbstände { Tag, Monat, Jahr }

public class ParkhausModel
{
    public ArrayList<ParkhausTicket> aktuelleBesucher;
    public ArrayList<ParkhausBezahlung> insgesamteBesucher;
    public int maxBesucher = 8;

    public BigDecimal currentParkingFeePerHour = new BigDecimal("1.50");

    public BigDecimal getAktuelleEinnahmen(FilterAbstände filter)
    {
        BigDecimal einnahmen = new BigDecimal(0);
        LocalDateTime checkTime = LocalDateTime.now();

        List<ParkhausBezahlung> filteredList = null;

        switch (filter)
        {
            case Tag:
                filteredList = insgesamteBesucher.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        filter(a->a.timeEnter.getMonth() == checkTime.getMonth()).
                        filter(a->a.timeEnter.getDayOfWeek() == checkTime.getDayOfWeek()).
                        collect(Collectors.toList());
                break;
            case Monat:
                filteredList = insgesamteBesucher.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        filter(a->a.timeEnter.getMonth() == checkTime.getMonth()).
                        collect(Collectors.toList());
                break;
            case Jahr:
                filteredList = insgesamteBesucher.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        collect(Collectors.toList());
                break;
            default:
                break;
        }

        if(filteredList.size() == 0) return new BigDecimal(0);

        for (int i = 0; i < filteredList.size(); i++)
        {
            einnahmen = einnahmen.add(filteredList.get(i).moneyPaid);
        }

        return einnahmen;
    }

    public BigDecimal calculateTicketPrice(LocalDateTime enterTime, LocalDateTime exitTime)
    {
        return currentParkingFeePerHour.multiply(new BigDecimal(enterTime.until( exitTime, ChronoUnit.HOURS )));
    }
}

class ParkhausTicket
{
    public ParkhausTicket()
    {
        timeEnter = LocalDateTime.now();
    }

    public LocalDateTime timeEnter;
    public boolean ticketBezahlt;
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