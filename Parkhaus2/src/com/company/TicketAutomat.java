package com.company;

public class TicketAutomat
{
    ParkhausController controller;
    public TicketAutomat(ParkhausController c){
        controller = c;
    }
    public void besucherAusfahrt(ParkhausTicket b)
    {
        controller.besucherAusfahrt(b);
    }

    public void besucherEinfahrt()
    {
        controller.besucherEinfahrt();
    }
}
