package com.globallogic.training.init;

import com.globallogic.training.State;
import com.globallogic.training.helper.ConsoleHelper;
import com.globallogic.training.helper.StateHelper;
import com.globallogic.training.users.User;
import com.globallogic.training.validator.CommonValidator;
import com.globallogic.training.validator.Validator;

import java.util.LinkedHashSet;
import java.util.List;

public class GameRunner {

    private State state;
    private Validator validator;
    private boolean isResponseOk;

    public GameRunner(State state) {
        this.state = state;
        this.validator = new CommonValidator(state);
    }

    public void run() {
        String word = "";
        String previousWord;

        List<User> usersChain = state.getUsersChain();
        LinkedHashSet<String> alreadyUsedWords = state.getAlreadyUsedWords();

        if (!alreadyUsedWords.isEmpty()) {
            previousWord = (String) alreadyUsedWords.toArray()[alreadyUsedWords.size() - 1];
            ConsoleHelper.writeMessage("Previous word was - " + previousWord + ".");
        }
        int order = 0;
        if (usersChain.stream().anyMatch(User::isRepliedLast)) {
            order = usersChain.indexOf(usersChain.stream().filter(User::isRepliedLast).findFirst().get());
        }
        order = (order == usersChain.size() - 1) ? 0 : order;
        for (; order < usersChain.size(); order++) {
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
                    word = currentUser.respond(previousWord);
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
    }

    private void saveState(String word, User currentUser, int userOrder) {
        isResponseOk = true;
        state.getAlreadyUsedWords().add(word);
        currentUser.setRepliedLast(true);
        StateHelper.saveState(state);
    }
}
