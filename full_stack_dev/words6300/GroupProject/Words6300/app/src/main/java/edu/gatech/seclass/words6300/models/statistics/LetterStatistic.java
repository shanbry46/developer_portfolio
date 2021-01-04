package edu.gatech.seclass.words6300.models.statistics;

public class LetterStatistic {
    private Integer numAVailable;
    private Integer timesPlayed;
    private Integer percentUsed;
    private Integer timesDrawn;
    private Integer value;
    private Integer timesTraded;
    private String letter;

    public Integer getNumAVailable() {
        return numAVailable;
    }
    public void setNumAVailable(Integer numAVailable) {
        this.numAVailable = numAVailable;
    }
    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Integer timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public Integer getTimesTraded() {
        return timesTraded;
    }

    public void setTimesTraded(Integer timesTraded) {
        this.timesTraded = timesTraded;
    }

    public Integer getPercentUsed() {
        return percentUsed;
    }

    public void setPercentUsed(Integer percentUsed) {
        this.percentUsed = percentUsed;
    }

    public Integer getTimesDrawn() {
        return timesDrawn;
    }

    public void setTimesDrawn(Integer timesDrawn) {
        this.timesDrawn = timesDrawn;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setLetter(String letter) {this.letter = letter;}

    public String getLetter() { return letter;}

}
