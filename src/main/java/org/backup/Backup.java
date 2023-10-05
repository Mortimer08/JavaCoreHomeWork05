package org.backup;

import java.io.*;

public class Backup {
    public static void main(String[] args) throws IOException {
        String backUpPath = "/home/mort/.backup";
        String resourcePath = "/home/mort/.conky";
        File backUpDirectory = new File(backUpPath);
        backUpDirectory.mkdir();
        backup((new File(resourcePath)), resourcePath, backUpPath);
    }

    public static void backup(File resourceFile, String resourcePath, String backUpPath) throws IOException {

        String backUpName = backUpPath + resourceFile.getPath().substring(resourcePath.length());
        if (resourceFile.isDirectory()) {
            File newFile = new File(backUpName);
            newFile.mkdir();
        } else {
            copyFile(resourceFile.getPath(), backUpName);
        }
        File[] files = resourceFile.listFiles();
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getPath());
                backup(files[i], resourcePath, backUpPath);
        }
    }

    public static void copyFile(String inputFileName, String outputFileName) throws IOException {
        System.out.printf("Copy File: %s\n\t to: %s\n", inputFileName, outputFileName);
        try (FileOutputStream os = new FileOutputStream(outputFileName)) {
            int c;
            try (FileInputStream is = new FileInputStream(inputFileName)) {
                while ((c = is.read()) != -1) {
                    os.write(c);
                }
            }
        }
    }
}
