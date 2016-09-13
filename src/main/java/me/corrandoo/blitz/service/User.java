package me.corrandoo.blitz.service;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private int lastStepPos;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastStepPos() {
        return lastStepPos;
    }

    public void setLastStepPos(int lastStepPos) {
        this.lastStepPos = lastStepPos;
    }
}
