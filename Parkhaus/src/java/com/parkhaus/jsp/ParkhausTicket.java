package com.parkhaus.jsp;

import java.time.LocalDateTime;

public class ParkhausTicket
{
    public ParkhausTicket()
    {
        timeEnter = LocalDateTime.now();
    }

    public LocalDateTime timeEnter;
    public boolean ticketBezahlt;
}