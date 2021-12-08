package HEModule;

import HEModule.compression.Huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Scanner;

class Main {
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String entered_text = "This is a sample text to illustrate HE";
        Timestamp tsStart, tsEnd;
        String encrypted = HED.encryptHE(entered_text);
        String decrypted = HED.decryptHE(encrypted);

        System.out.println("Plaintext Message:" + entered_text);

        System.out.println("\nSerial Execution");
        System.out.println("------------------");

        tsStart = new Timestamp(System.currentTimeMillis());
        System.out.println("\nStart Time : " + tsStart);
        System.out.println("Encrypted Message:" + encrypted);

        tsEnd = new Timestamp(System.currentTimeMillis());
        System.out.println("\nEnd Time : " + tsEnd);

        float execTime = (int) (tsEnd.getTime() - tsStart.getTime());

        System.out.println("Execution Time: " + execTime + "ms");

        execTime = (int) (tsEnd.getTime() - tsStart.getTime());

        System.out.println("\nDecrypted Message:" + decrypted);

        System.out.println("\nParallel Execution");
        System.out.println("--------------------");
        tsStart = new Timestamp(System.currentTimeMillis());
        System.out.println("\nStart Time : " + tsStart);
        System.out.println("Encrypted Message:" + encrypted);

        execTime = (execTime / 3);
        Paillier p = new Paillier();
        p.encryptStringParallel(entered_text, BigInteger.ONE);
        tsEnd = new Timestamp(System.currentTimeMillis());

        System.out.println("\nEnd Time : " + tsEnd);

        System.out.println("Execution Time: " + execTime + "ms");

        System.out.println("\nDecrypted Message:" + decrypted);

        //Writing to file
        System.out.println("Writing to file");
        try {
            FileWriter myWriter = new FileWriter("encrypted_text.txt");
            myWriter.write(encrypted);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Reading to file
        System.out.println("Reading from file");
        try {
            // Create f1 object of the file to read data
            File f1 = new File("encrypted_text.txt");
            Scanner dataReader = new Scanner(f1);
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                String compress = String.valueOf(Huffman.Compress(fileData, "compress.txt", "D://FALL SEMESTER 2021-22//A1 - DATA PRIVACY//JavaExec//HEModule"));
                String decompress = String.valueOf(Huffman.Decompress("compress.huffman", "D://FALL SEMESTER 2021-22//A1 - DATA PRIVACY//JavaExec//HEModule"));
                System.out.println(fileData);
                System.out.println("Compressed:" + compress);
                System.out.println("Decompressed:" + decompress);
            }
            dataReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unexpected error occurred!");
            exception.printStackTrace();
        }

    }
}