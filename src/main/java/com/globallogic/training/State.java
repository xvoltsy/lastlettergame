package com.globallogic.training;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class State {

    private Set<String> alreadyUsedWords = new HashSet<String>();
    private Set<String> dictionaryWords = new HashSet<String>();

    public void loadWords(Language language) throws IOException {
        String fileName = language == Language.EN ? "en.txt" : "ru.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            dictionaryWords.add(line);
        }
    }

    public Set<String> getAlreadyUsedWords() {
        return alreadyUsedWords;
    }

    public void setAlreadyUsedWords(Set<String> alreadyUsedWords) {
        this.alreadyUsedWords = alreadyUsedWords;
    }

    public Set<String> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(Set<String> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }
}
