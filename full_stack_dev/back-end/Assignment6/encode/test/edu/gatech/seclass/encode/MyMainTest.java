package edu.gatech.seclass.encode;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class MyMainTest {
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
        String args[] = {inputFile.getPath(), "-a"};
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
        String args[] = {inputFile.getPath(), "-a"};
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
        String args[] = {inputFile.getPath(), "-a"};
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
        String args[] = {inputFile.getPath(), "-k", "Ajfkdlaj"};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "Ajfkdlaj;391843!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-r to test if the remove functions works for a string
    //Test Frame 27
    @Test
    public void encodeTest7() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {inputFile.getPath(), "-r", "Ajfkdlaj"};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "fFDJAKFLDALE;du391843!@#$$^^&$!%$!%#!";
        assertEquals("The result received is incorrect!", expected, actual);
    }

    //-c to test if the capitalization function works to reverse capitalization
    //Test Frame 43
    @Test
    public void encodeTest8() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {inputFile.getPath(), "-c", "Ajfkdlaj"};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "aJFKDLAJfFDJAKFLDALE;du391843!@#$$^^&$!%$!%#!";
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
    @Test (expected = Exception.class)
    public void encodeTest10() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {inputFile.getPath(), "-k", "-r"};
        Main.main(args);
    }

    //Missing file path and only one argument
    //Test Frame 60
    @Test (expected = Exception.class)
    public void encodeTest11() throws Exception {
        String args[] = {"-a"};
        Main.main(args);
    }

    //Empty arguments and no file path
    //Test frame 61
    @Test (expected = Exception.class)
    public void encodeTest12() throws Exception {
        File inputFile = createInputFile(mixedString);
        String args[] = {};
    }

    //Combination of arc for testing encode, remove, and capitalize
    //Test Frame 53
    @Test
    public void encodeTest13() throws Exception {
        File inputFile = createInputFile(allCapsString);
        String args[] = {inputFile.getPath(), "-a", "-r", "ASDFKLFDSLJ", "-c", "ASDFKLFDSLJ"};
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
        String args[] = {inputFile.getPath(), "-a", "-k", "ASDFKLFDSLJ", "-c", "ASDFKLFDSLJ"};
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "zhwupouwhoq%$#@#%$*^%89378974293";
        assertEquals("The values are different!", expected, actual);
    }

    //Length 0 input for file
    //Test Frame 11
    @Test
    public void encodeTest15() throws Exception {
        File inputFile = createInputFile(zeroString);
        String args[] = {inputFile.getPath()};
        Main.main(args);
        String results = getFileContent(inputFile.getPath());
        Boolean expected = true;
        Boolean actual = true;
        if(results.length() == 0) {
            actual = false;
        }
        assertEquals("The file has no values!", expected, actual);
    }
}
