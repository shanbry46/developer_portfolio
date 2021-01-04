package edu.gatech.seclass.words6300;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.gatech.seclass.words6300.model.utils.LetterStaticsComparator;
import edu.gatech.seclass.words6300.models.statistics.LetterStatistic;

public class LetterStatistics extends Statistics {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataService dataService = new DataService();
        setContentView(R.layout.activity_letter_statistics);
        LetterPool read = dataService.getLetterPool();
        HashMap<Character, Letter> letterPool = read.getLetterPool();
        TableLayout ll = findViewById(R.id.display_table);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow row;
        TextView txtView;
        TextView txtView1;
        TextView txtView2;
        TextView txtView3;
        int numPlays;
        int numTrades;
        double pctUsage;
        int i = 0;
        String l;
        boolean grey = false;

        ArrayList<Letter> sortedLetters = new ArrayList<>(letterPool.values());
        Collections.sort(sortedLetters, new LetterStaticsComparator());

        for (Letter letter : sortedLetters) {
            String test;
            row = new TableRow(this);
            row.setLayoutParams(lp);
            if (!grey) {
                row.setBackgroundColor(Color.LTGRAY);
                grey = true;
            } else {
                row.setBackgroundColor(Color.WHITE);
                grey = false;
            }
            txtView = new TextView(this);
            l = Character.toString(letter.getLetter());
            txtView.setText(l);
            txtView.setPadding(40, 0, 130, 0);
            row.addView(txtView);
            numPlays = letter.getTimesPlayed();
            txtView1 = new TextView(this);
            txtView1.setText(Integer.toString(numPlays));
            txtView1.setPadding(40, 0, 130, 0);
            row.addView(txtView1);
            numTrades = letter.getTimesTraded();
            txtView2 = new TextView(this);
            txtView2.setText(Integer.toString(numTrades));
            txtView2.setPadding(40, 0, 130, 0);
            row.addView(txtView2);
            pctUsage = letter.getPercentUsed();
            txtView3 = new TextView(this);
            txtView3.setText(Double.toString(pctUsage));
            txtView3.setPadding(40, 0, 130, 0);
            row.addView(txtView3);
            ll.addView(row, i);
            i++;
        }


//            if (letterPool != null) {
//            for (Map.Entry<Character, Letter> letter : letterPool.entrySet()) {
//                row = new TableRow(this);
//                row.setLayoutParams(lp);
//                if (!grey){
//                    row.setBackgroundColor(Color.LTGRAY);
//                    grey = true;
//                }
//                else{
//                    row.setBackgroundColor(Color.WHITE);
//                    grey = false;
//                }
//                txtView = new TextView(this);
//                l = Character.toString(letter.getValue().getLetter());
//                txtView.setText(l);
//                txtView.setPadding(40, 0, 130, 0);
//                row.addView(txtView);
//                numPlays = letter.getValue().getTimesPlayed();
//                txtView1 = new TextView(this);
//                txtView1.setText(Integer.toString(numPlays));
//                txtView1.setPadding(40, 0, 130, 0);
//                row.addView(txtView1);
//                numTrades = letter.getValue().getTimesTraded();
//                txtView2 = new TextView(this);
//                txtView2.setText(Integer.toString(numTrades));
//                txtView2.setPadding(40, 0, 130, 0);
//                row.addView(txtView2);
//                pctUsage = letter.getValue().getPercentUsed();
//                txtView3 = new TextView(this);
//                txtView3.setText(Double.toString(pctUsage));
//                txtView3.setPadding(40, 0, 130, 0);
//                row.addView(txtView3);
//                ll.addView(row, i);
//                i++;
//            }

    }
}
