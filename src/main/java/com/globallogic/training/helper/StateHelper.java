package com.globallogic.training.helper;

import com.globallogic.training.State;

import java.io.*;

public class StateHelper {

    public static void saveState(State state) {
        try {
            FileOutputStream f = new FileOutputStream(new File("state.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write state to file
            o.writeChars("");
            o.writeObject(state);

            o.flush();
            o.close();
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("File not found!");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Error initializing stream!");
        }
    }

    public static State fetchState() {
        try {
            File stateFile = new File("state.txt");
            if (stateFile.length() != 0) {
                FileInputStream fi = new FileInputStream(stateFile);
                ObjectInputStream oi = new ObjectInputStream(fi);

                // Read objects
                State state = (State) oi.readObject();

                oi.close();
                return state;
            }
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("File not found!");
        } catch (IOException | ClassNotFoundException e) {
            ConsoleHelper.writeMessage("Error initializing stream!");
        }
        return null;
    }

}
