package com.company;

public class TicketAutomat
{
    ParkhausController controller;

    public void besucherAusfahrt(ParkhausTicket b)
    {
        controller.besucherAusfahrt(b);
    }

    public void besucherEinfahrt()
    {
        controller.besucherEinfahrt();
    }
}
