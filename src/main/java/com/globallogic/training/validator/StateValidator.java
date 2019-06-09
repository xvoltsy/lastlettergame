package com.globallogic.training.validator;

import com.globallogic.training.State;

public class StateValidator {

    private State state;

    public StateValidator(State state) {
        this.state = state;
    }

    public boolean validate(String word) {
        return !state.getAlreadyUsedWords().contains(word);
    }
}
