package HEModule;

import java.math.BigInteger;
import java.util.HashMap;

public class ThreadSplit implements Runnable{

    private String inputText;

    HashMap<String,BigInteger> map=new HashMap<>();


    ThreadSplit(String inputText){
        this.inputText = inputText;
    }

    @Override
    public void run() {

        int temp = inputText.charAt(0);
        // System.out.println(temp);
        BigInteger num = new BigInteger(String.valueOf(temp));

        for (int i = 1; i < inputText.length(); i++) {

            temp = inputText.charAt(i);
            // System.out.println(temp);

            num = num.multiply(BigInteger.valueOf(1000)).add(BigInteger.valueOf(temp));
            // System.out.println("num:" + num);

        }
    }
}
