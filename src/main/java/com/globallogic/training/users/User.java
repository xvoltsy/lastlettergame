package com.globallogic.training.users;

import java.io.Serializable;

public interface User extends Serializable {

    boolean isRepliedLast();

    void setRepliedLast(boolean repliedLast);

    String respond(String previousWord);

    boolean isResponseCorrect(String word);
}
