package com.mysolution.tweetanalyzer.repository;

import com.mysolution.tweetanalyzer.model.Tweet;
import com.mysolution.tweetanalyzer.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetails,String> {

}
