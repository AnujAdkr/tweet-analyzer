package com.mysolution.tweetanalyzer.respnsemodel;

public class TweetData {
    long tweetId;
    String hashTag;
    long created;
    String tweetText;

    public TweetData(long tweetId, String hashTag, long created, String tweetText) {
        this.tweetId = tweetId;
        this.hashTag = hashTag;
        this.created = created;
        this.tweetText = tweetText;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    @Override
    public String toString() {
        return "TweetMessage{" +
                "tweetId=" + tweetId +
                ", hashTag='" + hashTag + '\'' +
                ", created=" + created +
                ", tweetText='" + tweetText + '\'' +
                '}';
    }
}
