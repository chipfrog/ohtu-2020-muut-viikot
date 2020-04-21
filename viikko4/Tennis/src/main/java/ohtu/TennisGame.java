package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String scoreOutput = "";

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }
    public String getScore() {

        if (playersHaveEqualScore()) {
            setScoreOutputToEven();
        }
        else if (bothPlayersHaveAtLeast4Points()) {
            setScoreOutputToAdvantageOrWin();
        }
        else {
            setScoreOutputToUneven();
        }
        return scoreOutput;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }
    private boolean bothPlayersHaveAtLeast4Points() {
        if (player1Score >=4 || player2Score >=4) {
            return true;
        }
        return false;
    }
    private boolean playersHaveEqualScore() {
        if (player1Score == player2Score) {
            return true;
        }
        return false;
    }

    private void setScoreOutputToAdvantageOrWin() {
        int scoreDifference = player1Score - player2Score;
        if (scoreDifference==1) {
            scoreOutput ="Advantage player1";
        }
        else if (scoreDifference ==-1) {
            scoreOutput ="Advantage player2";
        }
        else if (scoreDifference>=2) {
            scoreOutput = "Win for player1";
        }
        else {
            scoreOutput ="Win for player2";
        }
    }

    private void setScoreOutputToEven() {
        switch (player1Score) {
            case 0:
                scoreOutput = "Love-All";
                break;
            case 1:
                scoreOutput = "Fifteen-All";
                break;
            case 2:
                scoreOutput = "Thirty-All";
                break;
            case 3:
                scoreOutput = "Forty-All";
                break;
            default:
                scoreOutput = "Deuce";
                break;
        }
    }

    private void setScoreOutputToUneven() {
        switch(player1Score)
        {
            case 0:
                scoreOutput+="Love";
                break;
            case 1:
                scoreOutput+="Fifteen";
                break;
            case 2:
                scoreOutput+="Thirty";
                break;
            case 3:
                scoreOutput+="Forty";
                break;
        }

        switch(player2Score)
        {
            case 0:
                scoreOutput+="-Love";
                break;
            case 1:
                scoreOutput+="-Fifteen";
                break;
            case 2:
                scoreOutput+="-Thirty";
                break;
            case 3:
                scoreOutput+="-Forty";
                break;
        }

    }

}