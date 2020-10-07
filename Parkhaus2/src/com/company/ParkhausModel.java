package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

enum FilterAbstände { Tag, Woche, Monat, Jahr }

public class ParkhausModel
{
    public ArrayList<ParkhausTicket> aktuelleBesucher = new ArrayList<ParkhausTicket>();
    public ArrayList<ParkhausBezahlung> insgesamteBesucher= new ArrayList<ParkhausBezahlung>();
    public int maxBesucher = 8;
    public BigDecimal currentParkingFeePerHour = new BigDecimal("1.50");

    public BigDecimal getAktuelleEinnahmen(FilterAbstände filter)
    {
        BigDecimal einnnahmen = new BigDecimal(0);
        LocalDateTime checkTime = LocalDateTime.now();

        List<ParkhausBezahlung> filteredList = null;

        switch (filter)
        {
            case Tag:
                filteredList = insgesamteBesucher.stream().
                        filter(a->a.timeEnter.toLocalDate().compareTo(checkTime.toLocalDate()) == 0).
                        collect(Collectors.toList());
                break;
            case Woche:
                filteredList = insgesamteBesucher.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        filter(a->a.timeEnter.getMonth() == checkTime.getMonth()).
                        filter(a-> a.timeEnter.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == checkTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR)).
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
            einnnahmen = einnnahmen.add(filteredList.get(i).moneyPaid);
        }

        return einnnahmen;
    }

    public BigDecimal calculateTicketPrice(LocalDateTime enterTime, LocalDateTime exitTime)
    {
        return currentParkingFeePerHour.multiply(new BigDecimal(enterTime.until( exitTime, ChronoUnit.HOURS )).setScale(0, RoundingMode.UP).max(new BigDecimal("1")));
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