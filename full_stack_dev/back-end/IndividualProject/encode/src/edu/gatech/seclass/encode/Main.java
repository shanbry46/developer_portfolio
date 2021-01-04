package edu.gatech.seclass.encode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {

    /**
     * This is a Georgia Tech provided code template for use in assigned private GT repositories. Students and users of this template
     * code are advised not to share it with other students or to make it available on publicly viewable websites including
     * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
     * <p>
     * Empty Main class for starting the Individual Project.
     */
    public static void main(String[] args) {
        HashMap valueMapper = evaluateArguments(args);
        if (valueMapper.get('k').equals(true) && valueMapper.get('r').equals(true)) {
            System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
            return;
        }
        if (args.length == 0) {
            usage();
            return;
        }
        if (args[args.length - 1].matches("-r") || args[args.length - 1].matches("-c") ||
                args[args.length - 1].matches("-a") || args[args.length - 1].matches("-k")) {
            System.err.println("Please resubmit your command with a file name.");
            return;
        }
        String encryptedFile = getFileContent(args[args.length - 1]);
        if (encryptedFile == null) {
            System.err.println("File Not Found");
            return;
        }
//        if(encryptedFile.length() == 0) {
//            System.err.println("The file has no values!");
//            return;
//        }
        boolean encrypt = false;
        int cipherValue = 0;
        try {
            for (int i = 0; i < args.length - 1; i++) {
                if (args[i].matches("-l") && ((args[+1]).contains("txt") || args[i + 1].contains("tmp") || args[i + 1].contains("-"))) {
                    usage();
                    return;
                } else if (args[i].matches("-l") && !(args[+1]).contains("txt") && !args[i + 1].contains("tmp") && !args[i + 1].contains("-")) {
                    String stringOutput = evaluateAllString(encryptedFile, args[i + 1]);
                    System.out.println(stringOutput);
                }
                else if (args[i].matches("-r") && !args[i + 1].contains("txt") && !args[i + 1].contains("tmp") && !args[i + 1].contains("-")) {
                    encryptedFile = removeString(encryptedFile, args[i + 1]);
                }
                else if (args[i].matches("-r") && (args[i + 1].contains("txt") || args[i + 1].contains("tmp") || args[i + 1].contains("-"))) {
                    System.err.println("Please resubmit option -r with a valid string of values to remove from your source file.");
                    return;
                } else if (args[i].matches("-k") && !args[i + 1].contains("txt") && !args[i + 1].contains("tmp") && !args[i + 1].contains("-")) {
                    if (!args[i + 1].matches("[A-Za-z0-9]+")) {
                        System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
                        return;
                    }
                    encryptedFile = keepString(encryptedFile, args[i + 1]);
                } else if (args[i].matches("-k") && (args[i + 1].contains("txt") || args[i + 1].contains("tmp") || args[i + 1].contains("-"))) {
                    System.err.println("Please resubmit option -k with a valid string of values to remove from your source file.");
                    return;
                } else if (args[i].matches("-c") && !args[i + 1].contains("txt") && !args[i + 1].contains("tmp") && !args[i + 1].contains("-")) {
                    if(!args[i+1].matches("^[a-zA-Z]*$")) {
                        usage();
                        return;
                    }
                    encryptedFile = reverseCapitalization(encryptedFile, args[i + 1]);
                } else if (args[i].matches("-c") && (args[i + 1].contains("txt") || args[i + 1].contains("tmp") || args[i + 1].contains("-"))) {
                    usage();
                    return;
                } else if (args[i].matches("-a")) {
                    if (Character.isDigit(args[i + 1].charAt(0))) {
                        cipherValue = Integer.parseInt(args[i + 1]);
                    }
                    encrypt = true;
                }
                else if(args[i].contains("-") && args[i] != "-a" && args[i] != "-r" && args[i] != "-c" && args[i] != "-k" && args[i] != "-l") {
                    usage();
                    return;
                }
            }
            //Supposed to happen last
            if (encrypt) {
                encryptedFile = atbashCipher(encryptedFile, cipherValue);
            }
            if (args.length == 1 && args[0].contains("txt") || args[0].contains("tmp")) {
                System.out.println("Inside Reverse Capitalization");
                encryptedFile = reverseCapitalization(encryptedFile, encryptedFile);
            }
            writeFileContents(encryptedFile, args[args.length - 1]);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void usage() {
        System.err.println("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>");
    }

    public static String atbashCipher(String fileContents, Integer value) {
        StringBuilder encryptedText = new StringBuilder();
        String charAsString = "";
        for (char c : fileContents.toCharArray()) {
            char currentChar = c;
            if (Character.isLetter(c) && Character.isLowerCase(c)) {
                currentChar = (char) ('a' + 'z' - c);
                currentChar = (char) ((currentChar - 'a' + (26 - value)) % 26 + 'a');
            } else if (Character.isLetter(c) && Character.isUpperCase(c)) {
                currentChar = (char) ('A' + 'Z' - c);
                currentChar = (char) ((currentChar - 'A' + (26 - value)) % 26 + 'A');
            }
            encryptedText.append(currentChar);
        }
        return encryptedText.toString();
    }
    
    public static String removeString(String fileContents, String removeString) {
        for (char c : removeString.toCharArray()) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                fileContents = fileContents.replaceAll(Character.toString(c).toUpperCase(), "");
                fileContents = fileContents.replaceAll(Character.toString(c).toLowerCase(), "");
            }
        }
        return fileContents;
    }

    public static String keepString(String fileContents, String keepString) {
        StringBuilder reducedString = new StringBuilder();
        for (char c : fileContents.toCharArray()) {
            boolean charEval = false;
            outerloop:
            for (char ch : keepString.toCharArray()) {
                if (Character.isAlphabetic(ch)) {
                    if (c == Character.toLowerCase(ch) || c == Character.toUpperCase(ch)) {
                        reducedString.append(c);
                        charEval = true;
                        break outerloop;
                    }
                } else if (Character.isDigit(ch) && c == ch) {
                    reducedString.append(c);
                    charEval = true;
                    break outerloop;
                }
            }
            if (!charEval && !Character.isLetter(c) && !Character.isDigit(c)) {
                reducedString.append(c);
            }
        }
        return reducedString.toString();
    }

    public static String reverseCapitalization(String fileContents, String input) {
        StringBuilder updatedStringB = new StringBuilder();
        for (char cf : fileContents.toCharArray()) {
            char currentChar = cf;
            for (char ci : input.toCharArray()) {
                if (Character.isLetter(cf) && (Character.toUpperCase(ci) == cf || Character.toLowerCase(ci) == cf)) {
                    currentChar = (char) (cf ^ 32);
                }
            }
            updatedStringB.append(currentChar);
        }
        return updatedStringB.toString();
    }

    public static String evaluateAllString(String fileContents, String input) {
        HashMap<Character, Integer> counter = new HashMap<>();
        String valuesToTest = "";
        //Set hash-map
        for (char cha : input.toCharArray()) {
            counter.put(cha, 0);
        }
        String[] nBreaks = fileContents.split("\n");
        String[] rBreaks = fileContents.split("\r");
        valuesToTest = atbashCipher(input, 0);
        valuesToTest = reverseCapitalization(valuesToTest, valuesToTest);
        int sum = 0;
        String[] lines;
        boolean evalString = false;
        String updatedString = "";
        if(nBreaks.length>1) {
            lines = nBreaks;
        }
        else {
            lines = rBreaks;
        }
        for (int i = 0; i < lines.length; i++) {
            //Reset values
            for (char cha : input.toCharArray()) {
                counter.put(cha, 0);
            }
            sum = 0;
            if (lines[i].contains(valuesToTest)) {
                evalString = true;
            }
            for (char ch : input.toCharArray()) {
                if (lines[i].contains(Character.toString(ch)) && counter.get(ch) != 1) {
                    counter.put(ch, 1);
                }
            }
            for (int value : counter.values()) {
                sum += value;
            }
            if (evalString && sum == input.length() || sum == input.length()) {
                updatedString += lines[i] + "\n";
            }
        }
        return updatedString;
    }

    public static char encryptLetter(char c) {
        char encryptedChar = 'a';

        if (Character.isLetter(c) && Character.isLowerCase(c)) {
            encryptedChar = (char) ('a' + ('z' - c));
        } else if (Character.isLetter(c) && Character.isUpperCase(c)) {
            encryptedChar = (char) ('A' + ('Z' - c));
        }
        return encryptedChar;
    }

    public static HashMap evaluateArguments(String[] args) {
        boolean evaluateValidity = false;
        HashMap<Character, Boolean> valueMapper = new HashMap<Character, Boolean>();
        boolean kFlag = false;
        boolean rFlag = false;
        boolean aFlag = false;
        boolean cFlag = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i] == "-k") {
                kFlag = true;
            } else if (args[i] == "-r") {
                rFlag = true;
            } else if (args[i] == "-c") {
                cFlag = true;
            } else if (args[i] == "-a") {
                aFlag = true;
            }
        }
        valueMapper.put('k', kFlag);
        valueMapper.put('r', rFlag);
        valueMapper.put('c', cFlag);
        valueMapper.put('a', aFlag);
        return valueMapper;
    }

    public static String validateArguments(String arg) {
        return "test";
    }

    //Read File Utility
    private static String getFileContent(String filename) {
        Charset charset = StandardCharsets.UTF_8;
        String content = null;
        try {
            content = Files.readString(Paths.get(filename), charset);
        } catch (IOException e) {
        }
        return content;
    }

    private static void writeFileContents(String input, String fileName) throws Exception {
        Path path = Paths.get(fileName);
        byte[] strToBytes = input.getBytes();
        Files.write(path, strToBytes);
    }
}
