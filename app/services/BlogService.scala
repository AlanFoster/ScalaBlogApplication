package services

import models.{UsersDAO, NewBlog, Blog, BlogDAO}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

object BlogService {
  def all(): List[Blog] =
    BlogDAO.all()

  def add(userId: Long, title: String, content: String): Long =
    BlogDAO.insert(NewBlog(userId, title, content))

  def delete(id: Long) =
    BlogDAO.delete(id)

  def blogUserPairs() =
    BlogDAO.blogUserPairs()
}
