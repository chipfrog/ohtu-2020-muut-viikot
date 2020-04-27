package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    int edellinenLuku;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        try {
            edellinenLuku = Integer.parseInt(tuloskentta.getText());
        } catch (Exception exception) {

        }
    }

    @Override
    public void suorita() {
        try {
            undo.disableProperty().set(false);
            edellinenLuku = sovellus.tulos();
            int arvo = Integer.parseInt(syotekentta.getText());
            sovellus.plus(arvo);
        } catch (Exception e) {
        }
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

