package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing class created for use in Georgia Tech CS6300, for posting only in assigned private Georgia Tech repositories.
 *
 * This is an test class for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * You should implement your tests in this class.  Do not change the test names.
 */

public class MyCustomStringTest {
    private MyCustomStringInterface mycustomstring;
    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }
    @After
    public void tearDown() {
        mycustomstring = null;
    }
    //Test Purpose: This is the first instructor example test.
    //Testing for mostCommonChar function
    @Test
    public void testMostCommonChar1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals('t', mycustomstring.mostCommonChar());
    }
    //Test empty string
    @Test(expected = NullPointerException.class)
    public void testMostCommonChar2() {
        mycustomstring.setString("");
        mycustomstring.mostCommonChar();
    }
    //Test null value set
    @Test(expected = NullPointerException.class)
    public void testMostCommonChar3() {
        mycustomstring.setString(null);
        mycustomstring.mostCommonChar();
    }
    //Test for not setting the value
    @Test (expected = NullPointerException.class)
    public void testMostCommonChar5() {
        mycustomstring.mostCommonChar();
    }
    //String more than one letter equal
    @Test
    public void testMostCommonChar6() {
        mycustomstring.setString("dddd uuuuuu iiiiiii 18293fdmkal aaaa");
        assertEquals('i', mycustomstring.mostCommonChar());
    }
    @Test
    //String special characters + more than one letter sequence
    public void testMostCommonChar4() {
        mycustomstring.setString("This is @!@#$a T*est to verify r%emoval of 9(special) characters.");
        assertEquals('t', mycustomstring.mostCommonChar());
    }


    //Test Purpose: This is the second instructor example test.
    @Test
    public void testFilterLetters1() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! H3y, L3t'5 put 50m 161ts in this 5tr1n6!11!1", mycustomstring.filterLetters('E', false));
    }
    //Test Purpose: This the third instructor example test.
    @Test
    public void testFilterLetters2() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("1234!!! 3, 3'5  50 d161   516!11!1", mycustomstring.filterLetters('E', true));
    }
    //Test for a declared null variable
    @Test (expected = NullPointerException.class)
    public void testFilterLetters3() {
        mycustomstring.setString(null);
        mycustomstring.filterLetters('E', true);
    }
    //Test for not even a declared variable
    @Test (expected = NullPointerException.class)
    public void testFilterLetters4() {
        mycustomstring.filterLetters('E', true);
    }
    //Test for illegal out of bounds exception
    @Test (expected = IllegalArgumentException.class)
    public void testFilterLetters5() {
        mycustomstring.setString("1234!!! H3y, L3t'5 put 50me d161ts in this 5tr1n6!11!1");
        mycustomstring.filterLetters('5', true);
    }
    //Pass in all upper case letters
    @Test
    public void testFilterLetters6() {
        mycustomstring.setString("EEEEEBJDKFLA89392023849032849032890583JDKLFJALKFDJSKALFJDSKLA");
        assertEquals("BDA89392023849032849032890583DADADA", mycustomstring.filterLetters('E', true));
    }
    //Pass in special characters
    @Test
    public void testFilterLetters7() {
        mycustomstring.setString("Welcome @$$# T@ CS 63@@");
        assertEquals("Wlom @$$# T@ S 63@@", mycustomstring.filterLetters('E', false));
    }
    ///Test only special characters
    @Test
    public void testFilterLetters8() {
        mycustomstring.setString("_@%^&($#*($*#@($*#@(");
        assertEquals("_@%^&($#*($*#@($*#@(", mycustomstring.filterLetters('E', false));
    }
    //Test only spaces
    @Test
    public void testFilterLetters9() {
        mycustomstring.setString("                   ");
        assertEquals("                   ", mycustomstring.filterLetters('E', true));
    }
    //Test special character in filterLetters call
    @Test (expected = IllegalArgumentException.class)
    public void testFilterLetters10() {
        mycustomstring.setString("Welcome @$$# T@ CS 63@@");
        mycustomstring.filterLetters('@', true);
    }
    //Test case with boolean values
    @Test
    public void testFilterLetters11() {
        mycustomstring.setString("true false true false");
        assertEquals(" a  a", mycustomstring.filterLetters('E', true));
    }
    //Test case sensitvitiy
    @Test
    public void testFilterLetters12() {
        mycustomstring.setString("HeLlo ThEre");
        assertEquals(" ", mycustomstring.filterLetters('E', true));
    }
    //Test Purpose: This is the fourth instructor example test.
    @Test
    public void testConvertDigitsToProductsInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToProductsInSubstring(17, 27);
        assertEquals("I'd b3tt3r put 50me 6 d61ts in this 5tr1n6, right?", mycustomstring.getString());
    }
    //Test Purpose: This is the fifth instructor example test, demonstrating a test for an exception.  Your exceptions should be tested in this format.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring2() {
        mycustomstring.convertDigitsToProductsInSubstring(2, 10);
    }
    //Illegal argument with start position less than 1
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToProductsInSubstring3() {
        mycustomstring.setString("I'd b3tt3r put 50me 123 d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToProductsInSubstring(-1, 10);
    }
    //Illegal argument with value set and start position is greater than end position
    @Test (expected = IllegalArgumentException.class)
    public void testConvertDigitsToProductsInSubstring4() {
        mycustomstring.setString("Java Beans");
        mycustomstring.convertDigitsToProductsInSubstring(20, 10);
    }
    //Test custom string null set
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring5() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToProductsInSubstring(1, 10);
    }
    //Test all numbers
    @Test
    public void testConvertDigitsToProductsInSubstring6() {
        mycustomstring.setString("123482  4832 23839  2304038  29034");
        mycustomstring.convertDigitsToProductsInSubstring(10, 17);
        assertEquals("123482  448 1449  2304038  29034", mycustomstring.getString());
    }
    //Test not null and end position is greater than custom string length
    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToProductsInSubstring7() {
        mycustomstring.setString("Java Beans");
        mycustomstring.convertDigitsToProductsInSubstring(1, 25);
    }
    //Test with special characters
    @Test
    public void testConvertDigitsToProductsInSubstring8() {
        mycustomstring.setString("J@v@ Be@ns 12$55&541");
        mycustomstring.convertDigitsToProductsInSubstring(11, 16);
        assertEquals("J@v@ Be@ns 2$25&541", mycustomstring.getString());
    }
    //Same number sequence
    @Test
    public void testConvertDigitsToProductsInSubstring9() {
        mycustomstring.setString("1234 1234");
        mycustomstring.convertDigitsToProductsInSubstring(1, 9);
        assertEquals("24 24", mycustomstring.getString());
    }
    //Test no change
    @Test
    public void testConvertDigitsToProductsInSubstring10() {
        mycustomstring.setString("true ++");
        mycustomstring.convertDigitsToProductsInSubstring(1, 3);
        assertEquals("true ++", mycustomstring.getString());
    }

}
