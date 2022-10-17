package javaTests;

import com.marktplaats.model.Gebruiker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GebruikerTest {

    @Test
    void IsVolwassen() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setLeeftijd(17);
        Gebruiker gebruiker2 = new Gebruiker();
        gebruiker2.setLeeftijd(20);
        Assert.assertFalse(gebruiker.IsVolwassen());
        Assert.assertTrue(gebruiker2.IsVolwassen());
    }
}