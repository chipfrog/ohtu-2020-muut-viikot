
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private String nationality;
    private String birthday;
    private int goals;
    private int assists;
    private int penalties;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getTotalPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return name + "  team  " + team + " " + goals + " + " + assists + " = " + getTotalPoints();
    }

    @Override
    public int compareTo(Player p) {
        return p.getTotalPoints() - getTotalPoints();
    }
}
