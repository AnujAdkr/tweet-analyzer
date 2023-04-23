package com.mysolution.tweetanalyzer.controller;

import com.mysolution.tweetanalyzer.listeners.TweetListener;
import com.mysolution.tweetanalyzer.model.Tweet;
import com.mysolution.tweetanalyzer.repository.TweetRepository;
import com.mysolution.tweetanalyzer.respnsemodel.TweetData;
import com.mysolution.tweetanalyzer.respnsemodel.TweetResponse;
import com.mysolution.tweetanalyzer.service.TweetAnalyzerService;
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
    TweetAnalyzerService tweetService;

    Logger logger = LoggerFactory.getLogger(TweetListener.class);

    @GetMapping("/v1/{id}/getMyPreferredTweets")
    public ResponseEntity<TweetResponse> getTweetsOfGenres(@PathVariable("id") String userId,
                                                                 @RequestParam Long pageFrom,
                                                                 @RequestParam Long pageSize,
                                                                 @RequestParam Long fromTime,
                                                                 @RequestParam Long toTime) {

        try {
            TweetResponse resp = tweetService.getTweetsOfGenres(userId, pageFrom, pageSize, fromTime, toTime);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            List<TweetData> defaultTweetData = Arrays.asList(new TweetData(0L,"",0L,""));
            return new ResponseEntity<>(new TweetResponse(defaultTweetData,0L), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
