package me.corrandoo.blitz.service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.corrandoo.blitz.Blitz.steps;

public class Step {
    private int modulePosition;
    private int lessonPosition;
    private int id;
    private int stepPosition;
    private int generalPosition;
    private static int totalViewers = 0;
    private static double serCoeffOfViewers;
    private static int coeffOfViewers;

    public Step(int modulePosition, int lessonPosition, int stepId, int stepPosition) {
        this.modulePosition = modulePosition;
        this.lessonPosition = lessonPosition;
        this.id = stepId;
        this.stepPosition = stepPosition;
        this.generalPosition = modulePosition * 1000000 + lessonPosition * 1000 + stepPosition;
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

    public static int getCoeffOfViewers() {
        return coeffOfViewers;
    }
    //
}
