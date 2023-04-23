package com.mysolution.tweetanalyzer.listeners;

import com.mysolution.tweetanalyzer.model.Tweet;
import com.mysolution.tweetanalyzer.model.TweetMessage;
import com.mysolution.tweetanalyzer.repository.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TweetListener {

    @Value("${spring.kafka.consumer.topic}")
    private String tweetTopic;

    @Autowired
    TweetRepository tweetRepo;

    Logger logger = LoggerFactory.getLogger(TweetListener.class);

    //ToDo -- Remove the hardcoded topic name and group and use it from properties.
    @KafkaListener(topics = "tweet-topic", groupId = "tweetanalyzer-group", containerFactory = "tweetKafkaListenerContainerFactory")
    public void listenTweets(TweetMessage message) {
        logger.info("Received TweetMessage with Id : " + message.getTweetId());
        logger.debug("tweet data received =  {}",message.toString());

        //1. Persist the Tweet in database.
        long createdTime = System.currentTimeMillis();
        String datastoreId = message.getTweetId() + "-" + message.getHashTag() + "-" + createdTime;
        tweetRepo.saveAndFlush(new Tweet(datastoreId,
                message.getTweetId(),
                message.getHashTag(),
                createdTime,
                message.getTweetText()));

        //ToDo --- Send ACK
    }
}
