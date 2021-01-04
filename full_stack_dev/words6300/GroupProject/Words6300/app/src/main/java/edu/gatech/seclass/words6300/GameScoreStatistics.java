package edu.gatech.seclass.words6300;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import android.content.Intent;

import edu.gatech.seclass.words6300.model.utils.GameScoreStatisticsComparator;
import edu.gatech.seclass.words6300.models.statistics.CompletedGame;

public class GameScoreStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_statistics);
        DataService ds = new DataService();
        LinkedHashMap<Date, CompletedGame> games = ds.getCompletedGames();
        TableLayout ll = findViewById(R.id.display_table);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow row;
        TextView txtView;
        TextView txtView1;
        TextView txtView2;
        TextView txtView3;
        float avgScorePerTurns;
        int numTurns;
        int score;
        boolean grey = false;
        int i = 0;

        final ArrayList<CompletedGame> sortedGames = new ArrayList<>(games.values());
        Collections.sort(sortedGames, new GameScoreStatisticsComparator());

        for (final CompletedGame game : sortedGames) {
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
            txtView.setText("Game"+i);
            txtView.setPadding(40, 0, 130, 0);
            txtView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent intent = new Intent(GameScoreStatistics.this, GameDetails.class);
                    String timeCompleted = game.getTimeCompleted();
                    intent.putExtra("gameKey", timeCompleted);
                    startActivity(intent);
                }
            });
            row.addView(txtView);
            score = game.getFinalScore();
            txtView1 = new TextView(this);
            txtView1.setText(Integer.toString(score));
            txtView1.setPadding(40, 0, 130, 0);
            row.addView(txtView1);
            numTurns = game.getGameTurns();
            txtView2 = new TextView(this);
            txtView2.setText(Integer.toString(numTurns));
            txtView2.setPadding(40, 0, 130, 0);
            row.addView(txtView2);
            avgScorePerTurns = game.getAvgScore();
            txtView3 = new TextView(this);
            txtView3.setText(Float.toString(avgScorePerTurns));
            txtView3.setPadding(40, 0, 130, 0);
            row.addView(txtView3);
            ll.addView(row, i);
            i++;
        }

        for (Map.Entry<Date, CompletedGame> game : games.entrySet()) {

        }
    }
}
