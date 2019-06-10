package com.globallogic.training.helper;

import com.globallogic.training.State;

import java.io.*;

public class StateHelper {

    public static void saveState(State state) {
        try {
            FileOutputStream f = new FileOutputStream(new File("state.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write state to file
            o.writeObject(state);

            o.flush();
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public static State fetchState() {
        try {
            FileInputStream fi = new FileInputStream(new File("state.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            State state = (State) oi.readObject();

            oi.close();
            return state;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error initializing stream");
        }
        return null;
    }

}
