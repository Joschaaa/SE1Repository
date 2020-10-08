package com.parkhaus.jsp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket 
{
    public int id;
    public String enterTime;
    public String leaveTime;  
    public float duration;
    
    final float pricePerSecond = 1.0f;
    public float price;

    public Ticket(int id)
    {
        this.id = id;
        Date date= new Date();
        //getTime() returns current time in milliseconds
	long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class 
	Timestamp ts = new Timestamp(time);
        enterTime = ts.toString();        
    }
    
    public void Leave()
    {
        Date date = new Date();
        //getTime() returns current time in milliseconds
	long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class 
	Timestamp ts = new Timestamp(time);
        leaveTime = ts.toString();
        duration = 1.1f;
        price = 1;
    } 
}
