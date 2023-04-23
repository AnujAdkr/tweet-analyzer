package com.mysolution.tweetanalyzer.model;

public class TweetMessage {
    long tweetId;
    String hashTag;
    long created;
    String tweetText;
    //ToDo - Checking Object to JsonB mapping
    private String status;

    public TweetMessage(long tweetId, String hashTag, long created, String tweetText, String status) {
        this.tweetId = tweetId;
        this.hashTag = hashTag;
        this.created = created;
        this.tweetText = tweetText;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TweetMessage{" +
                "tweetId=" + tweetId +
                ", hashTag='" + hashTag + '\'' +
                ", created=" + created +
                ", tweetText='" + tweetText + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
