package edu.gatech.seclass.words6300.model.utils;

import java.util.Comparator;

import edu.gatech.seclass.words6300.models.statistics.CompletedGame;

public class GameScoreStatisticsComparator implements Comparator<CompletedGame> {
    @Override
    public int compare(CompletedGame o1, CompletedGame o2) {
        return o2.getFinalScore() - o1.getFinalScore();
    }

    @Override
    public Comparator<CompletedGame> reversed() {
        return null;
    }
}
