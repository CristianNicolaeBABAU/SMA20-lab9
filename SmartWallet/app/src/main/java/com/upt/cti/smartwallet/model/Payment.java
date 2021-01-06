package com.upt.cti.smartwallet.model;

public class Payment {

    public String timestamp;
    private double cost;
    private String name;
    private String type;

    public Payment(double cost, String name, String type) {
        this.cost = cost;
        this.name = name;
        this.type = type;
    }

    public void setTimestamp(String t) {
        this.timestamp = t;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }
}
