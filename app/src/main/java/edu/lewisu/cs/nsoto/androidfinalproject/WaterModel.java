package edu.lewisu.cs.nsoto.androidfinalproject;



public class WaterModel {

    private float currentWater;
    private float maxWater;

    public WaterModel(float currentWater, float maxWater)
    {
        this.currentWater = currentWater;
        this.maxWater = maxWater;
    }

    public float getCurrentWater() {
        return currentWater;
    }

    public void setCurrentWater(float currentWater) {
        this.currentWater = currentWater;
    }

    public float getMaxWater() {
        return maxWater;
    }

    public void setMaxWater(float maxWater) {
        this.maxWater = maxWater;
    }

    public void addWater(float waterAmt) {
        currentWater = currentWater + waterAmt;
    }

    public void clearWater() {
        currentWater = 0;
        maxWater = 64;
    }



}
