package HEModule;

import java.math.BigInteger;
import java.util.Random;

public class Paillier {
    private BigInteger p, q, lambda;
    public BigInteger n;

    public BigInteger nsquare;
    private BigInteger g;
    private int bitLength;

    public Paillier(int bitLengthVal, int certainty) {
        KeyGeneration(bitLengthVal, certainty);
    }

    public Paillier() {

    }

    public BigInteger[] getPQ() {
        BigInteger[] res = new BigInteger[4];
        res[0] = new BigInteger("" + p);
        res[1] = new BigInteger("" + q);
        return res;
    }

    public void KeyGeneration(int bitLengthVal, int certainty) {
        bitLength = bitLengthVal;

        p = new BigInteger(bitLength / 2, certainty, new Random());
        q = new BigInteger(bitLength / 2, certainty, new Random());

        n = p.multiply(q);
        nsquare = n.multiply(n);

        g = new BigInteger("2");

        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
                p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

        if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("Message" + "g is not good. Choose g again.");
        }
    }

    public void KeyGeneration(int bitLengthVal, int certainty, BigInteger a, BigInteger b) {
        bitLength = bitLengthVal;

        p = a;
        q = b;

        // n = p*q
        n = p.multiply(q);

        //n^2 = n*n
        nsquare = n.multiply(n);

        // Choose random 'g'
        g = new BigInteger("2");

        // lambda = lcm(p-1, q-1)
        // lambda = p-1 * q-1 / gcd(p-1, q-1)
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
                p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

        // If not right, choose another
        if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("Message" + "g is not good. Choose g again.");
        }
    }

    // Random r, 0 < r < n
    public BigInteger Encryption(BigInteger m, BigInteger r) {

        // ciphertext c = ((g^m) * (r^n)) mod (n*n)
        return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);
    }

    public BigInteger Decryption(BigInteger c) {
        BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);

        // plaintext m = (L * ((c^lambda) mod (n*n)) * mu mod n
        return c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

    public BigInteger EncryptString(String st, BigInteger r) {
        int temp = st.charAt(0);
        BigInteger num = new BigInteger(String.valueOf(temp));

        for (int i = 1; i < st.length(); i++) {
            temp = st.charAt(i);
            num = num.multiply(BigInteger.valueOf(1000)).add(BigInteger.valueOf(temp));
        }

        return Encryption(num, r);
    }

    public String DecryptString(BigInteger num) {
        BigInteger num1 = Decryption(num);
        System.out.println("SecondBig:" + String.valueOf(num1));
        int strc = num1.toString().length();
        System.out.println("strc length:" + String.valueOf(strc));

        String m = num1.toString();
        System.out.println("m string" + m);

        if (strc % 3 != 0) {
            m = "0" + m;
        }

        StringBuilder strd = new StringBuilder();

        for (int i = 0; i < m.length(); i += 3) {
            strd.append((char) (Integer.parseInt(m.substring(i, i + 3))));
            System.out.println("Process:" + String.valueOf((strd)));
        }
        return strd.toString();
    }
}
