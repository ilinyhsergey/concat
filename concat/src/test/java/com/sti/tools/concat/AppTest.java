package com.sti.tools.concat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.IOException;
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
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(e.getLocalizedMessage(), false);
        }
    }
}
