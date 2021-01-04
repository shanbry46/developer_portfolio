package edu.gatech.seclass.words6300;
import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Date;
import edu.gatech.seclass.words6300.model.games.ActiveGameState;
import edu.gatech.seclass.words6300.model.games.Settings;
import edu.gatech.seclass.words6300.models.statistics.CompletedGame;
import edu.gatech.seclass.words6300.models.statistics.WordStatistic;

public class DataService {
    private ObjectMapper objectMapper;
    Context context;

    //Constructor
    public DataService() {
        context = MyApplication.getAppContext();
        objectMapper = new ObjectMapper();
    }

    //Write and Read Methods
    public void writeFile(String fileName, ByteArrayOutputStream outputStream) {
        try {
            OutputStream output = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            output.write(outputStream.toByteArray());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not WRITE file: " + fileName);
        }
    }

    public InputStream readFile(String fileName) throws IOException {
        System.out.println("Existing Files: " + Arrays.toString(context.fileList()));
        try {
            return context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Could not find file: " + fileName);
        }
    }

    //Game Settings
    public void setGameSettings(Settings gameSettings) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(output, gameSettings);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        writeFile(
                "gameSettings.json",
                output
        );
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Settings getGameSettings() {
        Settings settings;
        try {
            settings = objectMapper.readValue(readFile("gameSettings.json"),
                    new TypeReference<Settings>() {
                    }
            );
            System.out.println("Read file");
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return new Settings();
        }
        return settings;
    }

    //Active Game Information
    public void setActiveGameState(ActiveGameState activeGameState) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(output, activeGameState);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        writeFile(
                "activeGame.json",
                output
        );
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ActiveGameState getActiveGames() {
        ActiveGameState activeGame;
        try {
            activeGame = objectMapper.readValue(readFile("activeGame.json"), ActiveGameState.class);
            System.out.println("Read file");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return activeGame;
    }

    //Word Statistics
    public void setWordBank(ArrayList<String> gameWordsUsed) {
        updateWordBank(gameWordsUsed);
    }

    public void updateWordBank(ArrayList<String> updateValues) {
        LinkedHashMap<String, WordStatistic> wordBankUpdates = getWordStatistics();
        int i;
        for(i = 0; i<updateValues.size(); i++) {
            String key = updateValues.get(i);
            boolean hasValue = wordBankUpdates.containsKey(key);
            if(hasValue == true) {
                wordBankUpdates = getWordStatistics();
                WordStatistic values =  wordBankUpdates.get(key);
                Integer timesUsed = values.getTimesUsed();
                values.setTimesUsed(timesUsed + 1);
                values.setLastDatePlayed(new Date());
                wordBankUpdates.put(key, values);
                setWordStatistics(wordBankUpdates);
            }
            else {
                wordBankUpdates = getWordStatistics();
                WordStatistic newWord = new WordStatistic();
                newWord.setLastDatePlayed(new Date());
                newWord.setTimesUsed(1);
                newWord.setWord(key);
                wordBankUpdates.put(key, newWord);
                setWordStatistics(wordBankUpdates);
            }
        }
    }

    public void setWordStatistics(LinkedHashMap<String, WordStatistic> wordStats) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(output, wordStats);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        writeFile(
                "wordStatistics.json",
                output
        );
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LinkedHashMap<String, WordStatistic> getWordStatistics() {
        LinkedHashMap<String, WordStatistic> wordBank = null;
        try {
            wordBank = objectMapper.readValue(readFile("wordStatistics.json"),
                    new TypeReference<LinkedHashMap<String, WordStatistic>>() {
                    }
            );
            System.out.println("Read file");
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedHashMap<String, WordStatistic>();
        }
        return wordBank;
    }

    //Letter Pool Info
    public void setLetterPool(LetterPool letterPool) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(output, letterPool);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        writeFile(
                "letterPool.json",
                output
        );
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LetterPool getLetterPool() {
        LetterPool letterPool;
        try {
            letterPool = objectMapper.readValue(readFile("letterPool.json"), LetterPool.class);
            System.out.println("Read file");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return letterPool;
    }

    //Set Completed Game Info
    public void setCompletedGame(LinkedHashMap<Date, CompletedGame> completedGame) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(output, completedGame);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        writeFile(
                "completedGames.json",
                output
        );
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap<Date, CompletedGame> getCompletedGames() {
        LinkedHashMap<Date, CompletedGame> completedGame = null;
        try {
            completedGame = objectMapper.readValue(readFile("completedGames.json"),
                    new TypeReference<LinkedHashMap<Date, CompletedGame>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedHashMap<Date, CompletedGame>();
        }
        return completedGame;
    }

}
