package com.example.qwez.repository.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class Game {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int gameId;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "difficulty")
    public String difficulty;

    public Game(String category, String difficulty) {
        this.category = category;
        this.difficulty = difficulty;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}