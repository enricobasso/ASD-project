package it.enricobasso.asd;

import java.util.Random;

public class Generatore {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Random rand = new Random();
            System.out.printf("%.2f, ", rand.nextDouble());
        }
    }
}
