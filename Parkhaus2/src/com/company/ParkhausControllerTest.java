package com.company;
import static org.junit.jupiter.api.Assertions.*;
class ParkhausControllerTest
{
    ParkhausController pc = new ParkhausController(new ParkhausModel(),new ParkhausView());
    @org.junit.jupiter.api.Test
    void setNewParkingFee()
    {
        assertEquals("1.50",pc.model.currentParkingFeePerHour);
        pc.setNewParkingFee("2.00");
        assertEquals("2.00",pc.model.currentParkingFeePerHour);
    }

    @org.junit.jupiter.api.Test
    void besucherEinfahrt()
    {
        for(int i = 1; i<=8; i++){
            pc.ta.besucherEinfahrt();
            assertEquals(i,pc.model.aktuelleBesucher.size());
        }
        pc.ta.besucherEinfahrt();
        assertEquals(8,pc.model.aktuelleBesucher.size());
    }

    @org.junit.jupiter.api.Test
    void besucherBezahlt()
    {
        pc.ta.besucherEinfahrt();

        assertEquals(false,pc.model.aktuelleBesucher[0].bezahlt);
        pc.ba.besucherBezahlt(pc.model.aktuelleBesucher[0]);
        assertEquals(true,pc.model.aktuelleBesucher[0].bezahlt);

    }

    @org.junit.jupiter.api.Test
    void besucherAusfahrt()
    {
        assertEquals(0,pc.model.aktuelleBesucher.size());
        pc.ta.besucherEinfahrt();
        assertEquals(1,pc.model.aktuelleBesucher.size());
        pc.ta.besucherAusfahrt(pc.model.aktuelleBesucher[0]);
        assertEquals(1,pc.model.aktuelleBesucher.size());
        pc.ba.besucherBezahlt(pc.model.aktuelleBesucher[0]);
        pc.ta.besucherAusfahrt(pc.model.aktuelleBesucher[0]);
        assertEquals(0,pc.model.aktuelleBesucher.size());
    }
}