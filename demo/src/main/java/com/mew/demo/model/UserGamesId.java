package com.mew.demo.model;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserGamesId implements java.io.Serializable {
    private int userId;
    private int gameId;

    public UserGamesId() {}

    public UserGamesId(int userId, int gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    // Override equals and hashCode for proper comparison in composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGamesId)) return false;
        UserGamesId that = (UserGamesId) o;
        return userId == that.userId && gameId == that.gameId;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(userId);
        result = 31 * result + Integer.hashCode(gameId);
        return result;
    }
    
}
