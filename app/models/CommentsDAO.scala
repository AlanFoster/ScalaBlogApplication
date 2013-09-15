package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.lifted

object CommentsDAO extends Table[Comment]("COMMENTS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def blogId = column[Long]("BLOGID")
  def userId = column[Long]("USERID")
  def content = column[String]("CONTENT")
  def dataCols = blogId ~ userId ~ content

  def * = id ~: dataCols <> (Comment.apply _, Comment.unapply _)

  def autoIncr = dataCols returning id into {
    (m, id) => Function.uncurried( (Comment.apply _).curried(id) ).tupled(m)
  }

  def blog = foreignKey("BLOG_FK", blogId, BlogDAO)(_.id)

  def findCommentsByIdQuery(blogId: Long): lifted.Query[CommentsDAO.type, Comment] =
    Query(this).where(_.blogId === blogId)
}
