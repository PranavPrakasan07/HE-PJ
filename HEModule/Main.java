package HEModule;

class Main {

    public static void main(String[] args) {

        String entered_text = "Trial is this";

        String encrypted = HED.encryptHE(entered_text);
        String decrypted = HED.decryptHE(encrypted);

        System.out.println("ENA-OBJ:" + encrypted);
        System.out.println("DEC-OBJ:" + decrypted);

    }
}