package com.machinecoding.snakeandladder.models;

import java.util.UUID;

public class User {
    UUID id;
    String name;
    int currentPosition;

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.currentPosition = 0;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
