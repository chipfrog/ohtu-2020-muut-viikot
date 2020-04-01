
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;    // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        muuttujienAlustus(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        muuttujienAlustus(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti tai kasvatuskoko ei saa olla negatiivinen");
        }
        muuttujienAlustus(kapasiteetti, kasvatuskoko);
    }

    public void muuttujienAlustus(int kapasiteetti, int kasvatuskoko) {
        lukujono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaaLukujonoon(int luku) {
        if (alkioidenLkm == 0) {
            lukujono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukonKokoa();
            return true;
        }
        return false;
    }

    public void kasvataTaulukonKokoa() {
        if (alkioidenLkm % lukujono.length == 0) {
            int[]taulukkoOld = lukujono;
            lukujono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(taulukkoOld, lukujono);
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int poistettavanIndeksi = etsiPoistettavanIndeksi(luku);
        if (poistettavanIndeksi != -1) {
            for (int j = poistettavanIndeksi; j < alkioidenLkm - 1; j++) {
                lukujono[j] = lukujono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }
    
    private int etsiPoistettavanIndeksi(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                indeksi = i;
                lukujono[indeksi] = 0;
                break;
            }
        }
        return indeksi;
    }
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int palautaAlkioidenLukumaara() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujono[0] + "}";
        } else {
            String luvut = "";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                luvut += lukujono[i] + ", ";
            }
            String tuloste = "{" + luvut + lukujono[alkioidenLkm - 1] + "}";;
            return tuloste;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaaLukujonoon(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaaLukujonoon(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaaLukujonoon(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaaLukujonoon(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(bTaulu[i]);
        }
 
        return erotusJoukko;
    }
        
}
