package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.{Config, DB}
import scala.slick.driver.ExtendedDriver

object BlogDAO extends BaseTable[Blog]("BLOGS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def userId = column[Long]("USER_ID")
  def title = column[String]("TITLE")
  def content = column[String]("CONTENT")
  def user = foreignKey("USER_ID_FK", userId, UsersDAO)(_.id)

  def dataCols = userId ~ title ~ content

  def * = id.? ~: dataCols <> (Blog, Blog.unapply _)

  def autoInc = dataCols returning id.? into {
    case (m, id) => Function.uncurried( (Blog.apply _).curried(id)).tupled(m)
  }
  // Data access
  def insert(blog: Blog): Blog =
    DB.withSession { implicit session: scala.slick.session.Session =>
      BlogDAO.autoInc.insert(blog.data)
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