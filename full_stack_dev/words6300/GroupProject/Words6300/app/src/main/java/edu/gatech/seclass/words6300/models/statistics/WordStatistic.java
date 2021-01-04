package edu.gatech.seclass.words6300.models.statistics;
import java.util.Date;

public class WordStatistic {
    String word;
    Integer timesUsed;
    Date lastDatePlayed;

    public Date getLastDatePlayed() {
        return lastDatePlayed;
    }

    public void setLastDatePlayed(Date lastDatePlayed) {
        this.lastDatePlayed = lastDatePlayed;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(Integer timesUsed) {
        this.timesUsed = timesUsed;
    }
}
