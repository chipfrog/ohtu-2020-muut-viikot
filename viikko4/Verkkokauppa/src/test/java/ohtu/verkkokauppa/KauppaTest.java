package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanJaVerifioidaanOikeatArvot() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));
    }

    @Test
    public void koriinLisataanKaksiVarastossaOlevaaEriTuotettaJaSuoritetaanOstosJaKutsutaanMetodiaTilisiirtoOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(25);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Makkara", 4));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Arska", "0000");

        verify(pankki).tilisiirto(eq("Arska"), eq(25), eq("0000"), eq("33333-44455"), eq(6));
    }

    @Test
    public void koriinLisataanKaksiSamaaVarastossaOlevaaTuotettaJaSuoritetanOstosJaKutsutaanMetodiaTilisiirtoOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(25);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("Arska", "0000");

        verify(pankki).tilisiirto(eq("Arska"), eq(25), eq("0000"), eq("33333-44455"), eq(4));

    }

    @Test
    public void koriinLisataanVarastossaOlevaTuoteSekäLoppunutTuoteJaSuoritetaanOstosjaKutsutanMetodiaTilisiirtoOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(25);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Karhu", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Arska", "0000");

        verify(pankki).tilisiirto(eq("Arska"), eq(25), eq("0000"), eq("33333-44455"), eq(2));

    }

    @Test
    public void metodiAloitAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(25);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Karhu", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Arska", "0000");

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("Arska", "0000");
        verify(pankki).tilisiirto(eq("Arska"), eq(25), eq("0000"), eq("33333-44455"), eq(3));

    }

    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksutapahtumalle() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Arska", "0000");

        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Arska", "0000");

        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Arska", "0000");

        verify(viite, times(3)).uusi();

    }

    @Test
    public void lisatynTuotteenVoiPoistaaKoristaJaSeEiNayEnaaOstoksessa() {
        when(viite.uusi()).thenReturn(25);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff", 2));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Karhu", 3));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(1);
        k.tilimaksu("Arska", "0000");

        verify(pankki).tilisiirto(eq("Arska"), eq(25), eq("0000"), eq("33333-44455"), eq(3));

    }
}
