package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.http.client.fluent.Request;


public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        ArrayList<Player> pelaajat = new ArrayList<>();

        for (Player player : players) {
            if (player.getNationality().equals("FIN")){
                pelaajat.add(player);
            }
        }
        Collections.sort(pelaajat);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Players from Finland " + sdf.format(date) + "\n");

        for (Player p : pelaajat) {
            System.out.println(p);
        }
    }

}
