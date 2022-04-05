package tennisgame;

public class TennisGame1 implements TennisGame {

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(player1Name))
            scorePlayer1 += 1;
        if (playerName.equalsIgnoreCase(player2Name))
            scorePlayer2 += 1;

    }

    public String getScore() {

        StringBuilder score = new StringBuilder();

        if (isEqualScore(scorePlayer1, scorePlayer2))
            getScorePlayer(score);
        else if (isUpTo4Points()) getAdvantage(score);
        else if (isBetween1And3Points())
            forPointsGetResult(score);
        return score.toString();
    }

    private void forPointsGetResult(StringBuilder score) {
        for (int point = 1; point < 3; point++)
            getResult(score, point);
    }

    private void getResult(StringBuilder score, int point){
        int tempScore;
        if (point == 1) tempScore = scorePlayer1;
        else {
            score.append("-");
            tempScore = scorePlayer2;
        }
        getScoreDescription(score, tempScore);
    }

    private void getAdvantage(StringBuilder score){
        if (isDifferenceEqualsOne()) score.append("Advantage player1");
        else if (isDifferenceEqualsMinusOne()) score.append("Advantage player2");
        else if (isDifferenceGreaterOrEqualsTwo(scoreDifference())) score.append("Win for player1");
        else score.append("Win for player2");
    }

    private boolean isDifferenceEqualsMinusOne() {
        return scoreDifference() == -1;
    }

    private boolean isDifferenceEqualsOne() {
        return scoreDifference() == 1;
    }

    private boolean isDifferenceGreaterOrEqualsTwo(int scoreDifference) {
        return scoreDifference >= 2;
    }

    private int scoreDifference(){
        return scorePlayer1 - scorePlayer2;
    }

    private void getScoreDescription(StringBuilder score, int tempScore) {
        switch(tempScore) {
            case 0 -> score.append("Love");
            case 1 -> score.append("Fifteen");
            case 2 -> score.append("Thirty");
            case 3 -> score.append("Forty");
            default -> getScore();
        }
    }

    private boolean isUpTo4Points() {
        return scorePlayer1 >= 4 || scorePlayer2 >= 4;
    }

    private boolean isBetween1And3Points() {
        return playerScoreIsBetween1And3Points(scorePlayer1) || playerScoreIsBetween1And3Points(scorePlayer2);
    }

    private boolean playerScoreIsBetween1And3Points(int scorePlayer) {
        return scorePlayer >= 1 || scorePlayer < 3;
    }

    private boolean isEqualScore(int scorePlayer1, int scorePlayer2) {
        return scorePlayer1 == scorePlayer2;
    }

    private void getScorePlayer(StringBuilder score) {
        switch (scorePlayer1) {
            case 0 -> score.append("Love-All");
            case 1 -> score.append("Fifteen-All");
            case 2 -> score.append("Thirty-All");
            default -> score.append("Deuce");
        }
    }
}
