package com.parkhaus.jsp;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ParkhausController 
{
    ParkhausModel model;
    ParkhausView view;
    
    static ParkhausController _instance;
    
    public static ParkhausController GetInstance()
    {
        if (_instance == null)
            _instance = new ParkhausController(new ParkhausModel(), new ParkhausView());
        
        return _instance;
    }
    
    public ParkhausController(ParkhausModel model, ParkhausView view)
    {
        this.model = model;
        this.view = view;
    }
    
    public void Initialize(int parkingSpaceCount)
    {
        model.aktuelleBesucher = new ParkhausTicket[parkingSpaceCount];
        model.insgesamteBesucher.clear();
    }
    
    public String UpdateTicketList()
    {
        return view.updateTicketList(model.insgesamteBesucher);
    }
    
    public String GetEarnings()
    {
        return view.getEarnings(model.insgesamteBesucher, FilterAbstände.Jahr);
    }
    
    
    public void setNewParkingFee(BigDecimal newParkingFee)
    {
        model.currentParkingFeePerHour = newParkingFee;
    }

    public void besucherEinfahrt(int platz)
    {    
        model.aktuelleBesucher[platz] = new ParkhausTicket();
    }

    public void besucherBezahlt(int platz)
    {
        model.aktuelleBesucher[platz].ticketBezahlt = true;
        LocalDateTime currentTime = LocalDateTime.now();
        model.insgesamteBesucher.add(new ParkhausBezahlung(model.aktuelleBesucher[platz].timeEnter, currentTime, model.calculateTicketPrice(model.aktuelleBesucher[platz].timeEnter, currentTime)));
        //Update view
    }

    public void besucherAusfahrt(int besucherID)
    {
        if(model.aktuelleBesucher[besucherID].ticketBezahlt)
        {
            model.aktuelleBesucher[besucherID] = null;
            return;
        }
    }
}
