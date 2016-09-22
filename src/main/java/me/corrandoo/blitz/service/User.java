package me.corrandoo.blitz.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {
    private int id;
    private int lastStepPos = 0;
    private Set<Integer> stepsDiscovered = new HashSet<>(); // Хранит id степов, которые пользователь посмотрел
    private Set<Integer> stepsReturned = new HashSet<>(); // Хранит id степов, к которым пользователь вернулся

    public User(int id) {
        this.id = id;
    }


    public int getLastStepPos() {
        return lastStepPos;
    }

    public void setLastStepPos(int lastStepPos) {
        this.lastStepPos = lastStepPos;
    }

    public Set<Integer> getStepsDiscovered() {
        return stepsDiscovered;
    }

    public Set<Integer> getStepsReturned() {
        return stepsReturned;
    }
}
