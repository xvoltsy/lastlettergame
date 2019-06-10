package com.globallogic.training.validator;

import com.globallogic.training.Consts;
import com.globallogic.training.State;

public class CommonValidator implements Validator {

    private State state;

    public CommonValidator(State state) {
        this.state = state;
    }

    @Override
    public String validate(String word, String previousWord) {
        if (word.equals(Consts.EMPTY)) {
            return word;
        }
        String validation = gameValidation(word, previousWord);
        if (validation.isEmpty()) {
            validation = dictionaryValidation(word);
        } else if (validation.isEmpty()) {
            validation = stateValidation(word);
        }
        return validation;
    }

    private String gameValidation(String word, String previousWord) {
        char lastChar = previousWord.charAt(previousWord.length() - 1);
        return word.charAt(0) != lastChar ? "The word does not begin with a letter " + lastChar : Consts.EMPTY;
    }

    private String dictionaryValidation(String word) {
        return !state.getDictionaryWords().contains(word) ? "Wrong word. It doesn't exist in the dictionary!" : Consts.EMPTY;
    }

    private String stateValidation(String word) {
        return state.getAlreadyUsedWords().contains(word) ? "This word has already been!" : Consts.EMPTY;
    }
}
