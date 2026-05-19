package com.example.velocity4;

public class saveData {
    levelData levelSaved;
    int bestTime;
    int lastTime;

    public saveData(levelData levelSaved, int bestTime, int lastTime) {
        this.levelSaved = levelSaved;
        this.bestTime = bestTime;
        this.lastTime = lastTime;
    }

    public saveData() {}

    public levelData getLevelSaved() {
        return levelSaved;
    }

    public int getBestTime() {
        return bestTime;
    }

    public int getLastTime() {
        return lastTime;
    }
}
