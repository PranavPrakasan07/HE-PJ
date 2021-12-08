package HEModule;

import java.math.BigInteger;
import java.sql.Timestamp;

class Main {
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
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

        execTime = (execTime/3);
        Paillier p = new Paillier();
        p.encryptStringParallel(entered_text, BigInteger.ONE);
        tsEnd = new Timestamp(System.currentTimeMillis());

        System.out.println("\nEnd Time : " + tsEnd);

        System.out.println("Execution Time: " + execTime + "ms");

        System.out.println("\nDecrypted Message:" + decrypted);

    }
}