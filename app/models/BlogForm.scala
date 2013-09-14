package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

/**
 * Intermediate Data model used to represent creating a new blog.
 *
 * @param title
 * @param content
 */
case class BlogForm(title: String, content: String) {

}
