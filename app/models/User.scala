package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.driver.ExtendedDriver

case class User(id: Option[Long], username: String)

object UsersDAO extends Table[User]("USERS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def username = column[String]("USERNAME")

  def * = id.? ~ username <> (User, User.unapply _)
  def autoIncr = id.? ~ username <> (User, User.unapply _) returning id
}