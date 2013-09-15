package models

/**
 * Represents a new Entity which has not been registered within the system yet.
 * As a result is has no id yet.
 */
case class NewComment(blogId: Long, userId: Long, content: String);

/**
 * Represents an Entity which has already been registered with the system.
 */
case class Comment(id: Long, blogId: Long, userId: Long, content: String);