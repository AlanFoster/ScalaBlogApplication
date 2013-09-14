package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.driver.ExtendedDriver

object BlogDAO extends Table[Blog]("BLOGS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def userId = column[Long]("USER_ID")
  def title = column[String]("TITLE")
  def content = column[String]("CONTENT")
  def user = foreignKey("USER_ID_FK", userId, UsersDAO)(_.id)

  def * = id ~ userId ~ title ~ content <> (Blog, Blog.unapply _)

  def autoInc = userId ~ title ~ content <> (NewBlog, NewBlog.unapply _) returning id
  // Data access
  def all() =
    DB.withSession { implicit session: scala.slick.session.Session =>
      Query(BlogDAO).list
    }

  def insert(newBlog: NewBlog) =
    DB.withSession { implicit session: scala.slick.session.Session =>
      val one: ExtendedDriver#KeysInsertInvoker[NewBlog, Long]#RetOne = BlogDAO.autoInc.insert(newBlog)
      val list: List[Blog] = Query(BlogDAO).list()
      val foo = Query(UsersDAO).list()

      one
    }

  def delete(id: Long) =
    DB.withSession { implicit session: scala.slick.session.Session =>
      Query(BlogDAO).where(b => b.id === id).delete
    }

  def blogUserPairs(): List[(Blog, User)] = {
    DB.withSession {
      implicit session: scala.slick.session.Session => {
        for {
          blog <- BlogDAO
          poster <- blog.user
        } yield (blog, poster)
      }.list
    }
  }
}