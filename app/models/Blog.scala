package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

case class Blog(id: Option[Long], userId: Long, title: String, content: String) {
  def data = (userId, title, content)
}