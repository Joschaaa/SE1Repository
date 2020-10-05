package com.company;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public void besucherEinfahrt()
    {
        if(model.insgesamteBesucher.size() < model.maxBesucher)
        {
            model.aktuelleBesucher.add(new ParkhausTicket());
            //Update view
        }
        //else
        // "Parkhaus voll!"
    }

    public void besucherBezahlt(ParkhausTicket besucher)
    {
        besucher.ticketBezahlt = true;
        LocalDateTime currentTime = LocalDateTime.now();
        model.insgesamteBesucher.add(new ParkhausBezahlung(besucher.timeEnter, currentTime,model.calculateTicketPrice(besucher.timeEnter, LocalDateTime.now())));
        //Update view
    }

    public void besucherAusfahrt(ParkhausTicket besucher)
    {
        if(besucher.ticketBezahlt)
        {
            model.aktuelleBesucher.remove(besucher);
            //Update view
            return;
        }

        //"Nicht bezahlt!!"
    }

    public void viewIncomeList()
    {

    }

    public void viewCurrentlyParkedList()
    {

    }

    public void viewExitList()
    {

    }

    public void viewCameras()
    {

    }


}
