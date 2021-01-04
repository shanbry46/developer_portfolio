package edu.gatech.seclass.words6300;

public class Letter {

    public Letter() {}

    char letter;
    int value;
    int numAvailable;
    int timesPlayed;
    int timesTraded;
    int timesDrawn; // needed this to calculate percent used
    double percentUsed;

    public Letter(char c){
      setLetter(c);
    }

    public void setLetter(char l){
        letter = l;
    }

    public void setValue (int val){
        value = val;
    }

    public void setNumAvailable (int avail){
        numAvailable = avail;
    }

    public void setTimesPlayed(int played) { timesPlayed = played; }

    public void setTimesTraded(int traded) { timesTraded = traded; }

    public void setTimesDrawn(int drawn) { timesDrawn = drawn; }

    public char getLetter(){
        return letter;
    }

    public int getValue(){
        return value;
    }

    public int getNumAvailable(){
        return numAvailable;
    }

    public int getTimesPlayed(){
        return timesPlayed;
    }

    public int getTimesTraded(){
        return timesTraded;
    }

    public int getTimesDrawn(){
        return timesDrawn;
    }

    public double getPercentUsed(){
        percentUsed = 0;
        if(timesDrawn != 0){
            percentUsed = (timesPlayed/timesDrawn) * 100;
        }
        return percentUsed;
    }

    public void incrementNumAvailable(){
        numAvailable++;
    }

    public void incrementTimesPlayed(){
        timesPlayed++;
    }

    public void incrementTimesTraded(){
        timesTraded++;
    }

    public void incrementTimesDrawn() {
        timesDrawn++;
    }

    public void decrementNumAvailable(){
        numAvailable--;
    }

    public void decrementTimesPlayed(){
        timesPlayed--;
    }

    public void decrementTimesTraded(){
        timesTraded--;
    }

    public void decrementTimesDrawn() {
        timesDrawn--;
    }
}
