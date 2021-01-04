package edu.gatech.seclass.words6300.model.games;
import edu.gatech.seclass.words6300.Letter;
import edu.gatech.seclass.words6300.LetterPool;

public class Settings {
    public Settings() {

    }
    Integer maxTurns;
    LetterPool letterPool;

    public Integer getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(Integer maxTurns) {
        this.maxTurns = maxTurns;
    }

    public LetterPool getLetterPool() {
        return letterPool;
    }

    public void setLetterPool(LetterPool letterPool) {
        this.letterPool = letterPool;
    }
}
