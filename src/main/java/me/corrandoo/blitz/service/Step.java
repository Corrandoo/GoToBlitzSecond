package me.corrandoo.blitz.service;

import java.io.*;
import java.util.*;

import static me.corrandoo.blitz.Blitz.steps;

public class Step {
    private int modulePosition;
    private int lessonPosition;
    private int id;
    private int stepPosition;
    private int generalPosition;
    private static int coeffOfReturned;
    private static Set<Integer> discovered = new HashSet<>();
    private static Set<Integer> returned = new HashSet<>();

    public Step(int modulePosition, int lessonPosition, int stepId, int stepPosition) {
        this.modulePosition = modulePosition;
        this.lessonPosition = lessonPosition;
        this.id = stepId;
        this.stepPosition = stepPosition;
        this.generalPosition = modulePosition * 1000000 + lessonPosition * 1000 + stepPosition;
    }

    public int getId() {
        return id;
    }

    public static int getCoeffOfReturned() {
        return coeffOfReturned;
    }

    public int getModulePosition() {
        return modulePosition;
    }

    public int getLessonPosition() {
        return lessonPosition;
    }


    public int getStepPosition() {
        return stepPosition;
    }

    public int getGeneralPosition() {
        return generalPosition;
    }

    public Set<Integer> getDiscovered() {
        return discovered;
    }

    public Set<Integer> getReturned() {
        return returned;
    }

    public static void stepsFileToList(List<Step> steps, Map<Integer, Integer> stepMap, String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String s;
            reader.readLine();
            while((s = reader.readLine()) != null){
                String[] str = s.split(",");
                int modulePosition = Integer.parseInt(str[2]);
                int lessonPosition = Integer.parseInt(str[4]);
                int stepId = Integer.parseInt(str[5]);
                int stepPosition = Integer.parseInt(str[6]);
                stepMap.put(stepId, steps.size());
                steps.add(new Step(modulePosition, lessonPosition, stepId, stepPosition));
            }
            steps.sort((o1, o2) -> o1.getGeneralPosition() - o2.getGeneralPosition());
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл структуры курса не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при обработке файла структуры курса.");
        }
    }
    public static void countCoeffOfReturned(){
        double count = returned.size() * 1.0 / discovered.size() * 1.0;
        coeffOfReturned = (int)(count * 100000.0);
    }

    //
}
