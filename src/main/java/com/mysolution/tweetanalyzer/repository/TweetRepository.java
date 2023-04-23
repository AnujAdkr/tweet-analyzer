package com.mysolution.tweetanalyzer.repository;

import com.mysolution.tweetanalyzer.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,String> {

    @Query("SELECT t.tweet_id, t.hashtag_value, t.created_dtm, t.tweet_text FROM tweet_details t WHERE t.created_dtm BETWEEN :fromDate AND :toDate AND t.hashtag_value" +
            "IN :inHashTagValue ORDER BY t.created_dtm DESC LIMIT :limit OFFSET :offset")
    List<Tweet> getTweetsWithHashTag(@Param("inHashTagValue") Collection<String> inHashTagValue,
                                     @Param("limit") Long limit,
                                     @Param("offset") Long offset,
                                     @Param("fromDate") Long fromDate,
                                     @Param("toDate") Long toDate);

    @Query("SELECT count (t.tweet_id) FROM tweet_details t WHERE t.created_dtm BETWEEN :fromDate AND :toDate AND t.hashtag_value" +
            "IN :inHashTagValue ORDER BY t.created_dtm DESC")
    Long getCountOfTweetsWithHashTag(@Param("inHashTagValue") Collection<String> inHashTagValue,
                                     @Param("fromDate") Long fromDate,
                                     @Param("toDate") Long toDate);

}
