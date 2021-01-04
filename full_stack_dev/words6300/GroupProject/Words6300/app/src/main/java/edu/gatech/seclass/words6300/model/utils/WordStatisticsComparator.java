package edu.gatech.seclass.words6300.model.utils;

import java.util.Comparator;

import edu.gatech.seclass.words6300.models.statistics.WordStatistic;;

public class WordStatisticsComparator implements Comparator<WordStatistic> {
    @Override
    public int compare(WordStatistic o1, WordStatistic o2) {
        return (int) (o2.getLastDatePlayed().getTime() - o1.getLastDatePlayed().getTime());
    }
}
