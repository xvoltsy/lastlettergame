package com.globallogic.training;

import com.globallogic.training.helper.ConsoleHelper;
import com.globallogic.training.helper.StateHelper;
import com.globallogic.training.init.GameInitializer;
import com.globallogic.training.init.GameRunner;

import java.io.IOException;

public class LastLetterGame {

    public static void main(String[] args) throws IOException {
        ConsoleHelper.writeMessage("LAST LETTER GAME!");

        State state = StateHelper.fetchState();
        if (state == null) {
            ConsoleHelper.writeMessage("GAME STARTED!");
            GameInitializer gameInitializer = new GameInitializer();
            state = gameInitializer.init();
            gameInitializer.setLanguage();
            gameInitializer.loadWords();

            GameRunner runner = new GameRunner(state);
            runner.run();
        } else {
            ConsoleHelper.writeMessage("GAME RESTORED!");
            GameRunner runner = new GameRunner(state);
            runner.run();
        }
    }
}
