package com.globallogic.training.users;

public class UserImpl implements User {


    private String word;
    private char letter;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

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
