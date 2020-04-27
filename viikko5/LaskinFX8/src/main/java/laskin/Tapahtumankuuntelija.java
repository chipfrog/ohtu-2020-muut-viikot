package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka sovellus;

    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            updateNumbers(komento);
            edellinen = komento;
        } else {
            edellinen.peru();
            updateNumbers(edellinen);
            edellinen = null;
        }
    }

    public void updateNumbers(Komento k) {
        k.syotekentta.setText("");
        k.tuloskentta.setText("" + sovellus.tulos());
        naytaNollausNappi(k);
    }
    public void naytaNollausNappi(Komento k) {
        if ( sovellus.tulos() == 0) {
            k.nollaa.disableProperty().set(true);
        } else {
            k.nollaa.disableProperty().set(false);
        }
    }
}
