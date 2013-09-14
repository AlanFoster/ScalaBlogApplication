package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

case class NewBlog(userId: Long, title: String, content: String)
case class Blog(id: Long, userId: Long, title: String, content: String) {
  //lazy val comments: List[Comment] = BlogDB.blogToComments.left(this).toList
}

