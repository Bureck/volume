package com.burek.volume.Requests;

public class PlanCreationRequest {

    private float round;

    private String units;

    private float deadliftWeight;
    private int deadliftReps;

    private float squatWeight;
    private int squatReps;

    private float benchWeight;
    private int benchReps;

    private float ohpWeight;
    private int ohpReps;

    private float rowWeight;
    private int rowReps;


    public void setRound(float round) {
        this.round = round;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setDeadliftWeight(float deadliftWeight) {
        this.deadliftWeight = deadliftWeight;
    }

    public void setDeadliftReps(int deadliftReps) {
        this.deadliftReps = deadliftReps;
    }

    public void setSquatWeight(float squatWeight) {
        this.squatWeight = squatWeight;
    }

    public void setSquatReps(int squatReps) {
        this.squatReps = squatReps;
    }

    public void setBenchWeight(float benchWeight) {
        this.benchWeight = benchWeight;
    }

    public void setBenchReps(int benchReps) {
        this.benchReps = benchReps;
    }

    public void setOhpWeight(float ohpWeight) {
        this.ohpWeight = ohpWeight;
    }

    public void setOhpReps(int ohpReps) {
        this.ohpReps = ohpReps;
    }

    public void setRowWeight(float rowWeight) {
        this.rowWeight = rowWeight;
    }

    public void setRowReps(int rowReps) {
        this.rowReps = rowReps;
    }

    public float getRound() {
        return round;
    }

    public String getUnits() {
        return units;
    }

    public float getDeadliftWeight() {
        return deadliftWeight;
    }

    public int getDeadliftReps() {
        return deadliftReps;
    }

    public float getSquatWeight() {
        return squatWeight;
    }

    public int getSquatReps() {
        return squatReps;
    }

    public float getBenchWeight() {
        return benchWeight;
    }

    public int getBenchReps() {
        return benchReps;
    }

    public float getOhpWeight() {
        return ohpWeight;
    }

    public int getOhpReps() {
        return ohpReps;
    }

    public float getRowWeight() {
        return rowWeight;
    }

    public int getRowReps() {
        return rowReps;
    }
}
