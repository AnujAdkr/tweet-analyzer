package com.mysolution.tweetanalyzer.controller;

import com.mysolution.tweetanalyzer.listeners.TweetListener;
import com.mysolution.tweetanalyzer.model.Tweet;
import com.mysolution.tweetanalyzer.repository.TweetRepository;
import com.mysolution.tweetanalyzer.respnsemodel.TweetData;
import com.mysolution.tweetanalyzer.respnsemodel.TweetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TweetAnalyzerController {

    @Autowired
    TweetRepository tweetRepo;

    Logger logger = LoggerFactory.getLogger(TweetListener.class);

    @GetMapping("/v1/{id}/getMyPreferredTweets")
    public ResponseEntity<TweetResponse> getTweetsOfGenres(@PathVariable("id") String userId,
                                                                 @RequestParam Long pageFrom,
                                                                 @RequestParam Long pageSize,
                                                                 @RequestParam Long fromTime,
                                                                 @RequestParam Long toTime) {

        try {
            //ToDo --- Populate the genre and HashMap using database/file
            Map<String, List<String>> genreHashtagMap = new HashMap<>();
            Map<String, List<String>> userGenreMap = new HashMap<>();

            List<TweetData> responseData = new ArrayList<>();

            List<String> userDesiredhashtags = new ArrayList<>();

        /*
        User can be enrolled for multiple genres.
        Get the list of distinct hastags across multiple genres.
         */
            List<String> genreList = userGenreMap.getOrDefault(userId, new ArrayList<>());
            genreList.forEach(genre -> {
                userDesiredhashtags.addAll(genreHashtagMap.getOrDefault(genre, new ArrayList<>()));
            });
            List<String> distinctUserDesiredhasttags = userDesiredhashtags.stream().distinct().collect(Collectors.toList());

            //Calculate the offset value for database query
            long offset = pageFrom * pageSize;

            long count = tweetRepo.getCountOfTweetsWithHashTag(distinctUserDesiredhasttags, fromTime, toTime);
            List<TweetData> tweetDataList = tweetRepo.getTweetsWithHashTag(distinctUserDesiredhasttags, pageSize, offset, fromTime, toTime)
                    .stream().map(tweet -> new TweetData(tweet.getTweetId(), tweet.getHashTag(), tweet.getCreated(), tweet.getTweetText()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(new TweetResponse(tweetDataList, count), HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            List<TweetData> defaultTweetData = Arrays.asList(new TweetData(0L,"",0L,""));
            return new ResponseEntity<>(new TweetResponse(defaultTweetData,0L), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
