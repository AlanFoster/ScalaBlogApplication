# Blogs Schema
# Comments Schema
# Users Schema

# --- !Ups
CREATE SEQUENCE blog_id_seq;
CREATE TABLE Blogs (
  id integer NOT NULL DEFAULT nextval('blog_id_seq'),
  user_id integer NOT NULL,
  title varchar(255),
  content varchar(max)
);

CREATE SEQUENCE users_id_seq;
CREATE TABLE Users (
  id integer NOT NULL DEFAULT nextval('users_id_seq'),
  username varchar(20)
);

CREATE SEQUENCE comments_id_Seq;
CREATE TABLE Comments (
  id integer NOT NULL DEFAULT nextval('comments_id_Seq'),
  blogId integer NOT NULL,
  userId integer NOT NULL,

  username varchar(20),
  FOREIGN KEY (blogId) REFERENCES Blogs(id)
);

-- Data population
INSERT INTO users (username) values ('Admin');
INSERT INTO users (username) values ('Guest');

INSERT INTO blogs (user_id, title, content) VALUES (1, 'First post', 'Hello World!');

# --- !Downs
DROP TABLE Blogs;
DROP SEQUENCE blog_id_seq;

drop table Comments;
drop sequence comments_id_seq;

DROP TABLE Users;
DROP SEQUENCE users_id_seq;