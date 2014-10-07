package com.sti.tools.concat;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergeyi on 07.10.2014.
 */
public class Main2 {
    public static void execute(String[] args) throws IOException {

        if (args.length < 3)
            throw new IllegalArgumentException("Requires at least 3 parameters.");

        if (!args[args.length - 2].trim().equalsIgnoreCase(">"))
            throw new IllegalArgumentException("Wrong format. Must be: 'concat src1.txt [src2.txt ...] > dst.txt'");

        for (String arg : args) {
            if (arg == null || arg.trim().isEmpty())
                throw new IllegalArgumentException("Some arguments is empty.");
        }


        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {

            List<String> argList = Arrays.asList(args);
            List<String> srcNames = argList.subList(0, argList.size() - 2);

            writer = new BufferedWriter(new FileWriter(new File(args[args.length - 1])));

            String line;
            for (String srcName : srcNames) {
                reader = new BufferedReader(new FileReader(new File(srcName)));

                while ((line = reader.readLine()) != null) {
                    writer.write(line, 0, line.length());
                    writer.newLine();
                }
                reader.close();
                reader = null;
            }
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null)
                writer.close();
        }


    }
}
