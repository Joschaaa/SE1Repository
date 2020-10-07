package com.company;

public class BezahlAutomat
{
    ParkhausController controller;
    public BezahlAutomat(ParkhausController c){
        controller = c;
    }

    public void besucherBezahlt(ParkhausTicket besucher)
    {
        controller.besucherBezahlt(besucher);
    }
}
