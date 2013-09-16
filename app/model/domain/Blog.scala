package model.domain

/**
 * Represents a new Entity which has not been registered within the system yet.
 * As a result is has no id yet.
 */
case class NewBlog(userId: Long, title: String, content: String)

/**
 * Represents an Entity which has already been registered with the system.
 */
case class Blog(id: Long, userId: Long, title: String, content: String);