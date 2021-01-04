package edu.gatech.seclass;

public class TestCoverageClass {

    public Integer testCoverageMethod1(int n) {
        int result = 0;
        result = 100 / n;
        result += 50;
        return result;
    }

    public void testCoverageMethod2() {
        /**
         * To achieve 100% branch coverage there must be 100% statement coverage.
         * In other words, the test case(s) must execute all of the lines in a
         * function to achieve 100% statement coverage and branches are comprised
         * of conditional statements which use more than one line of code to write
         * and execute.
         */
    }

    public void testCoverageMethod3() {
        /**
         * Since the definition of every test suite is defined as all possible test
         * suites and test cases, it is not possible for every test suite generated
         * to not reveal to the fault.  To expand further, one test suite could contain
         * test cases that don't trigger the division by zero fault and another test
         * suite could contain a test case that would trigger the division
         * by zero error.
         */
    }

    public void testCoverageMethod4() {
        /**
         * In this scenario, it is possible to create a test suite that achieves
         * 100% branch coverage without revealing a fault, however, it is impossible
         * to also create a method where every test suite that achieves a 100% statement
         * coverage will reveal the fault.
         *
         * Since statement coverage is the lowest level of coverage, faults are
         * usually realized at higher levels of testing such as branch or path coverage.
         * Additionally, since the fault is required to execute in every statement coverage
         * test suite, it must be thrown in the one of the branches.
         *
         * For example, if I had a method that took one input integer and evaluated if the
         * value was greater than 0 to perform some operation else to create the divide by zero
         * fault, this would produce an error for every statement coverage test suite, but would
         * not pass the branch coverage criteria because the fault would still be generated.
         */
    }

    public boolean testCoverageMethod5 (boolean a, boolean b) {
            int x = 2;
            int y = 4;
            if(a)
                x += 2;
            else
                y = y/x;
            if(b)
                y -= 4;
            else
                y -= 2;
            return ((x/y)>= 0);
    }

    // ================
//
    // Fill in column “output” with T, F, or E:
    //
// | a | b |output|
// ================
// | T | T |   E   |
// | T | F |   T   |
// | F | T |   F   |
// | F | F |   E   |
// ================
//
// Fill in the blanks in the following sentences with
// “NEVER”, “SOMETIMES” or “ALWAYS”:
//
// Test suites with 100% statement coverage SOMETIMES reveal the fault in this method.
// Test suites with 100% branch coverage SOMETIMES reveal the fault in this method.
// Test suites with 100% path coverage SOMETIMES reveal the fault in this method.
// ================

}
