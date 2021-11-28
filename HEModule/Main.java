package HEModule;

class Main {

    public static void main(String[] args) {

        String entered_text = "Trial is this";

        String encr = HED.encryptHE(entered_text);
        System.out.println("ENA-OBJ:" + HED.encryptHE(entered_text));
        System.out.println("DEC-OBJ:" + HED.decryptHE(encr));

    }
}