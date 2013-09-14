package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

case class NewComment(blogId: Long, content: String)
case class Comment(id: Long, blogId: Long, content: String)

object Comments extends Table[Comment]("COMMENTS") {
  def id = column[Long]("ID")
  def blogId = column[Long]("BLOGID")
  def blog = foreignKey("BLOG_FK", blogId, BlogDAO)(_.id)
  def content = column[String]("CONTENT")

  def * = id ~ blogId ~ content <> (Comment.apply _, Comment.unapply _)

}
