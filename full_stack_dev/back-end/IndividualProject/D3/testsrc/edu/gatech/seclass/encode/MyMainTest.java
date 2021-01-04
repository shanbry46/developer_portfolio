package edu.gatech.seclass.encode;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyMainTest {
    private static final String USAGE = "Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>";
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }
    //Strings for encryption
    private static final String mixedString = "AjfkdlajfFDJAKFLDALE;du391843!@#$$^^&$!%$!%#!";
    private static final String allCapsString = "ASDFKLFDSLJ%$#@#%$*^%89378974293";
    private static final String allLowerString = "asdjfkldsjfueiuwejweie8!@#$^%$^$@84930185038";
    private static final String zeroString = "";

    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }

    //Length of string is less than zero then evaluated to false
    //Test Frame 13
    //Failure Type: BUG: bug 6, encode fails when just on argument is passed in, probably because it assumed the user would pass in a command and a file name and is trying to access the file name as the last index in the array or length-1.
    @Test
    public void encodeTest1() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String results = getFileContent(inputFile.getPath());
        Boolean expected = true;
        Boolean actual = true;
        if(results.length() < 0) {
            actual = false;
        }
        assertEquals("The file has no values!", expected, actual);
    }

    //Size of characters is greater than 0
    //Test Frame 12
    @Test
    public void encodeTest2() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String results = getFileContent(inputFile.getPath());
        Boolean expected = true;
        Boolean actual = false;
        if(results.length() > 0) {
            actual = true;
        }
        assertEquals("The file has no a length less than 1!", expected, actual);
    }

    //- a is used to encode all alphabetic values with only capitalized letters
    // Test Frame 14
    @Test
    public void encodeTest3() throws Exception {
        File inputFile = createInputFile(allCapsString);
        String args[] = {"-a", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "ZHWUPOUWHOQ%$#@#%$*^%89378974293";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //- a flag used to encode an all lower case values string
    //Test Frame 18
    @Test
    public void encodeTest4() throws Exception {
        File inputFile = createInputFile(allLowerString);
        String args[] = {"-a", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zhwqupowhqufvrfdvqdvrv8!@#$^%$^$@84930185038";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-a flag used to encode on all mixed values including special chars, upper, and lower case vars
    //Test Frame 16
    @Test
    public void encodeTest5() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-a", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "ZqupwozquUWQZPUOWZOV;wf391843!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-k to test if the keep function works for a string of mixed values
    //Test Frame 36
    @Test
    public void encodeTest6() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = { "-k", "Ajfkdlaj", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "AjfkdlajfFDJAKFLDAL;d!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-r to test if the remove functions works for a string
    //Test Frame 27
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-r", "Ajfkdlaj", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "E;u391843!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-c to test if the capitalization function works to reverse capitalization
    //Test Frame 43
    //Failure Type: BUG: bug 9,
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-c", "Ajfkdlaj", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "aJFKDLAJFfdjakfldalE;Du391843!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //no flags selected default to c
    //Test Frame 51
    @Test
    public void encodeTest9() throws Exception {
        File inputFile = createInputFile(allCapsString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "asdfklfdslj%$#@#%$*^%89378974293";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //kr both selected invalid, throw error
    //Test Frame 45
    //Failure Type: Corner Case: encode failed because I was expecting usage instructions when a user inputs the -k and the -r values together or when the user submits just a command and no string argument.
    @Test
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-k", "-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("", errStream.toString().trim());
    }

    //Missing file path and only one argument
    //Test Frame 60
    //Failure Type: Corner Case: encode probably failed because the statement passed back when there was no file found, but an argument was passed in was different in my version of encode and was more descriptive for passing in arguments.
    @Test
    public void encodeTest11() throws Exception {
        String args[] = {"-a"};
        Main.main(args);
        assertEquals("File Not Found", errStream.toString().trim());
    }

    //Empty arguments and no file path
    //Test frame 59
    @Test
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {};
        Main.main(args);
        assertEquals("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>", errStream.toString().trim());
    }

    //Combination of arc for testing encode, remove, and capitalize
    //Test Frame 53
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(allCapsString);
        String args[] = {"-a", "-r", "ASDFKLFDSLJ", "-c", "ASDFKLFDSLJ", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "%$#@#%$*^%89378974293";
        assertEquals("The values are different!", expected, actual);
    }

    //Combination of akc for testing encode, keep, and capitalize
    //Test Frame 55
    @Test
    public void encodeTest14() throws Exception {
        File inputFile = createInputFile(allCapsString);
        String args[] = {"-a", "-k", "ASDFKLFDSLJ", "-c", "ASDFKLFDSLJ", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zhwupouwhoq%$#@#%$*^%";
        assertEquals("The values are different!", expected, actual);
    }

    //Length 0 input for file
    //Test Frame 11
    //Failure Type: BUG: bug 6, encode probably fails when just on argument is passed in, probably because it assumed the user would pass in a command and a file name and is trying to access the file name as the last index in the array or length-1.
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        assertTrue(errStream.toString().trim().isBlank());
    }

    // Purpose: To Test if the file has present string values
    // Frame #: 9
    @Test
    public void encodeTest16() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        Boolean actual = false;
        String contents = getFileContent(inputFile.getPath());
        if(contents.length() > 0) {
            actual = true;
        }
        Boolean expected = true;
        assertEquals("String Values Greater than 0", expected, actual);
    }

    // Purpose: Testing that the reader receives back type string
    // Frame #: 22
    @Test
    public void encodeTest17() throws Exception {
        File inputFile = createInputFile("a");
        String args[] = {inputFile.getPath()};
        String contents = getFileContent(inputFile.getPath());
        Main.main(args);
        String actual =  contents.getClass().getSimpleName();
        String expected = "String";
        assertEquals("Verifying that a return is string", expected, actual);
    }

    // Purpose: Testing to verify that an output with all lower case letters is all uppercase values
    // Frame #: 18
    @Test
    public void encodeTest18() throws Exception {
        File inputFile = createInputFile("abjdkfjiequreopq");
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String contents = getFileContent(inputFile.getPath());
        assertEquals("Converted to all caps by default", "ABJDKFJIEQUREOPQ" , contents);
    }

    // Purpose: -r option specified and length of string is greater than 0
    // Frame #: 25
    @Test
    public void encodeTest19() throws Exception {
        File inputFile = createInputFile("Apple Pie");
        String args[] = {"-r", "p", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "Ale ie";
        assertEquals("R flag passed in and processes as expected.",expected, actual);
    }

    // Purpose: -k option specified
    // Frame #: 29
    @Test
    public void encodeTest20() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-k", "test", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "E;!@#$$^^&$!%$!%#!";
        assertEquals("Test k flag and values specified",expected, actual);
    }

    // Purpose: Verifying -r flag string passed in and string value is zero
    // Frame #: 24
    // Failure Type: BUG: bug 3, encode probably fails because there was no string in the file provided and the program is likely trying to access an index of a string array while evaluating it.
    @Test
    public void encodeTest21() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {"-r", "", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "";
        assertEquals("Test r flag and nothing back",expected, actual);
    }

    // Purpose: Verifying -k input is successful with 0 length string
    // Frame #: 33
    //Failure Type: BUG: bug 6, encode probably fails when just on argument is passed in, probably because it assumed the user would pass in a command and a file name and is trying to access the file name as the last index in the array or length-1.
    @Test
    public void encodeTest22() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "";
        assertEquals("Test k string is paseed in correctly",expected, actual);
    }

    // Purpose: To test response for r flag and failure
    // Frame #: 23
    //Failure Type: BUG: bug 7, encode fails probably because the file removes all characters from the file rather than flagging the user to denote a string value to remove.
    @Test
    public void encodeTest23() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-r", inputFile.getPath()};
        Main.main(args);
        assertEquals("Please resubmit option -r with a valid string of values to remove from your source file.", errStream.toString().trim());
    }

    // Purpose: To test response for k flag and no values for failure
    // Frame #: 32
    //Failure Type: BUG: bug 7, encode fails probably because there is no usage warning given to the user when they submit -k without a valid string of values to keep rather the empty output.
    @Test
    public void encodeTest24() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-k", inputFile.getPath()};
        Main.main(args);
        assertEquals("Please resubmit option -k with a valid string of values to remove from your source file.", errStream.toString().trim());
    }

    // Purpose: To test response for c flag and failure
    // Frame #: 38
    @Test
    public void encodeTest25() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>", errStream.toString().trim());
    }

    // Purpose: To test response for c flag and length 0 of string input
    // Frame #: 40
    @Test
    public void encodeTest26() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {"-c", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "";
        assertEquals(expected, actual);
    }

    // Purpose: To test response for multiple lines requiring string input and verify that the first input value is required.
    // Frame #: 61
    //Failure Type: Corner Case: encode failed because the statement passed back when there were no string values is empty rather than the custom string message I had denoting to the user.
    @Test
    public void encodeTest27() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-k", "-c", inputFile.getPath()};
        Main.main(args);
        assertEquals("", errStream.toString().trim());
    }

    // Purpose: To test when the user tries to extract numeric characters using k
    // Frame #: 37
    @Test
    public void encodeTest28() throws Exception {
        File inputFile = createInputFile("Abc123");
        String args[] = {"-k", "123", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "123";
        assertEquals(expected, actual);
    }

    // Purpose: To test response for c flag and passing in numbers to reverse values on
    // Frame #: 44
    @Test
    public void encodeTest29() throws Exception {
        File inputFile = createInputFile("1234895417847584#@%#^$#");
        String args[] = {"-c", "1234", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "1234895417847584#@%#^$#";
        assertEquals(expected, actual);
    }

    // Purpose: To encrypt a mixed string value, keep values in a specified string, and reverse capitalization
    // Frame #: 47
    @Test
    public void encodeTest30() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"-a", "-c", "abc", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zqupwoZquUWQzPUOWzOV;wf391843!@#$$^^&$!%$!%#!";
        assertEquals(expected, actual);
    }

    //Failure Type: BUG: bug 10, encode didn't filter argument input for valid string values
    @Test
    public void encodeTest31() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {"", "", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>", errStream.toString().trim());
    }

    //Failure Type: BUG: bug 10, encode probably doesn't filter argument input for valid string values
    @Test
    public void encodeTest32() throws Exception {
        File inputFile = createInputFile("Test Value.");
        String args[] = {"-c", "", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-a [integer]] [-r string | -k string] [-c string] [-l string] <filename>", errStream.toString().trim());
    }

    //Failure Type: BUG: bug 8, encode probably fails because when evaluating the characters passed in with the the string of values and one or more chars is not available in the string
    @Test
    public void encodeTest33() throws Exception {
        File inputFile = createInputFile("abcd");
        String args[] = {"-l", "z", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zqupwoZquUWQzPUOWzOV;wf391843!@#$$^^&$!%$!%#!";
        assertEquals(expected, actual);
    }

    //Failure Type: BUG: bug 1, encode probably fails if you use a negative value less than 1 to shift your cipher
    @Test
    public void encodeTest34() throws Exception {
        File inputFile = createInputFile("asdf");
        String args[] = {"-a", "-2", inputFile.getPath()};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zqupwoZquUWQzPUOWzOV;wf391843!@#$$^^&$!%$!%#!";
        assertEquals(expected, actual);
    }
}
