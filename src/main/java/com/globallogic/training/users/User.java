package com.globallogic.training.users;

public interface User {

    String respond(String previousWord);

    boolean isResponseCorrect(String word);
}
