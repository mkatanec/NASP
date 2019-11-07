package hr.fer.avl.tree.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreationService {

    public static void main(String[] args) {
        if (args.length == 0) {
            createAsciiFile(1000, 50);
            //stress file
//            createAsciiFile(10, 100);
        } else {
            createAsciiFile(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
    }

    private static void createAsciiFile(final int range, final int length) {
        File file = new File("data.txt");
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < length; i++) {
                int randomNumber = (int) (Math.random() * range);
                bufferedWriter.write("" + randomNumber);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
