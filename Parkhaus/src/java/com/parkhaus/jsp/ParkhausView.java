package com.parkhaus.jsp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ParkhausView 
{    
    public String updateTicketList(ArrayList<ParkhausBezahlung> bezahlungen)
    {
        String output = 
        "<tr>\n" +
        "<th>Einfahrt</th>\n" +
        "<th>Ausfahrt</th>\n" +
        "<th>Preis</th>\n" +
        "</tr>";
        
        for (int i = 0; i < bezahlungen.size(); i++) 
        {
            if (bezahlungen.get(i).timeExit != null)
                output += generateTicketHTMLText(bezahlungen.get(i));
        }
        
        return output;
    }
    
    public String generateTicketHTMLText(ParkhausBezahlung ticket)
    {
        String output = 
        "<tr>\n" + 
        "<td>" + ticket.timeEnter + "</td>\n" + 
        "<td>" + ticket.timeExit + "</td>\n" + 
        "<td>" + String.format("%.2f", ticket.moneyPaid) + "€</td>\n" + 
        "</tr>";
        
        return output;
    }
    
    public String getEarnings(ArrayList<ParkhausBezahlung> bezahlungen,FilterAbstände filter)
    {
        BigDecimal einnnahmen = new BigDecimal(0);
        LocalDateTime checkTime = LocalDateTime.now();

        List<ParkhausBezahlung> filteredList = null;

        switch (filter)
        {
            case Tag:
                filteredList = bezahlungen.stream().
                        filter(a->a.timeEnter.toLocalDate().compareTo(checkTime.toLocalDate()) == 0).
                        collect(Collectors.toList());
                break;
            case Woche:
                filteredList = bezahlungen.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        filter(a->a.timeEnter.getMonth() == checkTime.getMonth()).
                        filter(a-> a.timeEnter.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == checkTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR)).
                        filter(a->a.timeEnter.getDayOfWeek() == checkTime.getDayOfWeek()).
                        collect(Collectors.toList());
                break;
            case Monat:
                filteredList = bezahlungen.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        filter(a->a.timeEnter.getMonth() == checkTime.getMonth()).
                        collect(Collectors.toList());
                break;
            case Jahr:
                filteredList = bezahlungen.stream().
                        filter(a->a.timeEnter.getYear() == checkTime.getYear()).
                        collect(Collectors.toList());
                break;
            default:
                break;
        }

        if(filteredList.isEmpty()) return "";

        for (int i = 0; i < filteredList.size(); i++)
        {
            einnnahmen = einnnahmen.add(filteredList.get(i).moneyPaid);
        }

        return String.format("%.2f", einnnahmen);
    }
}
