package edu.gatech.seclass.words6300;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.gatech.seclass.words6300.model.games.Settings;

public class GameSettings extends AppCompatActivity{
    TextView updateMaxTurns;
    int maxTurns = 15; // -1 for no max
    DataService dataService = new DataService();
    final Settings gameSettings = dataService.getGameSettings();
    final LetterPool letterPool = dataService.getLetterPool();
    char letterChar;
    Integer numAvailable;
    Integer value;
    final String[] alphabet = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        //final Button submitMaxTurns = findViewById(R.id.submitMaxTurns);
        //final EditText letter = findViewById(R.id.letter);
        final Button submitLetterUpdates = findViewById(R.id.updateLetterSettings);
        updateMaxTurns = findViewById(R.id.maxTurnsSet);
        NumberPicker maxTurnsNP = findViewById(R.id.numberPicker);
        NumberPicker letterNP = findViewById(R.id.letterPicker);

        NumberPicker valueNP = findViewById(R.id.valuePicker);
        NumberPicker numAvailNP = findViewById(R.id.numAvailPicker);

        maxTurnsNP.setMinValue(2);
        maxTurnsNP.setMaxValue(99);
        maxTurnsNP.setValue(dataService.getGameSettings().getMaxTurns());
        updateMaxTurns.setText(dataService.getGameSettings().getMaxTurns()+"");
        maxTurnsNP.setOnValueChangedListener(onMaxTurnsChangeListener);

        letterNP.setMinValue(0);
        letterNP.setMaxValue(alphabet.length -1);
        letterNP.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return alphabet[value];
            }
        });
        letterNP.setOnValueChangedListener(onLetterChangeListener);

        valueNP.setMinValue(1);
        valueNP.setMaxValue(10);
        valueNP.setOnValueChangedListener(onValueChangeListener);

        numAvailNP.setMinValue(1);
        numAvailNP.setMaxValue(20);
        numAvailNP.setOnValueChangedListener(onNumAvailChangeListener);

//        submitMaxTurns.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText editMaxTurns = findViewById(R.id.maxTurns);
//                Integer maxTurnsValue = Integer.parseInt(String.valueOf(editMaxTurns.getText()));
//                updateMaxTurns(maxTurnsValue, gameSettings);
//            }
//        });
//        letter.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.length() >= 1) {
//                    //char letterChar = String.valueOf(letter.getText()).toLowerCase().charAt(0);
//                     // int numAvail = getNumAvailable(letterChar);
//                      //int value = getLetterValue(letterChar);
//                      //EditText numAvailable = findViewById(R.id.numAvail);
//                      //EditText letterValue = findViewById(R.id.letterValue);
//                      //numAvailable.setText(Integer.toString(numAvail));
//                      //letterValue.setText(Integer.toString(value));
//                  }
//            }
//        });
        submitLetterUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLetterValues(letterChar, numAvailable, value);
            }
        });
    }

    NumberPicker.OnValueChangeListener onMaxTurnsChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    updateMaxTurns(numberPicker.getValue(), gameSettings);

                }
            };

    NumberPicker.OnValueChangeListener onLetterChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    String character = alphabet[numberPicker.getValue()];
                    letterChar = character.charAt(0);

                }
            };

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    value = numberPicker.getValue();

                }
            };

    NumberPicker.OnValueChangeListener onNumAvailChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    numAvailable = numberPicker.getValue();

                }
            };

    public void updateMaxTurns(int maxTurnsValue, Settings gameSettings){
        maxTurns = maxTurnsValue;
        updateMaxTurns.setText(Integer.toString(maxTurnsValue));
        gameSettings.setMaxTurns(maxTurns);
        dataService.setGameSettings(gameSettings);
    }

    public void updateLetterValues(char letter, int numAvail, int letterVal) {
        Letter letter1 = letterPool.getLetter(letter);
        letter1.getLetter();
        letter1.setNumAvailable(numAvail);
        letter1.setValue(letterVal);
        letterPool.setLetter(letter, letter1);
        dataService.setLetterPool(letterPool);
    }

    public int getNumAvailable(char c) {
        Letter letter = letterPool.getLetter(c);
        int numAvail = letter.getNumAvailable();
        return numAvail;
    }

    public int getLetterValue(char c) {
        Letter letter = letterPool.getLetter(c);
        int value = letter.getValue();
        return value;
    }

    public int getMaxTurns(){
        return maxTurns;
    }

}
