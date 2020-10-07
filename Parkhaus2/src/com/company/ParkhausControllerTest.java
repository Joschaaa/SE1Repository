package com.company;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausControllerTest
{
    ParkhausController controller = new ParkhausController(new ParkhausModel(), new ParkhausView());

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
        for (int i = 0; i < 8; i++)
        {
            controller.ticketAutomat.besucherEinfahrt();
            assertEquals(i + 1, controller.model.aktuelleBesucher.size());
        }
        controller.ticketAutomat.besucherEinfahrt();
        assertEquals(8, controller.model.aktuelleBesucher.size());
    }

    @org.junit.jupiter.api.Test
    void besucherBezahlt()
    {
        controller.ticketAutomat.besucherEinfahrt();

        assertFalse(controller.model.aktuelleBesucher.get(0).ticketBezahlt);
        controller.bezahlAutomat.besucherBezahlt(controller.model.aktuelleBesucher.get(0));
        assertTrue(controller.model.aktuelleBesucher.get(0).ticketBezahlt);

        assertEquals(new BigDecimal("1.50"),controller.model.getAktuelleEinnahmen(FilterAbstände.Tag));
        assertEquals(new BigDecimal("1.50"),controller.model.getAktuelleEinnahmen(FilterAbstände.Woche));
        assertEquals(new BigDecimal("1.50"),controller.model.getAktuelleEinnahmen(FilterAbstände.Monat));
        assertEquals(new BigDecimal("1.50"),controller.model.getAktuelleEinnahmen(FilterAbstände.Jahr));
    }

    @org.junit.jupiter.api.Test
    void besucherAusfahrt()
    {
        assertEquals(0, controller.model.aktuelleBesucher.size());
        controller.ticketAutomat.besucherEinfahrt();
        assertEquals(1, controller.model.aktuelleBesucher.size());
        controller.ticketAutomat.besucherAusfahrt(controller.model.aktuelleBesucher.get(0));
        assertEquals(1, controller.model.aktuelleBesucher.size());
        controller.bezahlAutomat.besucherBezahlt(controller.model.aktuelleBesucher.get(0));
        controller.ticketAutomat.besucherAusfahrt(controller.model.aktuelleBesucher.get(0));
        assertEquals(0, controller.model.aktuelleBesucher.size());
    }
}