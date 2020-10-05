package com.company;

import java.math.BigDecimal;

public class ParkhausController
{
    ParkhausModel model;
    ParkhausView view;

    public ParkhausController(ParkhausModel model, ParkhausView view)
    {
        this.model = model;
        this.view = view;
    }

    public void setNewParkingFee(BigDecimal newParkingFee)
    {
        model.currentParkingFeePerHour = newParkingFee;
    }

    public void setNewMonthlyParkingTicketPrice(BigDecimal newMonthlyParkingTicketPrice)
    {
        model.currentMonthlyParkingTicketPricePerHour = newMonthlyParkingTicketPrice;
    }

    public void addBesucher(ParkhausEinfahrt besucher)
    {
        model.aktuelleBesucher.add(besucher);
    }

    public void removeBesucher(ParkhausEinfahrt besucher)
    {
        model.aktuelleBesucher.remove(besucher);
    }

    public ParkhausEinfahrt getBesucher(int besucher)
    {
        return model.aktuelleBesucher.get(besucher);
    }

    public void viewIncomeList()
    {

    }

    public void viewCameras()
    {

    }

    //Neue Ticketmaschinenklasse mit Referenz zu diesem Controller für Einparken und ausparken mit und ohne prepaid tickets
}
