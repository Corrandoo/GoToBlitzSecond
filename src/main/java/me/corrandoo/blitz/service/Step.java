package me.corrandoo.blitz.service;

public class Step {
    private int moduleId;
    private int modulePosition;
    private int lessonId;
    private int lessonPosition;
    private int stepId;
    private int stepPosition;

    public Step(int moduleId, int modulePosition, int lessonId, int lessonPosition, int stepId, int stepPosition) {
        this.moduleId = moduleId;
        this.modulePosition = modulePosition;
        this.lessonId = lessonId;
        this.lessonPosition = lessonPosition;
        this.stepId = stepId;
        this.stepPosition = stepPosition;
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
}
