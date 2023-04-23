create table "tweet_data" (
  id text,
  tweet_id bigint not null,
  hashtag_value text not null,
  created_dtm bigint not null,
  tweet_text text not null,
  primary key (id)
);
CREATE INDEX tweet_data_index ON tweet_data (hashtag_value);
CREATE INDEX tweet_data_created_dtm_index ON tweet_data (created_dtm);

create table "user_details" (
  user_id text,
  first_name text not null,
  last_name text,
  email text,
  user_credentials text not null
  primary key (user_id)
);

create table "user_genre_mapping"(
    id text,
    user_id text,
    genre text
    primary key (id);
);

CREATE INDEX user_genre_mapping_index ON user_genre_mapping (user_id);
COMMENT ON COLUMN user_genre_mapping.genre is 'This is a comma separated string value like sport,politics';

create table "genre_hashtag_mapping"(
    id text,
    genre text,
    hashtag text
    primary key (id);
);

CREATE INDEX genre_hashtag_mapping_index ON genre_hashtag_mapping (genre);
COMMENT ON COLUMN genre_hashtag_mapping.hashtag is 'This is a comma separated string value like cricket,football';
