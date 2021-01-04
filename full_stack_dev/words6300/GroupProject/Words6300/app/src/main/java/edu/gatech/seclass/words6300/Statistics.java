package edu.gatech.seclass.words6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Statistics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        final Button wordStatistics = findViewById(R.id.wordStatistics);
        final Button letterStatistics = findViewById(R.id.letterStatistics);
        final Button gameStatistics = findViewById(R.id.gameStatistics);
        wordStatistics.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            renderWordStatistics();
        }
    });
        letterStatistics.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            renderLetterStatistics();
        }
    });
        gameStatistics.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            renderGameScoreStatistics();
        }
    });
}
    public void renderWordStatistics() {
        Intent intent = new Intent(this, WordStatistics.class);
        startActivity(intent);
    }
    public void renderLetterStatistics() {
        Intent intent = new Intent(this, LetterStatistics.class);
        startActivity(intent);
    }
    public void renderGameScoreStatistics() {
        Intent intent = new Intent(this, GameScoreStatistics.class);
        startActivity(intent);
    }
}
