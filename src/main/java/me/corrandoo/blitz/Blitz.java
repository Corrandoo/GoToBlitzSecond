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
            if(!steps.get(stepMap.get(event.getStepId())).getViewersMap().containsKey(event.getUserId()) || (steps.get(stepMap.get(event.getStepId())).getGeneralPosition() - users.get(userMap.get(event.getUserId())).getLastStepPos() > 0)){
                steps.get(stepMap.get(event.getStepId())).getViewersMap().put(event.getUserId(), 1);
            }
            else if((steps.get(stepMap.get(event.getStepId())).getViewersMap().containsKey(event.getUserId())) && (steps.get(stepMap.get(event.getStepId())).getGeneralPosition() - users.get(userMap.get(event.getUserId())).getLastStepPos() < 0)){
                int count = steps.get(stepMap.get(event.getStepId())).getViewersMap().get(event.getUserId()) + 1;
                steps.get(stepMap.get(event.getStepId())).getViewersMap().put(event.getUserId(), count);
            }
        }
    }

    public static void countMainUsersReturned(){
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).countUsersReturned();
        }
        steps.sort((o1, o2) -> o1.getUsersReturned() - o2.getUsersReturned());
    }
    private static void getTenSteps(){
        int j = 0;
        for (int i = 0; i < steps.size(); i++) {
            if(steps.get(i).getUsersReturned() > 1){
                System.out.print(steps.get(i).getStepId() + " " + steps.get(i).getUsersReturned());
                j++;
                if(j == 10)
                    break;
                System.out.print(",");
            }

        }
    }


}
