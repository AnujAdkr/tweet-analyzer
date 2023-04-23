package com.mysolution.tweetanalyzer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_genre_mapping")
public class UserGenreDetails {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "genre")
    String genre;

    public UserGenreDetails(String id, String userId, String genre) {
        this.id = id;
        this.userId = userId;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "UserGenreDetails{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
