package com.mysolution.tweetanalyzer.service;

import com.mysolution.tweetanalyzer.model.GenreHashtagDetails;
import com.mysolution.tweetanalyzer.model.UserGenreDetails;
import com.mysolution.tweetanalyzer.repository.GenreHashtagRepository;
import com.mysolution.tweetanalyzer.repository.TweetRepository;
import com.mysolution.tweetanalyzer.repository.UserGenreRepository;
import com.mysolution.tweetanalyzer.respnsemodel.TweetData;
import com.mysolution.tweetanalyzer.respnsemodel.TweetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TweetAnalyzerService {

    @Autowired
    TweetRepository tweetRepo;

    @Autowired
    UserGenreRepository userGenreRepo;

    @Autowired
    GenreHashtagRepository genreHashtagRepository;

    Logger logger = LoggerFactory.getLogger(TweetAnalyzerService.class);

    public TweetResponse getTweetsOfGenres(String userId, Long pageFrom, Long pageSize, Long fromTime, Long toTime) {

        Map<String, List<String>> genreHashtagMap = new HashMap<>();
        Map<String, List<String>> userGenreMap = new HashMap<>();

        List<String> genres = new ArrayList<>();

        Optional<UserGenreDetails> maybeUserGenre = userGenreRepo.getAllGenresForUser(userId).stream().findFirst();
        if(maybeUserGenre.isPresent()){
            genres = Arrays.stream(maybeUserGenre.get().getGenre().split(",")).toList();
            userGenreMap.put(userId, genres);
        }else {
            userGenreMap.put(userId, new ArrayList<>());
        }

        genres.forEach(data -> {
            Optional<GenreHashtagDetails> mayBeHashtag = genreHashtagRepository.getAllHashtagsForGenre(data).stream().findFirst();
            if(mayBeHashtag.isPresent()){
                genreHashtagMap.put(data, Arrays.stream(mayBeHashtag.get().getHashtag().split(",")).toList());
            }else {
                genreHashtagMap.put(data, new ArrayList<>());
            }
        });


        List<String> userDesiredhashtags = new ArrayList<>();

        /*
        User can be enrolled for multiple genres.
        One hastag can be present in multiple genres.
        the below is used to get the list of distinct hastags across all genres for the user.
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

        return (new TweetResponse(tweetDataList, count));
    }
}
