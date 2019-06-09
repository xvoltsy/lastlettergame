package com.globallogic.training.validator;

public class GameValidator {

    public boolean isWordStartFromCorrectLetter(String word, char inputLetter) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        return word.charAt(0) == inputLetter;
    }
}
