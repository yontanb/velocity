package com.example.velocity4;

public class saveData {
    levelData levelSaved;
    long bestTime;
    long lastTime;
    /**
     * creates a new saveData
     * @param levelSaved the data of the level saved
     * @param bestTime the best time the player has finished the level
     * @param lastTime the last time the player has finished the level
     */
    public saveData(levelData levelSaved, long bestTime, long lastTime) {
        this.levelSaved = levelSaved;
        this.bestTime = bestTime;
        this.lastTime = lastTime;
    }
    /**
     * empty constructor for firebase
     */
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
