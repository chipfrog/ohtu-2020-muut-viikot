package ohtu.verkkokauppa;

public interface PankkiIf {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
