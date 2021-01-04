package edu.gatech.seclass.words6300.model.utils;

import java.util.Comparator;

import edu.gatech.seclass.words6300.Letter;
import edu.gatech.seclass.words6300.models.statistics.LetterStatistic;

public class LetterStaticsComparator implements Comparator<Letter> {
    @Override
    public int compare(Letter o1, Letter o2) {
        return o1.getTimesPlayed() - o2.getTimesPlayed();
    }
}
