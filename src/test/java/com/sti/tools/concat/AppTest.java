package com.sti.tools.concat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        URL src1 = AppTest.class.getResource("/src1.txt");
        URL src2 = AppTest.class.getResource("/src2.txt");
        URL src3 = AppTest.class.getResource("/src3.txt");
        URL root = AppTest.class.getResource("/");
        assertNotNull(src1);
        assertNotNull(src2);
        assertNotNull(src3);
        File src1File = new File(src1.getPath());
        File src2File = new File(src2.getPath());
        File src3File = new File(src3.getPath());
        File dstFile = new File(root.getPath() + "dst.txt");


        try {
            Main.execute(new String[]{src1File.getPath(), src2File.getPath(), src3File.getPath(), ">", dstFile.getPath()});

            String[] trueLines = {
                    "f1r1",
                    "f1r2",
                    "f1r3",
                    "f2r1",
                    "f2r2",
                    "f2r3",
                    "f3r1",
                    "f3r2",
                    "f3r3"
            };
            checkFileLines(trueLines, dstFile);

        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(e.getLocalizedMessage(), false);
        }
    }

    private void checkFileLines(String[] trueLines, File file) {


        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            try {
                String line;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    assertEquals(trueLines[i++], line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException ignore) {
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
