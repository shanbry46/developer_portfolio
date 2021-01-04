package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import edu.gatech.seclass.words6300.models.statistics.CompletedGame;
import edu.gatech.seclass.words6300.model.games.Settings;
import edu.gatech.seclass.words6300.model.games.ActiveGameState;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Statistics objStatistics = new Statistics();
        CompletedGame completedGame = new CompletedGame();
        Settings gameSettings = new Settings();
        DataService ds = new DataService();
        LetterPool letterPool = ds.getLetterPool();
        final Button playGame = findViewById(R.id.playGame);
        final Button statistics = findViewById(R.id.statistics);
        final Button settings = findViewById(R.id.settings);
        //Initialize the Game Settings
        //Data Service Call to Check Active Game, if not empty then instantiate new game settings
        if(letterPool == null) {
            LetterPool pool = new LetterPool();
            pool.defaultPool();
            ds.setLetterPool(pool);
            gameSettings.setMaxTurns(15);
            ds.setGameSettings(gameSettings);
        }
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderGame();
            }
        });
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderStatistics();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderSettings();
            }
        });
    }
    public void renderGame() {
        Intent intent = new Intent(this, ActiveGame.class);
        startActivity(intent);
    }
    public void renderStatistics() {
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }
    public void renderSettings() {
        Intent intent = new Intent(this, GameSettings.class);
        startActivity(intent);
    }
}
