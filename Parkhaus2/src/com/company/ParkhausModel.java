package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

enum FilterAbstände { Tag, Woche, Monat, Jahr }

public class ParkhausModel
{
    public ArrayList<ParkhausEinfahrt> aktuelleBesucher;
    public ArrayList<ParkhausBezahlung> insgesamteBesucher;

    public BigDecimal currentParkingFeePerHour = new BigDecimal(1.50);
    public BigDecimal currentMonthlyParkingTicketPricePerHour = new BigDecimal(37.49);

    public int getAktuelleBesucherAnzahl()
    {
        return aktuelleBesucher.size();
    }

    public BigDecimal getAktuelleEinnahmen(FilterAbstände filter)
    {
        BigDecimal einnahmen = new BigDecimal(0);

        for (int i = 0; i < insgesamteBesucher.size(); i++)
        {
            einnahmen.add(insgesamteBesucher.get(i).moneyPaid);
        }

        return einnahmen;
    }
}

class ParkhausEinfahrt
{
    public LocalDateTime timeEnter;
}

class ParkhausBezahlung
{
    public ParkhausEinfahrt besucher;
    public LocalDateTime timeExit;
    public BigDecimal moneyPaid = new BigDecimal(0);
}
