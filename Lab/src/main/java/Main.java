import com.sun.org.apache.xerces.internal.impl.io.UCSReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        createAsciiFile();
    }

    private static void createAsciiFile(){
        File file = new File("data.txt");
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < 20; i++){
                int randomNumber = (int) (Math.random() * 100);
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
