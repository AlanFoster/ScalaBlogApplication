package services

import models.{User, Blog}
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB

object BlogService {
  val blog: RowParser[Blog] = {
    get[Long]("id") ~
    get[String]("title") ~
    get[String]("content") map {
      case id~title~content => Blog(id, title, content)
    }
  }

  def all(): List[Blog] = DB.withConnection { implicit c =>
    SQL("select * from blogs").as(blog *)
  }

  def add(title: String, content: String) {
    DB.withConnection { implicit c =>
      SQL("insert into blogs (title, content) values ({title}, {content})").on(
        'title -> title,
        'content -> content
      ).executeUpdate()
    }
  }

  def delete(id: Long) = {
    DB.withConnection { implicit c =>
      SQL("delete from blogs where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}
