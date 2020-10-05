package com.company;

public class BezahlAutomat
{
    ParkhausController controller;

    public void besucherBezahlt(ParkhausTicket besucher)
    {
        controller.besucherBezahlt(besucher);
    }
}
