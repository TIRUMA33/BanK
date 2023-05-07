package es.uma.taw.bank;

import java.util.Random;

public class DataGenerator {
    public static String randomIbanGenerator() {
        Random rnd = new Random();
        StringBuilder iban = new StringBuilder("ES");

        for (int i = 0; i < 22; i++) {
            int n = rnd.nextInt(10);
            iban.append(n);
        }

        return iban.toString();
    }

    public static String randomSwiftGenerator() {
        Random rnd = new Random();
        int A = 65;
        int Z = 90;
        int limite = 11;

        return rnd.ints(A, Z + 1).limit(limite).collect(StringBuilder::new, StringBuilder::appendCodePoint,
                StringBuilder::append).toString();
    }
}
