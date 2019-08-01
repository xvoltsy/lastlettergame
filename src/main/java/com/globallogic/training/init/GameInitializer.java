package com.globallogic.training.init;

import com.globallogic.training.Language;
import com.globallogic.training.State;
import com.globallogic.training.helper.ConsoleHelper;
import com.globallogic.training.helper.StateHelper;
import com.globallogic.training.users.Bot;
import com.globallogic.training.users.User;
import com.globallogic.training.users.UserImpl;
import com.globallogic.training.validator.CommonValidator;
import com.globallogic.training.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.List;

public class GameInitializer {

    private State state;
    private static Language LANGUAGE = Language.NONE;

    public void setLanguage() throws IOException {
        ConsoleHelper.writeMessage("Please select Language of the game: EN or RU ?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (LANGUAGE == Language.NONE) {
                String language = br.readLine();
                if ("EN".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.EN;
                } else if ("RU".equalsIgnoreCase(language)) {
                    LANGUAGE = Language.RU;
                } else {
                    ConsoleHelper.writeMessage("You have entered incorrect language abbreviation. Please input EN of RU in any case!");
                }
            }
            if (LANGUAGE == Language.EN) {
                ConsoleHelper.writeMessage("Thank you! Please enter first word to start play!");
            } else {
                ConsoleHelper.writeMessage("Спасибо! Пожалуйста, введите первое слово, чтобы начать игру!");
            }
            state.setLanguage(LANGUAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public State init() {
        this.state = new State();

        User user = new UserImpl();
        User bot = new Bot(state);
        List<User> usersChain = state.getUsersChain();
        usersChain.add(user);
        usersChain.add(bot);

        return state;
    }

    public void loadWords() throws IOException {
        this.state.loadWords(LANGUAGE);
    }
}
