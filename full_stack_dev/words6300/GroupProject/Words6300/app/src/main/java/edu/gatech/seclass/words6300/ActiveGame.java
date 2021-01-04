package edu.gatech.seclass.words6300;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Date;

import edu.gatech.seclass.words6300.model.games.ActiveGameState;
import edu.gatech.seclass.words6300.models.statistics.CompletedGame;
import edu.gatech.seclass.words6300.model.games.Settings;

public class ActiveGame extends AppCompatActivity {
    private TextView board1;
    private TextView board2;
    private TextView board3;
    private TextView board4;
    private TextView rack1;
    private TextView rack2;
    private TextView rack3;
    private TextView rack4;
    private TextView rack5;
    private TextView rack6;
    private TextView rack7;
    private Button playWordButton;
    private Button swapLettersButton;
    private Button resetButton;
    private Button exitGameButton;
    private TextView messageBanner;
    ArrayList<Character> remainingLetters = new ArrayList<Character>();
    int turnsPlayed = 0;
    DataService ds = new DataService();
    Settings currentSettings = ds.getGameSettings();
    int maxTurns = currentSettings.getMaxTurns();
    ArrayList<Integer> scores = new ArrayList<Integer>();
    LetterPool letterPool = ds.getLetterPool();
    int totalScore = 0;
    ArrayList<String> previousWords = new ArrayList<String>();
    String currentWord;
    ArrayList<String> word = new ArrayList<>();
    char selectedLetter;
    int letterLocation;
    ArrayList<Integer> rackLocations = new ArrayList<>();

    char[] board = new char[4];
    char[] rack = new char[7];
    private static final int bonusPoints = 10;
    boolean gameOver = false;
    boolean activeGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);
        board1 = findViewById(R.id.board1);
        board2 = findViewById(R.id.board2);
        board3 = findViewById(R.id.board3);
        board4 = findViewById(R.id.board4);
        rack1 = findViewById(R.id.selection1);
        rack2 = findViewById(R.id.selection2);
        rack3 = findViewById(R.id.selection3);
        rack4 = findViewById(R.id.selection4);
        rack5 = findViewById(R.id.selection5);
        rack6 = findViewById(R.id.selection6);
        rack7 = findViewById(R.id.selection7);
        messageBanner = findViewById(R.id.messageBanner);
        playWordButton = findViewById(R.id.playWord);
        playWordButton.setEnabled(false);
        swapLettersButton = findViewById(R.id.swapLetters);
        resetButton = findViewById(R.id.reset);
        exitGameButton = findViewById(R.id.exit);
        ActiveGameState activeGameState = ds.getActiveGames();
        if(activeGameState != null && activeGameState.isGameOver() == false) {
            reinitializeActiveGame(activeGameState);
        } else {
            initializeRemainingLetters();
            initializeBoard();
            initializeRack();
        }
    }

    public void reinitializeActiveGame(ActiveGameState activeGameState) {
        turnsPlayed = activeGameState.getTurnsPlayed();
        scores = activeGameState.getScores();
        remainingLetters = activeGameState.getRemainingLetters();
        rack = activeGameState.getRack();
        previousWords = activeGameState.getPreviousWords();
        maxTurns = activeGameState.getMaxTurns();
        letterPool = activeGameState.getLetterPool();
        currentSettings = activeGameState.getCurrentSettings();
        board = activeGameState.getBoard();
        updateBoardOnScreen();
        updateRackOnScreen();
    }

    public void resetClick(View view){
        resetScreen();
    }

    public void resetScreen(){
        messageBanner.setText("");
        board1.setEnabled(true);
        board1.setTextColor(Color.BLACK);
        board2.setEnabled(true);
        board2.setTextColor(Color.BLACK);
        board3.setEnabled(true);
        board3.setTextColor(Color.BLACK);
        board4.setEnabled(true);
        board4.setTextColor(Color.BLACK);
        rack1.setEnabled(true);
        rack1.setTextColor(Color.BLACK);
        rack2.setEnabled(true);
        rack2.setTextColor(Color.BLACK);
        rack3.setEnabled(true);
        rack3.setTextColor(Color.BLACK);
        rack4.setEnabled(true);
        rack4.setTextColor(Color.BLACK);
        rack5.setEnabled(true);
        rack5.setTextColor(Color.BLACK);
        rack6.setEnabled(true);
        rack6.setTextColor(Color.BLACK);
        rack7.setEnabled(true);
        rack7.setTextColor(Color.BLACK);
        word.clear();
        currentWord = "";
        rackLocations.clear();
        playWordButton.setEnabled(false);
    }

    public void exitButtonClick(View view){
        if(gameOver) {
            LinkedHashMap<Date, CompletedGame> games = ds.getCompletedGames();
            games = createCompletedGameRecord(games);
            ActiveGameState activeGameState = ds.getActiveGames();
            if(activeGameState != null) {
                activeGameState.setGameOver(true);
                ds.setActiveGameState(activeGameState);
            }
            ds.setCompletedGame(games);
            ds.setWordBank(previousWords);
        } else {
            ActiveGameState activeGame = createActiveGameRecord();
            DataService ds = new DataService();
            ds.setActiveGameState(activeGame);
        }
        Context context = view.getContext();
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    private ActiveGameState createActiveGameRecord() {
        letterPool = ds.getLetterPool();
        ActiveGameState game = new ActiveGameState();
        game.setBoard(board);
        game.setCurrentSettings(currentSettings);
        game.setGameOver(gameOver);
        game.setLetterPool(letterPool);
        game.setMaxTurns(maxTurns);
        game.setPreviousWords(previousWords);
        game.setRack(rack);
        game.setRemainingLetters(remainingLetters);
        game.setScores(scores);
        game.setTurnsPlayed(turnsPlayed);

        return game;
    }


    public void letterClick(View view){

        switch (view.getId()){
            case R.id.board1:
                selectedLetter = board[0];
                letterLocation = 0;
                word.add(board1.getText().toString());
                board1.setTextColor(Color.RED);
                board1.setEnabled(false);
                board2.setEnabled(false);
                board3.setEnabled(false);
                board4.setEnabled(false);
                rackLocations.add(-1);
                messageBanner.setText(wordAsString());
                break;
            case R.id.board2:
                selectedLetter = board[1];
                letterLocation = 1;
                word.add(board2.getText().toString());
                board2.setTextColor(Color.RED);
                board1.setEnabled(false);
                board2.setEnabled(false);
                board3.setEnabled(false);
                board4.setEnabled(false);
                rackLocations.add(-1);
                messageBanner.setText(wordAsString());
                break;
            case R.id.board3:
                selectedLetter = board[2];
                letterLocation = 2;
                word.add(board3.getText().toString());
                board3.setTextColor(Color.RED);
                board1.setEnabled(false);
                board2.setEnabled(false);
                board3.setEnabled(false);
                board4.setEnabled(false);
                rackLocations.add(-1);
                messageBanner.setText(wordAsString());
                break;
            case R.id.board4:
                selectedLetter = board[3];
                letterLocation = 3;
                word.add(board4.getText().toString());
                board4.setTextColor(Color.RED);
                board1.setEnabled(false);
                board2.setEnabled(false);
                board3.setEnabled(false);
                board4.setEnabled(false);
                rackLocations.add(-1);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection1:
                word.add(rack1.getText().toString());
                rack1.setTextColor(Color.RED);
                rack1.setEnabled(false);
                rackLocations.add(0);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection2:
                word.add(rack2.getText().toString());
                rack2.setTextColor(Color.RED);
                rack2.setEnabled(false);
                rackLocations.add(1);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection3:
                word.add(rack3.getText().toString());
                rack3.setTextColor(Color.RED);
                rack3.setEnabled(false);
                rackLocations.add(2);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection4:
                word.add(rack4.getText().toString());
                rack4.setTextColor(Color.RED);
                rack4.setEnabled(false);
                rackLocations.add(3);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection5:
                word.add(rack5.getText().toString());
                rack5.setTextColor(Color.RED);
                rack5.setEnabled(false);
                rackLocations.add(4);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection6:
                word.add(rack6.getText().toString());
                rack6.setTextColor(Color.RED);
                rack6.setEnabled(false);
                rackLocations.add(5);
                messageBanner.setText(wordAsString());
                break;
            case R.id.selection7:
                word.add(rack7.getText().toString());
                rack7.setTextColor(Color.RED);
                rack7.setEnabled(false);
                rackLocations.add(6);
                messageBanner.setText(wordAsString());
                break;

        }
        if(lettersPlayed()){
            playWordButton.setEnabled(true);
        }
    }

    public String wordAsString(){
        String theWord;
        theWord = word.get(0);
        for(int i = 1; i < word.size(); i++){
            theWord = theWord + word.get(i);
        }

        return theWord;

    }

    public boolean lettersPlayed(){
        boolean lettersPlayed;
        if(board1.isEnabled()||board2.isEnabled()||board3.isEnabled()||board4.isEnabled()){
            lettersPlayed = false;
        } else if(rack1.isEnabled()&&rack1.isEnabled()&&rack2.isEnabled()&&rack3.isEnabled()&&
                rack5.isEnabled()&&rack6.isEnabled()&&rack7.isEnabled()) {
            lettersPlayed = false;
        } else {
            lettersPlayed = true;
        }
        return lettersPlayed;
    }

    public void playWordClick(View view){

        currentWord = word.get(0);
        for (int i = 1; i < word.size(); i++)
        {
           currentWord += word.get(i);
        }
        messageBanner.setText(currentWord);
        playWord();

    }

    public void swapLettersClick(View view){
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                handleSwapLetters(menuItem);
                return true;
            }
        });
        popup.show();


    }

    public void setActiveGame() {
        activeGame = true;
    }

    public void handleSwapLetters(MenuItem menuItem) {

        messageBanner.setText(menuItem.getTitle());
        if(menuItem.getItemId() == R.id.swap1){
            swapLetters(1);
        } else if (menuItem.getItemId() == R.id.swap2){
            swapLetters(2);
        } else if (menuItem.getItemId() == R.id.swap3){
            swapLetters(3);
        } else if (menuItem.getItemId() == R.id.swap4){
            swapLetters(4);
        } else if (menuItem.getItemId() == R.id.swap5){
            swapLetters(5);
        } else if (menuItem.getItemId() == R.id.swap6){
            swapLetters(6);
        } else {
            swapLetters(7);
        }
    }


    private void initializeRack() {
        LetterPool letterPool = ds.getLetterPool();
        Random random = new Random();
        int index;
        char letter;
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[0] = letter;
        Letter drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[1] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[2] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[3] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[4] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[5] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        rack[6] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        updateRackOnScreen();
        ds.setLetterPool(letterPool);
    }

    private void initializeBoard() {
        letterPool = ds.getLetterPool();
        Random random = new Random();
        int index;
        char letter;
        Letter drawnLetter;
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        board[0] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        board[1] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        board[2] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        index = random.nextInt(remainingLetters.size());
        letter = remainingLetters.get(index);
        board[3] = letter;
        drawnLetter = letterPool.getLetter(letter);
        drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
        letterPool.setLetter(letter, drawnLetter);
        remainingLetters.remove(index);
        updateBoardOnScreen();
        ds.setLetterPool(letterPool);
    }

    private void initializeRemainingLetters() {

        Iterator lpIterator = letterPool.getLetterPool().entrySet().iterator();

        while (lpIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)lpIterator.next();
            Letter letterObject = (Letter)mapElement.getValue();
            char letter = letterObject.getLetter();
            int numAvailable = letterObject.getNumAvailable();
            for(int i = 0; i < numAvailable; i++){
                remainingLetters.add(letter);
            }
        }
    }


    public void playWord() {
        turnsPlayed++;
        int score;
        if(verifyWord()){
            previousWords.add(currentWord);
            updateLetterStatsForWord();
            score = updateScore();
            if(turnsPlayed == maxTurns) {
                completeGame(false);
                return;
            }
            updateBoard();
            if(remainingLetters.size() > currentWord.length()-1){
                refillRack();
            } else {
                completeGame(true);
                return;
            }
            resetScreen();
            messageBanner.setText(score + " points scored!");

        } else {
            resetScreen();
            messageBanner.setText("Word Already Played");
        }


    }

    private void updateLetterStatsForWord() {
        LetterPool letterPool = ds.getLetterPool();
        char character;
        for(int i = 0; i < word.size(); i++) {
            character = word.get(i).charAt(0);
            Letter letter = letterPool.getLetter(character);
            letter.setTimesPlayed((letter.getTimesPlayed()) + 1);
            letterPool.setLetter(character, letter);
        }
        ds.setLetterPool(letterPool);
    }

    public void swapLetters(int numToSwap) {
        messageBanner.setText("");
        turnsPlayed++;
        letterPool = ds.getLetterPool();
        Random randomRackPosition = new Random();
        Random  randomLetterPoolPosition = new Random();
        int rackPosition;
        int letterPoolPosition;
        char tradedChar;
        char drawnChar;
        if(maxTurns == turnsPlayed) {
            completeGame(false);
            return;
        } else {
            if(remainingLetters.size() < numToSwap) {
                completeGame(true);
                return;
            }
            for (int i = 0; i < numToSwap; i++) {
                rackPosition = randomRackPosition.nextInt(rack.length);
                letterPoolPosition = randomLetterPoolPosition.nextInt(remainingLetters.size());
                tradedChar = rack[rackPosition];
                drawnChar = remainingLetters.get(letterPoolPosition);
                rack[rackPosition] = drawnChar;
                remainingLetters.remove(letterPoolPosition);
                remainingLetters.add(tradedChar);
                if(tradedChar == drawnChar) {
                    Letter letter = letterPool.getLetter(tradedChar);
                    letter.setTimesTraded(letter.getTimesTraded() + 2);
                    letterPool.setLetter(tradedChar, letter);
                }
                else {
                    Letter tradedLetter = letterPool.getLetter(tradedChar);
                    tradedLetter.setTimesTraded((tradedLetter.getTimesTraded()) + 1);
                    Letter drawnLetter = letterPool.getLetter(drawnChar);
                    drawnLetter.setTimesTraded((drawnLetter.getTimesTraded()) + 1);
                    letterPool.setLetter(drawnChar, drawnLetter);
                    letterPool.setLetter(tradedChar, tradedLetter);
                }
            }
            ds.setLetterPool(letterPool);
        }
        updateRackOnScreen();
        messageBanner.setText(numToSwap + " Letters Swapped");
    }

    public void updateRackOnScreen(){
        rack1.setText(rack[0]+"");
        rack2.setText(rack[1]+"");
        rack3.setText(rack[2]+"");
        rack4.setText(rack[3]+"");
        rack5.setText(rack[4]+"");
        rack6.setText(rack[5]+"");
        rack7.setText(rack[6]+"");
    }

    public void updateBoardOnScreen() {
        board1.setText(board[0]+"");
        board2.setText(board[1]+"");
        board3.setText(board[2]+"");
        board4.setText(board[3]+"");
    }

    public boolean verifyWord() {
        boolean wordValid;
        if(previousWords.contains(currentWord)) {
            wordValid = false;
        } else {
            wordValid = true;
        }
        return wordValid;
    }

    public int updateScore() {
        int roundScore = 0;
        Letter currentLetter;
        for (int i = 0; i < currentWord.length(); i++)
        {
            currentLetter = letterPool.getLetter(currentWord.charAt(i));
            roundScore += currentLetter.getValue();
        }
        scores.add(roundScore);
        return roundScore;

    }

    public void completeGame(boolean extraPoints) {
        if(scores.size() > 0) {
            for (int i = 0; i < scores.size(); i++) {
                totalScore += scores.get(i);
            }
        }
        if (extraPoints){
            totalScore += bonusPoints;
        }
        messageBanner.setText("Final Score: " + totalScore);
        disableScreen();
        gameOver = true;
    }

    public void disableScreen() {
        board1.setEnabled(false);
        board2.setEnabled(false);
        board3.setEnabled(false);
        board4.setEnabled(false);
        rack1.setEnabled(false);
        rack2.setEnabled(false);
        rack3.setEnabled(false);
        rack4.setEnabled(false);
        rack5.setEnabled(false);
        rack6.setEnabled(false);
        rack7.setEnabled(false);
        playWordButton.setEnabled(false);
        swapLettersButton.setEnabled(false);
        resetButton.setEnabled(false);

    }

    public LinkedHashMap<Date, CompletedGame> createCompletedGameRecord(LinkedHashMap<Date, CompletedGame> completedGames) {
        CompletedGame completedGame = new CompletedGame();
        Settings settings = new Settings();
        settings.setLetterPool(letterPool);
        settings.setMaxTurns(maxTurns);
        completedGame.setSettings(settings);
        completedGame.setFinalScore(totalScore);
        if(scores.size() > 0) {
            completedGame.setAvgScore(totalScore / (float) scores.size());
        } else {
            completedGame.setAvgScore(0);
        }
        Date date = new Date();
        String date1 = date.toString();
        completedGame.setGameTurns(turnsPlayed);
        completedGame.setTimeCompleted(date1);
        completedGames.put(new Date(), completedGame);
        return completedGames;
    }

    public void updateBoard() {
        Random random = new Random();
        currentWord.replaceFirst(String.valueOf(selectedLetter), "");
        messageBanner.setText(currentWord);
        board[letterLocation] = currentWord.charAt(random.nextInt(currentWord.length()));
        updateBoardOnScreen();
    }

    public void refillRack() {
        Random random = new Random();
        int randomInt;
        char letter;
        for(int i = 0; i < rackLocations.size(); i++) {
            if(rackLocations.get(i) != -1) {
                randomInt = random.nextInt(remainingLetters.size());
                letter = remainingLetters.get(randomInt);
                rack[rackLocations.get(i)] = letter;
                Letter drawnLetter = letterPool.getLetter(letter);
                drawnLetter.setTimesDrawn((drawnLetter.getTimesDrawn()) + 1);
                remainingLetters.remove(randomInt);

            }
        }
        updateRackOnScreen();
    }

}
