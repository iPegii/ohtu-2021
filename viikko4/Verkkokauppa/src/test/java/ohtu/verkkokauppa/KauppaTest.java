package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

    public class KauppaTest{

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    
    @Before
    public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
    
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett� pankin metodia tilisiirto on kutsuttu

        //(String nimi, int viitenumero, String tililta, String tilille, int summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        // toistaiseksi ei v�litetty kutsussa k�ytetyist� parametreista
    }

    @Test
    public void ostetaanKaksiEriTuotetta() {

        /*
        aloitetaan asiointi, koriin lisätään kaksi eri tuotetta,
         joita varastossa on ja suoritetaan ostos,
          varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
         */
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "keksi", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli keksi
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett� pankin metodia tilisiirto on kutsuttu

        //(String nimi, int viitenumero, String tililta, String tilille, int summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(10));   
        // toistaiseksi ei v�litetty kutsussa k�ytetyist� parametreista
    }

    @Test
    public void ostetaanKaksiSamaaTuotetta() {

        /*
        aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta
         jota on varastossa tarpeeksi ja suoritetaan ostos,
          varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
         */
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 2 eli keksi
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett� pankin metodia tilisiirto on kutsuttu

        //(String nimi, int viitenumero, String tililta, String tilille, int summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(10));   
        // toistaiseksi ei v�litetty kutsussa k�ytetyist� parametreista
    }

    @Test
    public void ostetaanKaksiTuotettaJoistaToinenOnLoppu() {

        /*
        aloitetaan asiointi, koriin lisätään tuote,
         jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos,
          varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
         */
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "loppunutKeksi", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli keksi
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett� pankin metodia tilisiirto on kutsuttu

        //(String nimi, int viitenumero, String tililta, String tilille, int summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        // toistaiseksi ei v�litetty kutsussa k�ytetyist� parametreista
    }
}