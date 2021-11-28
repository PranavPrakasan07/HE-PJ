package HEModule;

import java.sql.Timestamp;

class Main {

    public static void main(String[] args) {

        String entered_text = "sed enim ut sem viverra enimenim enim virrara sed s";
        System.out.println(entered_text.length());

        String encrypted = HED.encryptHE(entered_text);
        System.out.println(encrypted.length());

        String decrypted = HED.decryptHE(encrypted);

        Timestamp tsStart = new Timestamp(System.currentTimeMillis());
        System.out.println(tsStart);
        System.out.println("ENA-OBJ:" + encrypted);

        Timestamp tsEnd = new Timestamp(System.currentTimeMillis());
        System.out.println(tsEnd);

        System.out.println("execution Time: " + (tsEnd.getTime() - tsStart.getTime()));

        System.out.println("DEC-OBJ:" + decrypted);

    }
}