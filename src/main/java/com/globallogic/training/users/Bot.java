package com.globallogic.training.users;

import com.globallogic.training.State;
import com.globallogic.training.helper.ConsoleHelper;

import java.util.Set;

public class Bot implements User {

    private State state;
    private boolean isRepliedLast;

    public Bot(State state) {
        this.state = state;
    }

    @Override
    public String respond(String previousWord) {
        char lastLetter = previousWord.charAt(previousWord.length() - 1);
        Set<String> alreadyUsedWords = state.getAlreadyUsedWords();
        for (String dictionaryWord : state.getDictionaryWords()) {
            if (dictionaryWord.charAt(0) == lastLetter && !alreadyUsedWords.contains(dictionaryWord)) {
                ConsoleHelper.writeMessage(dictionaryWord);
                return dictionaryWord;
            }
        }
        return "";
    }

    @Override
    public boolean isRepliedLast() {
        return isRepliedLast;
    }

    @Override
    public boolean isResponseCorrect(String word) {
        return ((word != null) && (!word.equals(""))
                && (word.chars().allMatch(Character::isLetter)));
    }

    public void setRepliedLast(boolean repliedLast) {
        isRepliedLast = repliedLast;
    }
}
