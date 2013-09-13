# Blogs Schema
# Comments Schema
# Users Schema

# --- !Ups
CREATE SEQUENCE blog_id_seq;
CREATE TABLE Blogs (
  blogId integer NOT NULL DEFAULT nextval('blog_id_seq'),
  title varchar(255),
  content varchar(max)
);

CREATE SEQUENCE users_id_seq;
CREATE TABLE Users (
  userId integer NOT NULL DEFAULT nextval('users_id_seq'),
  username varchar(20)
);

CREATE SEQUENCE comments_id_Seq;
CREATE TABLE Comments (
  commentId integer NOT NULL DEFAULT nextval('comments_id_Seq'),
  blogId integer NOT NULL,
  username varchar(20),
  FOREIGN KEY (blogId)
  REFERENCES public.Blogs(blogId)
);


# --- !Downs
DROP TABLE Blogs;
DROP SEQUENCE blog_id_seq;

drop table Comments;
drop sequence comments_id_seq;

DROP TABLE Blogs;
DROP SEQUENCE users_id_seq;