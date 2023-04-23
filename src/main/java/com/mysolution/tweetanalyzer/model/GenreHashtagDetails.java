package com.mysolution.tweetanalyzer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_genre_mapping")
public class GenreHashtagDetails {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "hashtag")
    String hashtag;

    @Column(name = "genre")
    String genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "GenreHashtagDetails{" +
                "id='" + id + '\'' +
                ", hashtag='" + hashtag + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
