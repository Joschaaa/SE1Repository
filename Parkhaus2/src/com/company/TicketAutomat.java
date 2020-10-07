package com.company;

public class TicketAutomat //Der Automat an dem das Ticket gezogen wird beim Einfahren
{
    ParkhausController controller;

    public TicketAutomat(ParkhausController controller)
    {
        this.controller = controller;
    }

    public void besucherAusfahrt(ParkhausTicket besucher)
    {
        controller.besucherAusfahrt(besucher);
    }

    public void besucherEinfahrt()
    {
        controller.besucherEinfahrt();
    }
}
