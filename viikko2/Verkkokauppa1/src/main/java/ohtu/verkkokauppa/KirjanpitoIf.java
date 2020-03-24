package ohtu.verkkokauppa;

import java.util.ArrayList;


public interface KirjanpitoIf {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
