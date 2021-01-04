package edu.gatech.seclass.words6300.models.statistics;

import java.util.Date;

public class Word {
    String word;
    int timesUsed = 0;
    Date date = new Date();

    public Word (String w) {setWord(w); }
    public void setWord(String w) {word = w;}
    public void setTimesUsed(int used) {timesUsed = used;}
    public void setLastModifiedDate(Date d) {date = d;}
    //Getters
    public int getTimesUsed() {return timesUsed;}
    public Date getlastModifiedDate() {return date;}
    //Incrementers
    public void incrementNumTimesUsed() {timesUsed++;}
    public String getWord(){ return word;}
}
