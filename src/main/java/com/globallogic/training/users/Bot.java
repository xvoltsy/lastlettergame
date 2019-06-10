package com.globallogic.training.users;

public class Bot implements User {

    @Override
    public String respond(String previousWord) {
        return null;
    }

    @Override
    public boolean isResponseCorrect(String word) {
        return ((word != null) && (!word.equals(""))
                && (word.chars().allMatch(Character::isLetter)));
    }
}
