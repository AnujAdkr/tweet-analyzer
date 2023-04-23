package com.mysolution.tweetanalyzer.repository;

import com.mysolution.tweetanalyzer.model.UserDetails;
import com.mysolution.tweetanalyzer.model.UserGenreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGenreRepository extends JpaRepository<UserGenreDetails,String> {

    @Query("SELECT u.id, u.user_id, u.genre FROM user_genre_mapping u where u.user_id = :userId")
    List<UserGenreDetails> getAllGenresForUser(@Param("userId") String userId);
}
