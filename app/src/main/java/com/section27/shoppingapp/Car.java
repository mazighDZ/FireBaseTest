package com.section27.shoppingapp;

public class Car {
private  String engine;
private  String bodyType;

private  String doors;
private  String mileage;

    public Car() {
    }

    public Car(String engine, String bodyType, String doors, String mileage) {
        this.engine = engine;
        this.bodyType = bodyType;
        this.doors = doors;
        this.mileage = mileage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
