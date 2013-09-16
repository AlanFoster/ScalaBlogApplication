package domain

/**
  * Represents a new Entity which has not been registered within the system yet.
  * As a result is has no id yet.
 */
case class NewUser(username: String)

/**
 * Represents an Entity which has already been registered with the system.
 */
case class User(id: Long, username: String)

