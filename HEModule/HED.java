package HEModule;

import java.math.BigInteger;
import java.util.Random;

/**
 * Homomorphic Encryption class
 */

public class HED {

    // Store the keys, in the file
    static String FILENAME = "HEModule/keyStorage";
    static Paillier paillier;
    static FileOps fileOps;
    static BigInteger r;


    static {

        fileOps = new FileOps();
        paillier = new Paillier();

        // check if the file already exists
        if (fileOps.check(FILENAME)) {

            BigInteger[] temp = fileOps.getKey(FILENAME);
            paillier.keyGeneration(512, 62, temp[0], temp[1]);
            r = temp[2];

        }

        // if the file does not exist, create the file with the keys
        else {

            // temp = [p, q, r]

            r = new BigInteger(512, new Random());
            paillier.keyGeneration(512, 62);

            BigInteger[] temp;

            // Returns [p, q]
            temp = paillier.getPQ();

            temp[2] = new BigInteger("" + r);

            fileOps.fileWrite(temp, FILENAME);

        }
    }

    public static String encryptHE(String input) {

        BigInteger ena = paillier.encryptString(input, r);

        String ena_string = ena.toString();

        return ena_string;
    }

    public static String decryptHE(String input) {

        String dec = paillier.decryptString(new BigInteger(input));

        return dec;
    }
}
