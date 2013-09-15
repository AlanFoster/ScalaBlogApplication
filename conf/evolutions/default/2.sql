# --- Data population

# --- !Ups
INSERT INTO users (username) values ('Admin');
INSERT INTO users (username) values ('Guest');

INSERT INTO blogs (user_id, title, content) VALUES (1, 'First post', 'Hello World!');

# --- !Downs

delete from users;
delete from blogs;
