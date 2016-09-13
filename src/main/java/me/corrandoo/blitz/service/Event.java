package me.corrandoo.blitz.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Event {
    private int userId;
    private String eventType;
    private int stepId;
    private int time;

    private Event(int userId, String eventType, int stepId, int time) {
        this.userId = userId;
        this.eventType = eventType;
        this.stepId = stepId;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getStepId() {
        return stepId;
    }

    public int getTime() {
        return time;
    }

    public static void eventsFileToList(List<Event> events, String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            String s;
            while((s = reader.readLine()) != null){
                String[] str = s.split(",");
                int userId = Integer.parseInt(str[0]);
                String actionType = str[1];
                int stepId = Integer.parseInt(str[2]);
                int time = Integer.parseInt(str[3]);

                events.add(new Event(userId, actionType, stepId, time));
            }
            events.sort((o1, o2) -> o1.getTime() - o2.getTime());
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл лога не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при обработке файла лога.");
        }
    }
}
