package com.example.velocity4;

public class saveData {
    levelData levelSaved;
    long bestTime;
    long lastTime;

    public saveData(levelData levelSaved, long bestTime, long lastTime) {
        this.levelSaved = levelSaved;
        this.bestTime = bestTime;
        this.lastTime = lastTime;
    }

    public saveData() {}

    public levelData getLevelSaved() {
        return levelSaved;
    }

    public long getBestTime() {
        return bestTime;
    }

    public long getLastTime() {
        return lastTime;
    }
}
