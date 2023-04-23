create table "tweet_data" (
  id text,
  tweet_id bigint not null,
  hashtag_value text not null,
  created_dtm bigint not null,
  tweet_text text not null,
  status_data jsonb
  primary key (id)
);

create table "user_details" (
  user_id text,
  first_name text not null,
  last_name text,
  email text,
  user_credentials text not null
  primary key (table)
);