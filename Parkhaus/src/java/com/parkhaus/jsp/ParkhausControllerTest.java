package com.parkhaus.jsp;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausControllerTest
{    
    static ParkhausController controller = ParkhausController.GetInstance();

    @org.junit.jupiter.api.Test
    void setNewParkingFee()
    {
        assertEquals(new BigDecimal("1.50"), controller.model.currentParkingFeePerHour);
        controller.setNewParkingFee(new BigDecimal("2.00"));
        assertEquals(new BigDecimal("2.00"), controller.model.currentParkingFeePerHour);
    }

    @org.junit.jupiter.api.Test
    void besucherEinfahrt()
    {
        for (int i = 0; i < 10; i++)
        {
            controller.besucherEinfahrt(i);
            assertEquals(i + 1, controller.model.derzeitigeBesucherzahl());
        }
        controller.besucherEinfahrt(1);
        assertEquals(10, controller.model.derzeitigeBesucherzahl());
    }

    @org.junit.jupiter.api.Test
    void besucherBezahlt()
    {
        controller.besucherEinfahrt(0);

        assertFalse(controller.model.aktuelleBesucher[0].ticketBezahlt);
        controller.besucherBezahlt(0);
        assertTrue(controller.model.aktuelleBesucher[0].ticketBezahlt);

        assertEquals("1.50",controller.view.getEarnings(controller.model.insgesamteBesucher,FilterAbstände.Tag));
        assertEquals("1.50",controller.view.getEarnings(controller.model.insgesamteBesucher,FilterAbstände.Woche));
        assertEquals("1.50",controller.view.getEarnings(controller.model.insgesamteBesucher,FilterAbstände.Monat));
        assertEquals("1.50",controller.view.getEarnings(controller.model.insgesamteBesucher,FilterAbstände.Jahr));
    }

    @org.junit.jupiter.api.Test
    void besucherAusfahrt()
    {
        assertEquals(0, controller.model.derzeitigeBesucherzahl());
        controller.besucherEinfahrt(0);
        assertEquals(1, controller.model.derzeitigeBesucherzahl());
        controller.besucherAusfahrt(0);
        assertEquals(1, controller.model.derzeitigeBesucherzahl());
        controller.besucherBezahlt(0);
        controller.besucherAusfahrt(0);
        assertEquals(0, controller.model.derzeitigeBesucherzahl());
    }
}