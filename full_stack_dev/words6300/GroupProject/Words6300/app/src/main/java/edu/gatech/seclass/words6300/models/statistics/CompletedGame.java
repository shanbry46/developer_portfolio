package edu.gatech.seclass.words6300.models.statistics;
import java.util.ArrayList;
import java.util.Date;
import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.model.games.Settings;

public class CompletedGame {
    public CompletedGame() {

    }
    //Objects + Vars
    Settings gameSettings;
    int score;
    String timeCompleted;
    float avgScore;
    int turns;
    //Methods
    public void setSettings(Settings settings) {
        gameSettings = settings;
    }
    public Settings getGameSettings() {
        return gameSettings;
    }
    public String getTimeCompleted() {
        return timeCompleted;
    }
    public void setTimeCompleted(String timeCompleted) {
        this.timeCompleted = timeCompleted;
    }
    public void setFinalScore(int finalScore) {
        score = finalScore;
    }
    public void setAvgScore(float average) {
        avgScore = average;
    }
    public void setGameTurns(int gameTurns) {
        turns = gameTurns;
    }
    public int getFinalScore() {
        return score;
    }
    public int getGameTurns() {
        return turns;
    }
    public float getAvgScore() {
            return avgScore;
    }
}
