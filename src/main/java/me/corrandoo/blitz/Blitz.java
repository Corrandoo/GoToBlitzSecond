package me.corrandoo.blitz;

import com.sun.org.apache.xpath.internal.SourceTree;
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
        getCoeff();
        steps.sort((o1 ,o2) -> o2.getCoeffOfReturned() - o1.getCoeffOfReturned());
        print();
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
            if(user.getStepsDiscovered().contains(event.getStepId()) && user.getLastStepPos() - step.getGeneralPosition() > 0 && event.getEventType().equals("viewed")){
                if(!user.getStepsReturned().contains(event.getStepId()))
                    user.getStepsReturned().add(event.getStepId());
            }
            user.setLastStepPos(step.getGeneralPosition());
            users.set(userMap.get(event.getUserId()), user);
        }

        for (Event event : events) {
            Step step = steps.get(stepMap.get(event.getStepId()));
            User user = users.get(userMap.get(event.getUserId()));
            if(user.getStepsDiscovered().contains(step.getId())){
                if(!step.getDiscovered().contains(user.getId()))
                    step.getDiscovered().add(user.getId());
            }
            if(user.getStepsReturned().contains(step.getId())){
                if(!step.getReturned().contains(user.getId()))
                    step.getReturned().add(user.getId());
            }
            steps.set(stepMap.get(event.getStepId()), step);
        }
    }
    public static void getCoeff(){
        for (Step step : steps) {
            step.countCoeffOfReturned();
            steps.set(stepMap.get(step.getId()), step);
        }
    }
    public static void print(){
        int count = 0;
        for (Step step : steps) {
            System.out.print(step.getId());
            count++;
            if(count >= 10) break;
            System.out.print(",");
        }
    }



}
