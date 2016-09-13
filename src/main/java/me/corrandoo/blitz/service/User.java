package me.corrandoo.blitz.service;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    public Map<Integer, Integer> stepsViewed = new HashMap<>();

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getStepsViewed() {
        return stepsViewed;
    }

    public void setStepsViewed(Map<Integer, Integer> stepsViewed) {
        this.stepsViewed = stepsViewed;
    }
}
