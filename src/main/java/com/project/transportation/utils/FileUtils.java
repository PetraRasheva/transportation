package com.project.transportation.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void writeToFile(String content, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}