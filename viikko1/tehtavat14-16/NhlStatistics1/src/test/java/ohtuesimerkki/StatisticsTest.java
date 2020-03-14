package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    @Test
    public void palauttaaNullJosPelaajaaEiLoydy() {
        assertEquals(null, stats.search("Peltonen"));
    }

    @Test
    public void palauttaaHaetunPelaajan() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }

    @Test
    public void palauttaaHaetunJoukkueenPelaajat() {
        List<Player> pelaajat = stats.team("EDM");
        assertEquals(3, pelaajat.size());
        assertEquals("EDM", pelaajat.get(0).getTeam());
        assertEquals("EDM", pelaajat.get(1).getTeam());
        assertEquals("EDM", pelaajat.get(2).getTeam());

    }

    @Test
    public void palauttaaKaksiEnitenPistettaSaanuttaPelaajaa() {
        List<Player> pelaajat = stats.topScorers(2);
        assertEquals("Gretzky", pelaajat.get(0).getName());
        assertEquals("Lemieux", pelaajat.get(1).getName());
    }


}
