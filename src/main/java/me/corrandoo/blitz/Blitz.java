package me.corrandoo.blitz;

import me.corrandoo.blitz.service.Event;
import me.corrandoo.blitz.service.Step;
import me.corrandoo.blitz.service.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.corrandoo.blitz.service.Step.countUsersReturned;

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
        getUsersReturned();
        countMainUsersReturned();
        steps.sort((o1, o2) -> o1.getCoeffOfViewers() - o2.getCoeffOfViewers());
        getTenSteps();
    }
    public static void getUsersList(){
        for (Event event : events) {
            if(!userMap.containsKey(event.getUserId())){
                userMap.put(event.getUserId(), users.size());
                users.add(new User(event.getUserId()));
            }
        }
    }

    public static void getUsersReturned(){
        for (Event event : events) {
            if(event.getEventType().equals("discovered")){
                steps.get(stepMap.get(event.getStepId())).getViewersMap().put(event.getUserId(), 1);
            }
            else if(users.get(userMap.get(event.getUserId())).getLastStepPos() > steps.get(stepMap.get(event.getStepId())).getGeneralPosition()){
                int count = steps.get(stepMap.get(event.getStepId())).getViewersMap().get(event.getUserId());
                steps.get(stepMap.get(event.getStepId())).getViewersMap().put(event.getUserId(), count++);
            }
        }
    }

    public static void countMainUsersReturned(){
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).countUsersReturned();
        }
    }
    private static void getTenSteps(){
        int j = 0;
        for (int i = 1; i <= steps.size(); i++) {
            Step step = steps.get(steps.size() - i);
            System.out.print(step.getStepId() + " " + step.getUsersReturned());
            j++;
            if(j == 10)
                break;
            System.out.print(",");
        }
        System.out.println();
        int x = 0;
        for (int i = 0; i < steps.size(); i++) {
            System.out.print(steps.get(i).getStepId());
            x++;
            if(x == 10) break;
            System.out.print(",");
        }
    }


}
