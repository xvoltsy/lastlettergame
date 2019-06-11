package com.globallogic.training.init;

import com.globallogic.training.Language;
import com.globallogic.training.State;
import com.globallogic.training.helper.ConsoleHelper;
import com.globallogic.training.users.Bot;
import com.globallogic.training.users.User;
import com.globallogic.training.users.UserImpl;
import com.globallogic.training.validator.CommonValidator;
import com.globallogic.training.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GameInitializer {

    private State state;
    private static Language LANGUAGE = Language.NONE;

    public GameInitializer(State state) {
        this.state = state;
    }

    public void start() throws IOException {
        ConsoleHelper.writeMessage("GAME STARTED!");
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Please select Language of the game: EN or RU ?");

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
            state.setLanguage(LANGUAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void initialize() throws IOException {
        state = new State();
        state.loadWords(LANGUAGE);

        User user = new UserImpl();
        User bot = new Bot(state);
        List<User> usersChain = state.getUsersChain();
        usersChain.add(user);
        usersChain.add(bot);
    }
}
