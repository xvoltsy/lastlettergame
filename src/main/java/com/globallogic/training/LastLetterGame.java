package com.globallogic.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LastLetterGame {

    public static Language LANGUAGE = Language.NONE;

    public static void main(String[] args) {
        System.out.println("GAME STARTED!");
        System.out.println();
        System.out.println("Please select Language of the game: EN or RU ?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean isLanguageCorrect = false;
            while (!isLanguageCorrect) {
                String language = br.readLine();
                if ("EN".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.EN;
                    isLanguageCorrect = true;
                } else if ("RU".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.RU;
                    isLanguageCorrect = true;
                } else {
                    System.out.println("You have entered incorrect language abbreviation. Please input EN of RU in any case!");
                }
            }
            if (LANGUAGE == Language.EN) {
                System.out.println("Thank you! Please enter first word to start play!");
            } else {
                System.out.println("Спасибо! Пожалуйста, введите первое слово, чтобы начать игру!");
            }

            State state = new State();
            state.loadWords(LANGUAGE);

            String word = br.readLine();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
