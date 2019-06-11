package com.globallogic.training;

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
import java.util.*;

public class LastLetterGame {

    private static Language LANGUAGE = Language.NONE;

    private static boolean isResponseOk;
    private static State state;

    public static void main(String[] args) {
        state = StateHelper.fetchState();
        if (state == null) {
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


                state = new State();
                state.setLanguage(LANGUAGE);
                state.loadWords(LANGUAGE);

                Validator validator = new CommonValidator(state);


                User user = new UserImpl();
                User bot = new Bot(state);
                List<User> usersChain = state.getUsersChain();
                usersChain.add(user);
                usersChain.add(bot);

                String word = "";
                String previousWord;
                LinkedHashSet<String> alreadyUsedWords = state.getAlreadyUsedWords();


                for (int order = 0; order < usersChain.size(); order++) {
                    isResponseOk = false;
                    User currentUser = usersChain.get(order);
                    while (!isResponseOk) {
                        if (alreadyUsedWords.isEmpty()) {
                            word = currentUser.respond("");
                            if (currentUser.isResponseCorrect(word)) {
                                saveState(word, currentUser, order);
                            } else {
                                ConsoleHelper.writeMessage("The word is not correct. Please try again!");
                            }
                        } else {
                            previousWord = (String) alreadyUsedWords.toArray()[alreadyUsedWords.size() - 1];
                            word = currentUser.respond(word);
                            if (!currentUser.isResponseCorrect(word)) {
                                ConsoleHelper.writeMessage("The word is not correct. Please try again!");
                                continue;
                            }

                            String validationMessage = validator.validate(word, previousWord);
                            if (!validationMessage.isEmpty()) {
                                ConsoleHelper.writeMessage(validationMessage);
                            } else {
                                if (order == 0){
                                    usersChain.get(usersChain.size() - 1).setRepliedLast(false);
                                } else {
                                    usersChain.get(order - 1).setRepliedLast(false);
                                }
                                saveState(word, currentUser, order);
                            }
                        }
                    }
                    // Start next cycle
                    if (order == usersChain.size() - 1) {
                        order = -1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("GAME RESTORED!");

        }
    }

    private static void saveState(String word, User currentUser, int userOrder) {
        isResponseOk = true;
        state.getAlreadyUsedWords().add(word);
        currentUser.setRepliedLast(true);
        StateHelper.saveState(state);
    }
}
