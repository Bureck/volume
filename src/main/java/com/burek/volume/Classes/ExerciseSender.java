package com.burek.volume.Classes;


public class ExerciseSender {

    private long id;

    private String name;

    private long PlanId;

    private float weight;

    private int reps;

    private int fails;

    public ExerciseSender() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlanId() {
        return PlanId;
    }

    public void setPlanId(long planId) {
        PlanId = planId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }
}
