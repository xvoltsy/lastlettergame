package com.globallogic.training.users;

import com.globallogic.training.helper.ConsoleHelper;

import java.io.IOException;

public class UserImpl implements User {

    private boolean isRepliedLast;

    @Override
    public String respond(String previousWord) {
        return ConsoleHelper.readMessage();
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
