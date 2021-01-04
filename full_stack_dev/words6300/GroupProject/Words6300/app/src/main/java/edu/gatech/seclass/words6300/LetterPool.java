package edu.gatech.seclass.words6300;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.gatech.seclass.words6300.models.statistics.CompletedGame;

public class LetterPool {

    @JsonIgnore
    DataService dataService = new DataService();

    public LetterPool() {

    }

    HashMap<Character, Letter> letterPool = new HashMap<Character, Letter>();

    public void defaultPool() {
        Letter a = new Letter('a');
        Letter b = new Letter('b');
        Letter c = new Letter('c');
        Letter d = new Letter('d');
        Letter e = new Letter('e');
        Letter f = new Letter('f');
        Letter g = new Letter('g');
        Letter h = new Letter('h');
        Letter i = new Letter('i');
        Letter j = new Letter('j');
        Letter k = new Letter('k');
        Letter l = new Letter('l');
        Letter m = new Letter('m');
        Letter n = new Letter('n');
        Letter o = new Letter('o');
        Letter p = new Letter('p');
        Letter q = new Letter('q');
        Letter r = new Letter('r');
        Letter s = new Letter('s');
        Letter t = new Letter('t');
        Letter u = new Letter('u');
        Letter v = new Letter('v');
        Letter w = new Letter('w');
        Letter x = new Letter('x');
        Letter y = new Letter('y');
        Letter z = new Letter('z');
        a.setValue(1);
        a.setNumAvailable(9);
        b.setValue(3);
        b.setNumAvailable(2);
        c.setValue(3);
        c.setNumAvailable(2);
        d.setValue(2);
        d.setNumAvailable(4);
        e.setValue(1);
        e.setNumAvailable(12);
        f.setValue(4);
        f.setNumAvailable(2);
        g.setValue(2);
        g.setNumAvailable(3);
        h.setValue(4);
        h.setNumAvailable(2);
        i.setValue(1);
        i.setNumAvailable(9);
        j.setValue(8);
        j.setNumAvailable(1);
        k.setValue(5);
        k.setNumAvailable(1);
        l.setValue(1);
        l.setNumAvailable(4);
        m.setValue(3);
        m.setNumAvailable(2);
        m.setValue(1);
        n.setNumAvailable(6);
        o.setValue(1);
        o.setNumAvailable(8);
        p.setValue(3);
        p.setNumAvailable(2);
        q.setValue(10);
        q.setNumAvailable(1);
        r.setValue(1);
        r.setNumAvailable(6);
        s.setValue(1);
        s.setNumAvailable(4);
        t.setValue(1);
        t.setNumAvailable(6);
        u.setValue(1);
        u.setNumAvailable(4);
        v.setValue(4);
        v.setNumAvailable(2);
        w.setValue(4);
        w.setNumAvailable(2);
        x.setValue(8);
        x.setNumAvailable(1);
        y.setValue(4);
        y.setNumAvailable(2);
        z.setValue(10);
        z.setNumAvailable(1);

        letterPool.put('a', a);
        letterPool.put('b', b);
        letterPool.put('c', c);
        letterPool.put('d', d);
        letterPool.put('e', e);
        letterPool.put('f', f);
        letterPool.put('g', g);
        letterPool.put('h', h);
        letterPool.put('i', i);
        letterPool.put('j', j);
        letterPool.put('k', k);
        letterPool.put('l', l);
        letterPool.put('m', m);
        letterPool.put('n', n);
        letterPool.put('o', o);
        letterPool.put('p', p);
        letterPool.put('q', q);
        letterPool.put('r', r);
        letterPool.put('s', s);
        letterPool.put('t', t);
        letterPool.put('u', u);
        letterPool.put('v', v);
        letterPool.put('w', w);
        letterPool.put('x', x);
        letterPool.put('y', y);
        letterPool.put('z', z);
    }

//    public void initializePool(){
//        LetterPool letters;
//        letters = dataService.getLetterPool();
//        for (LetterPool letter : letters){
//            setLetter(letter.getLetter(), letter);
//        }
//    }

    public void setLetterPool(HashMap<Character, Letter> map) {
        letterPool = map;
    }

    public Letter getLetter(char c) {
        LetterPool letterPool = dataService.getLetterPool();
        return (Letter) letterPool.letterPool.get(c);
    }

    public void setLetter(char c, Letter l) {
        letterPool.put(c, l);
    }

    public HashMap<Character, Letter> getLetterPool() {
        return letterPool;
    }
}