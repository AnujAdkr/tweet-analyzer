package com.mysolution.tweetanalyzer.repository;

import com.mysolution.tweetanalyzer.model.GenreHashtagDetails;
import com.mysolution.tweetanalyzer.model.UserGenreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreHashtagRepository extends JpaRepository<GenreHashtagDetails,String> {

    @Query("SELECT u.id, u.hashtag, u.genre FROM genre_hashtag_mapping u where u.genre= :genre")
    List<GenreHashtagDetails> getAllHashtagsForGenre(@Param("genre") String genre);
}
