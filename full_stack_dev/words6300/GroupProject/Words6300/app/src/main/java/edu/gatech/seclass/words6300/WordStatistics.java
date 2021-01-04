package edu.gatech.seclass.words6300;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import edu.gatech.seclass.words6300.model.utils.LetterStaticsComparator;
import edu.gatech.seclass.words6300.model.utils.WordStatisticsComparator;
import edu.gatech.seclass.words6300.models.statistics.LetterStatistic;
import edu.gatech.seclass.words6300.models.statistics.Word;
import edu.gatech.seclass.words6300.models.statistics.WordStatistic;

public class WordStatistics extends AppCompatActivity {
    //Hash-map generation
    HashMap wordBank = new HashMap<String, Word>();
    //Android call-back
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_statistics); setContentView(R.layout.activity_word_statistics);
        DataService ds = new DataService();
        LinkedHashMap<String, WordStatistic> wordStats = ds.getWordStatistics();
        TableLayout ll = findViewById(R.id.display_table);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow row;
        TextView txtView;
        TextView txtView1;
        String word;
        int numPlays;
        int i = 0;
        boolean grey = false;

        if (wordStats != null) {
        ArrayList<WordStatistic> sortedWords = new ArrayList<>(wordStats.values());
        Collections.sort(sortedWords, new WordStatisticsComparator());

            for (WordStatistic wordStat : sortedWords) {
                row = new TableRow(this);
                row.setLayoutParams(lp);
                if (!grey){
                    row.setBackgroundColor(Color.LTGRAY);
                    grey = true;
                }
                else{
                    row.setBackgroundColor(Color.WHITE);
                    grey = false;
                }
                txtView = new TextView(this);
                word = wordStat.getWord();
                txtView.setText(word);
                txtView.setPadding(40, 0, 130, 0);
                row.addView(txtView);
                numPlays = wordStat.getTimesUsed();
                txtView1 = new TextView(this);
                txtView1.setText(Integer.toString(numPlays));
                txtView1.setPadding(40, 0, 130, 0);
                row.addView(txtView1);
                ll.addView(row, i);
                i++;
            }

        }
//        try {
//            JSONObject json = new JSONObject(wordStats);
//            JSONArray words = json.getJSONArray("words");
//            TableLayout ll = (TableLayout) findViewById(R.id.display_table);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//            TableRow row;
//            TextView txtView;
//            TextView txtView1;
//            int timesUsed;
//            String word;
//            for (int i = 0; i<words.length(); i++) {
//                row = new TableRow(this);
//                row.setLayoutParams(lp);
//                word = ((JSONObject) words.get(i)).getString("word");
//                txtView = new TextView(this);
//                txtView.setText(word);
//                txtView.setPadding(00, 0, 80, 0);
//                row.addView(txtView);
//                timesUsed = ((JSONObject) words.get(i)).getInt("timesUsed");
//                txtView1 = new TextView(this);
//                txtView1.setText(Integer.toString(timesUsed));
//                txtView1.setPadding(80, 0, 100, 0);
//                row.addView(txtView1);
//                ll.addView(row, i);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
    //Methods
    public void addWord(String s) {
        Word w = new Word(s);
        w.setWord(s);
        w.incrementNumTimesUsed();
        w.setLastModifiedDate(new Date());
        wordBank.put(s, w);
    }
    public Word getWord(String s) {
        return (Word) wordBank.get(s);
    }
    public HashMap<String, Word> getWordBank() {
        return wordBank;
    }
}
