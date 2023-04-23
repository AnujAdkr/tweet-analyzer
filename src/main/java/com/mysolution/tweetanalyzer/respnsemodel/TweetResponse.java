package com.mysolution.tweetanalyzer.respnsemodel;

import java.util.ArrayList;
import java.util.List;

public class TweetResponse {
    List<TweetData> data;
    long count;

    public TweetResponse(List<TweetData> data, long count) {
        this.data = data;
        this.count = count;
    }

    public List<TweetData> getData() {
        return data;
    }

    public void setData(List<TweetData> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TweetResponse{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }
}
