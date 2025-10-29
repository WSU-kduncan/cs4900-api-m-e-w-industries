package com.mew.demo.model;
import jakarta.persistence.Embeddable;

@Embeddable
public class MatchedUserId implements java.io.Serializable {
    private int userId1;
    private int userId2;

    public MatchedUserId() {}

    public MatchedUserId(int userId1, int userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    // Getters and Setters
    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    // Override equals and hashCode for proper comparison in composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchedUserId)) return false;
        MatchedUserId that = (MatchedUserId) o;
        return userId1 == that.userId1 && userId2 == that.userId2;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(userId1);
        result = 31 * result + Integer.hashCode(userId2);
        return result;
    }
}