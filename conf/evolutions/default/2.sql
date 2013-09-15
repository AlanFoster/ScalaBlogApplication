# --- Data population

# --- !Ups

-- User Creation
INSERT INTO Users (username) values ('Admin');
INSERT INTO Users (username) values ('Guest');

-- Example Blog and Comment Creation
INSERT INTO Blogs (user_id, title, content) VALUES (1, 'First post', 'Hello World!');
INSERT INTO Comments (blogId, userId, content) values (1, 2, 'Nice Post!')

# --- !Downs

delete from Users;
delete from Blogs;
