package com.globallogic.training;

import com.globallogic.training.users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    private Language language;

    private LinkedHashSet<String> alreadyUsedWords = new LinkedHashSet<>();
    private Set<String> dictionaryWords = new HashSet<String>();
    private List<User> usersChain = new ArrayList<>();
    private User lastUser;

    public void loadWords(Language language) throws IOException {
        String fileName = language == Language.EN ? "en.txt" : "ru.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            dictionaryWords.add(line);
        }
    }

    public int findLastRepliedUserOrderInChain() {
        return usersChain.indexOf(usersChain.stream().filter(User::isRepliedLast).findFirst().get());
    }

    public void setLastUser(int order) {
        if (order == 0){
            lastUser = usersChain.get(usersChain.size() - 1);
        } else {
            lastUser = usersChain.get(order - 1);
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LinkedHashSet<String> getAlreadyUsedWords() {
        return alreadyUsedWords;
    }

    public void setAlreadyUsedWords(LinkedHashSet<String> alreadyUsedWords) {
        this.alreadyUsedWords = alreadyUsedWords;
    }

    public Set<String> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(Set<String> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    public List<User> getUsersChain() {
        return usersChain;
    }

    public void setUsersChain(List<User> usersChain) {
        this.usersChain = usersChain;
    }

    public User getLastUser() {
        return lastUser;
    }

    public void setLastUser(User lastUser) {
        this.lastUser = lastUser;
    }
}
