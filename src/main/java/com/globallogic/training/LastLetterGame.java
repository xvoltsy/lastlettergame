package com.globallogic.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LastLetterGame {

    public static Language LANGUAGE = Language.NONE;

    public static void main(String[] args) {
        System.out.println("GAME STARTED!");
        System.out.println();
        System.out.println("Please select Language of the game: ENG or RU ?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean isLanguageCorrect = false;
            while (!isLanguageCorrect) {
                String language = br.readLine();
                if ("ENG".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.ENG;
                    isLanguageCorrect = true;
                } else if ("RU".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.RU;
                    isLanguageCorrect = true;
                } else {
                    System.out.println("You have entered incorrect language abbreviation. Please input ENG of RU in any case!");
                }
            }
            if (LANGUAGE == Language.ENG) {
                System.out.println("Thank you! Please enter first word to start play!");
            } else {
                System.out.println("Спасибо! Пожалуйста, введите первое слово, чтобы начать игру!");
            }

            String language = br.readLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
