package me.corrandoo.blitz.service;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Step {
    private int moduleId;
    private int modulePosition;
    private int lessonId;
    private int lessonPosition;
    private int stepId;
    private int stepPosition;
    private int generalPosition;

    public Step(int moduleId, int modulePosition, int lessonId, int lessonPosition, int stepId, int stepPosition) {
        this.moduleId = moduleId;
        this.modulePosition = modulePosition;
        this.lessonId = lessonId;
        this.lessonPosition = lessonPosition;
        this.stepId = stepId;
        this.stepPosition = stepPosition;
        this.generalPosition = modulePosition * 100 + lessonPosition * 10 + stepPosition;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getModulePosition() {
        return modulePosition;
    }

    public int getLessonId() {
        return lessonId;
    }

    public int getLessonPosition() {
        return lessonPosition;
    }

    public int getStepId() {
        return stepId;
    }

    public int getStepPosition() {
        return stepPosition;
    }

    public int getGeneralPosition() {
        return generalPosition;
    }

    public static void stepsFileToList(List<Step> steps, Map<Integer, Integer> stepMap, String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String s;
            reader.readLine();
            while((s = reader.readLine()) != null){
                String[] str = s.split(",");
                int moduleId = Integer.parseInt(str[1]);
                int modulePosition = Integer.parseInt(str[2]);
                int lessonId = Integer.parseInt(str[3]);
                int lessonPosition = Integer.parseInt(str[4]);
                int stepId = Integer.parseInt(str[5]);
                int stepPosition = Integer.parseInt(str[6]);
                stepMap.put(stepId, steps.size());
                steps.add(new Step(moduleId, modulePosition, lessonId, lessonPosition, stepId, stepPosition));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Данный файл структуры курса не найден.");
        }
        catch(IOException e){
            System.out.println("Ошибка при обработке файла структуры курса.");
        }
    }
}
