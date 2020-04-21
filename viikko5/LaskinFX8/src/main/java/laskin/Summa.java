package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    /*private void naytaNollausNappi() {
        if ( sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }*/

    @Override
    public void suorita() {
        try {
            int arvo = Integer.parseInt(syotekentta.getText());
            sovellus.plus(arvo);
            syotekentta.setText("");
            tuloskentta.setText("" + sovellus.tulos());
            naytaNollausNappi();
        } catch (Exception e) {
        }
    }

    @Override
    public void peru() {

    }
}

