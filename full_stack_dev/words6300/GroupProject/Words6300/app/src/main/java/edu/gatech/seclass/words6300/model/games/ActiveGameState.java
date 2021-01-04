package edu.gatech.seclass.words6300.model.games;

import java.util.ArrayList;

import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.LetterPool;

public class ActiveGameState {
    ArrayList<Character> remainingLetters;
    int turnsPlayed;
    Settings currentSettings;
    int maxTurns;
    ArrayList<Integer> scores;
    LetterPool letterPool;
    ArrayList<String> previousWords;
    char[] board;
    char[] rack;
    boolean gameOver;

    public ArrayList<Character> getRemainingLetters() {
        return remainingLetters;
    }

    public void setRemainingLetters(ArrayList<Character> remainingLetters) {
        this.remainingLetters = remainingLetters;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public void setTurnsPlayed(int turnsPlayed) {
        this.turnsPlayed = turnsPlayed;
    }

    public Settings getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(Settings currentSettings) {
        this.currentSettings = currentSettings;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public LetterPool getLetterPool() {
        return letterPool;
    }

    public void setLetterPool(LetterPool letterPool) {
        this.letterPool = letterPool;
    }

    public ArrayList<String> getPreviousWords() {
        return previousWords;
    }

    public void setPreviousWords(ArrayList<String> previousWords) {
        this.previousWords = previousWords;
    }

    public char[] getBoard() {
        return board;
    }

    public void setBoard(char[] board) {
        this.board = board;
    }

    public char[] getRack() {
        return rack;
    }

    public void setRack(char[] rack) {
        this.rack = rack;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
