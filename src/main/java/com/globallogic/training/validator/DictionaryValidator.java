package com.globallogic.training.validator;

import com.globallogic.training.State;

public class DictionaryValidator {

    private State state;

    public DictionaryValidator(State state) {
        this.state = state;
    }

    public boolean validate(String word) {
        return state.getDictionaryWords().contains(word);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
