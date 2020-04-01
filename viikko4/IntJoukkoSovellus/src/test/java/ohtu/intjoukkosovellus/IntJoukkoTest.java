package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaaLukujonoon(10);
        joukko.lisaaLukujonoon(3);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaaLukujonoon(4);
        assertEquals(3, joukko.palautaAlkioidenLukumaara());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaaLukujonoon(10);
        joukko.lisaaLukujonoon(3);
        assertEquals(2, joukko.palautaAlkioidenLukumaara());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.kuuluu(10));
        assertFalse(joukko.kuuluu(5));
        assertTrue(joukko.kuuluu(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.kuuluu(3));
        assertEquals(1, joukko.palautaAlkioidenLukumaara());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        joukko.lisaaLukujonoon(55);
        joukko.poista(10);
        joukko.lisaaLukujonoon(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            joukko.lisaaLukujonoon(luku);
        }
        assertEquals(14, joukko.palautaAlkioidenLukumaara());
        assertTrue(joukko.kuuluu(11));
        joukko.poista(11);
        assertFalse(joukko.kuuluu(11));
        assertEquals(13, joukko.palautaAlkioidenLukumaara());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", joukko.toString());
    }
    
    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla(){
        joukko = new IntJoukko();
        joukko.lisaaLukujonoon(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla(){
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }     
}
