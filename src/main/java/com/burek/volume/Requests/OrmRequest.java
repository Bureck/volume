package com.burek.volume.Requests;

public class OrmRequest {

    private float weight;

    private String units;

    private int reps;

    public OrmRequest(){

    }

    public float getWeight() {
        return weight;
    }

    public String getUnits() {
        return units;
    }

    public int getReps() {
        return reps;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}




