# Blogs Schema
# Users Schema

# --- !Ups
CREATE SEQUENCE blog_id_seq;
CREATE TABLE Blogs (
  id integer NOT NULL DEFAULT nextval('blog_id_seq'),
  title varchar(255),
  content varchar(max)
);

CREATE SEQUENCE users_id_seq;
CREATE TABLE Users {
  id integer NOT NULL DEFAULT nextval('users_id_seq'),
  username varchar(20)
}

# --- !Downs
DROP TABLE Blogs;
DROP SEQUENCE blog_id_seq;

DROP TABLE Blogs;
DROP SEQUENCE users_id_seq;

