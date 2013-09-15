package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.{Config, DB}
import scala.slick.driver.ExtendedDriver


// TODO It would be nice to split this code into reusable queries with no session, and the actual implementation with sesion

object BlogDAO extends BaseTable[Blog]("BLOGS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def userId = column[Long]("USER_ID")
  def title = column[String]("TITLE")
  def content = column[String]("CONTENT")
  def user = foreignKey("USER_ID_FK", userId, UsersDAO)(_.id)

  def dataCols = userId ~ title ~ content

  def * = id ~: dataCols <> (Blog, Blog.unapply _)

  def autoInc = dataCols <> (NewBlog, NewBlog.unapply _) returning id

  // Data access
  def insert(newBlog: NewBlog): Long =
    DB.withSession { implicit session: scala.slick.session.Session =>
      BlogDAO.autoInc.insert(newBlog)
    }

  def delete(id: Long) =
    DB.withSession { implicit session: scala.slick.session.Session =>
      Query(BlogDAO).where(b => b.id === id).delete
    }

  private def blogUserPairsQuery = {
    for {
      blog <- BlogDAO
      poster <- blog.user
    } yield (blog, poster)
  }

/*  def findBlogAndPosterById(blogId: Long) = {
    for {
      (blog, poster) <- blogUserPairsQuery
      // Cannot prove that Option[U] =:= Long.
      if blog.id.get == blogId
    } yield(blog, poster)
  }*/

  def blogUserPairs(): List[(Blog, User)] = {
    DB.withSession {
      implicit session: scala.slick.session.Session => {
        blogUserPairsQuery
      }.list
    }
  }

}