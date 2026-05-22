package com.example.velocity4;

public class saveData {
    levelData levelSaved;
    String bestTime;
    String lastTime;

    public saveData(levelData levelSaved, String bestTime, String lastTime) {
        this.levelSaved = levelSaved;
        this.bestTime = bestTime;
        this.lastTime = lastTime;
    }

    public saveData() {}

    public levelData getLevelSaved() {
        return levelSaved;
    }

    public String getBestTime() {
        return bestTime;
    }

    public String getLastTime() {
        return lastTime;
    }
}
