package com.marktplaats.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class GebruikerTest {

    @Test
    void getLeeftijd() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setGeboorteDatum(LocalDate.parse("2004-10-12"));
        Gebruiker gebruiker2 = new Gebruiker();
        gebruiker2.setGeboorteDatum(LocalDate.parse("2004-12-12"));
/*        Assert.assertTrue(gebruiker.getLeeftijd() >= 18);
        Assert.assertFalse(gebruiker2.getLeeftijd() >= 18);*/
    }
}