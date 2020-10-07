package com.company;

public class BezahlAutomat //Der Automat an dem das Ticket bezahlt wird bevor man ausfahren will
{
    ParkhausController controller;

    public BezahlAutomat(ParkhausController controller)
    {
        this.controller = controller;
    }

    public void besucherBezahlt(ParkhausTicket besucher)
    {
        controller.besucherBezahlt(besucher);
    }
}
