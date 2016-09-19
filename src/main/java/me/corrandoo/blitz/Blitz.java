package me.corrandoo.blitz;

import me.corrandoo.blitz.service.Event;
import me.corrandoo.blitz.service.Step;
import me.corrandoo.blitz.service.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Blitz {
    public static List<Event> events = new ArrayList<Event>();
    public static List<Step> steps = new ArrayList<Step>();
    public static List<User> users = new ArrayList<>();
    public static Map<Integer, Integer> userMap = new HashMap<>();
    public static Map<Integer, Integer> stepMap = new HashMap<>();

    public static void main(String[] args) {
        Event.eventsFileToList(events, "src/main/resources/course-217-events.csv");
        Step.stepsFileToList(steps, stepMap, "src/main/resources/course-217-structure.csv");
        getUsersList();
        steps.sort((o1, o2) -> o1.getCoeffOfViewers() - o2.getCoeffOfViewers());
    }
    public static void getUsersList(){
        for (Event event : events) {
            if(!userMap.containsKey(event.getUserId())){
                userMap.put(event.getUserId(), users.size());
                users.add(new User(event.getUserId()));
            }
        }
    }



}
