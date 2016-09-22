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
    public static Map<Integer, Integer> userMap = new HashMap<>(); // Хранит id пользователя и ссылку на него в users
    public static Map<Integer, Integer> stepMap = new HashMap<>(); // Хранит id степа и ссылку на него в steps

    public static void main(String[] args) {
        Event.eventsFileToList(events, "src/main/resources/course-217-events.csv");
        Step.stepsFileToList(steps, stepMap, "src/main/resources/course-217-structure.csv");
        getUsersList();
        processingReturnedSteps();
    }
    public static void getUsersList(){
        for (Event event : events) {
            if(!userMap.containsKey(event.getUserId())){
                userMap.put(event.getUserId(), users.size());
                users.add(new User(event.getUserId()));
            }
        }
    }
    public static void processingReturnedSteps(){
        for (Event event : events) {
            User user = users.get(userMap.get(event.getUserId()));
            Step step =  steps.get(stepMap.get(event.getStepId()));
            if(!user.getStepsDiscovered().contains(event.getStepId())){
                user.getStepsDiscovered().add(event.getStepId());
            }
            if(user.getStepsDiscovered().contains(event.getStepId()) && user.getLastStepPos() - step.getGeneralPosition() > 0){
                if(!user.getStepsReturned().contains(event.getStepId()))
                    user.getStepsReturned().add(event.getStepId());
            }
            users.set(userMap.get(event.getUserId()), user);
        }
    }



}
