package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento{
    private int edellinenLuku;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        try {
            edellinenLuku = Integer.parseInt(tuloskentta.getText());
        } catch (Exception exception) {

        }
    }

    @Override
    public void suorita() {
        undo.disableProperty().set(false);
        edellinenLuku = sovellus.tulos();
        sovellus.nollaa();
    }

    @Override
    public void peru() {
        try {
            undo.disableProperty().set(true);
            sovellus.setArvo(edellinenLuku);
        } catch (Exception exception) {

        }
    }
}
