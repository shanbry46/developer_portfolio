package edu.gatech.seclass.words6300;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.words6300.model.games.Settings;
import edu.gatech.seclass.words6300.models.statistics.CompletedGame;

public class GameDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        TableLayout ll = findViewById(R.id.display_table);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        TableRow row;
        TextView maxTurns = findViewById(R.id.maxTurns);
        DataService ds = new DataService();
        Intent i = getIntent();
        String key = i.getExtras().getString("gameKey");
        HashMap<Date, CompletedGame> completedGames = ds.getCompletedGames();
        TextView txtView;
        TextView txtView1;
        TextView txtView2;
        Integer numAvailable;
        Integer letterValue;
        String l;
        boolean grey = false;
        int k;
        int j;
        int a = 0;
        Settings settings = null;

        for (HashMap.Entry<Date, CompletedGame> completedGameEntry : completedGames.entrySet()) {
            String timeCompleted = completedGameEntry.getValue().getTimeCompleted();
            if (key == null) {
                throw new AssertionError();
            }
            if (timeCompleted.contains(key)) {
                settings = completedGameEntry.getValue().getGameSettings();
                Integer maxTurnsVal = completedGameEntry.getValue().getGameSettings().getMaxTurns();
                maxTurns.setText("Max Turns: "+Integer.toString(maxTurnsVal));
            }
        }

        HashMap<Character, Letter> letterPool = settings.getLetterPool().letterPool;
        for (Map.Entry<Character, Letter> letter : letterPool.entrySet()) {
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
            l = Character.toString(letter.getValue().letter);
            txtView.setText((CharSequence) l);
            txtView.setPadding(60, 0, 180, 0);
            row.addView(txtView);
            numAvailable = letter.getValue().numAvailable;
            txtView1 = new TextView(this);
            txtView1.setText(Integer.toString(numAvailable));
            txtView1.setPadding(60, 0, 180, 0);
            row.addView(txtView1);
            letterValue = letter.getValue().value;
            txtView2 = new TextView(this);
            txtView2.setText(Integer.toString(letterValue));
            txtView2.setPadding(60, 0, 180, 0);
            row.addView(txtView2);
            ll.addView(row, a);
            a++;
        }
    }
}
