//Package Declaration
//Author: Shannon M. Bryant
//Date: 9/3/2019

//Comments: Initial Version
package edu.gatech.seclass;

//Import Utilized Java Classes

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class MyCustomString implements MyCustomStringInterface{
    public String customString;

    //Function to get a string value
    @Override
    public String getString() {
        return customString;
    }
    //Function to set a string value
    public void setString(String string) {
        customString = string;
    }
    @Override
    //Function to determine the most common alphanumeric character in a string
    public char mostCommonChar() {
        if(customString == null || customString.isEmpty()) throw new NullPointerException();
            System.out.println(customString);
            String strTemp = customString.replaceAll("[\\W]|_", "");
            Map<Character, Integer> hmCharCount = new LinkedHashMap<Character, Integer>();
            for (int i=0; i<strTemp.length(); i++) {
                char ch = Character.toLowerCase(strTemp.charAt(i));
                Integer intTemp = (Integer) hmCharCount.get(ch);
                if (intTemp != null) {
                    hmCharCount.put(ch, ++intTemp);
                }
                hmCharCount.putIfAbsent(ch, 1);
            }
            System.out.println(hmCharCount);
            int maxVal = 1;
            char value = 'a';
            for(Character key: hmCharCount.keySet()) {
                if(hmCharCount.get(key) > maxVal) {
                    value = key;
                    maxVal = hmCharCount.get(key);
                    System.out.println(key);
                }
            }
            System.out.println(value);
            return value;
    }

    @Override
    public String filterLetters(char n, boolean more) {
            if(customString == null) throw new NullPointerException();
            if(Character.isLetter(n) == false) throw new IllegalArgumentException();
            String strTemp = customString;
            System.out.println(strTemp);
            n = Character.toLowerCase(n);
            for (int k=0; k<customString.length(); k++) {
                char ch = Character.toLowerCase(customString.charAt(k));
                String s = Character.toString(ch);
                //Remove all letters that are greater than or equal to n
                if(more == true && ch >= n && Character.isLetter(ch) == true) {
                    strTemp = strTemp.replaceAll(s, "");
                    strTemp = strTemp.replaceAll(s.toUpperCase(), "");
                }
                //Remove all letters that are less than or equal to n
                if(more == false && ch <= n && Character.isLetter(ch) == true) {
                    strTemp = strTemp.replaceAll(s, "");
                    strTemp = strTemp.replaceAll(s.toUpperCase(), "");
                }
            }
            return strTemp;
    }

    @Override
    public void convertDigitsToProductsInSubstring(int startPosition, int endPosition) {
            if(startPosition < 1 || startPosition > endPosition) throw new IllegalArgumentException();
            if(customString == null || (customString != null && endPosition > customString.length())) throw new MyIndexOutOfBoundsException();
            String reuse = "";
            String tmpString = customString;
            List<Character> sequentsDigits = new ArrayList<>();
        for (int j=startPosition-1; j<=endPosition-1; j++) {
            int product = 1;
            char ch = tmpString.charAt(j);
                if (Character.isDigit(ch) == true && j < endPosition-1) {
                    sequentsDigits.add(ch);
                    reuse = reuse.concat(Character.toString(ch));
                }
                else if(Character.isDigit(ch) == true && j == endPosition-1) {
                    sequentsDigits.add(ch);
                    reuse = reuse.concat(Character.toString(ch));
                    System.out.println(reuse);
                    for (int k = 0; k < sequentsDigits.size(); k++) {
                        product *= Character.getNumericValue(sequentsDigits.get(k));
                        System.out.println(product);
                    }
                    customString = customString.replace(reuse, Integer.toString(product));
                    sequentsDigits.clear();
                    reuse = "";
                    System.out.println(customString);
                }
               else if ((Character.isDigit(ch) == false && sequentsDigits.size() > 0) || (sequentsDigits.size() > 0 && j == endPosition - 1)) {
                   System.out.println(reuse);
                    for (int k = 0; k < sequentsDigits.size(); k++) {
                        product *= Character.getNumericValue(sequentsDigits.get(k));
                        System.out.println(product);
                    }
                    customString = customString.replace(reuse, Integer.toString(product));
                    sequentsDigits.clear();
                    reuse = "";
                    System.out.println(customString);
                }
            }
    }
}


