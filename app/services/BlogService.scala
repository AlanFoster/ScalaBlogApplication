package services

import models.{Comment, User, Blog}
import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB

object BlogService {
  val comment: RowParser[Comment] = {
    get[Long]("commentId") ~
    get[Long]("blogId") ~
    get[String]("content") map {
      case commentId~blogId~content => Comment(commentId, blogId, content)
    }
  }

  val blog: RowParser[Blog] = {
    get[Long]("blogId") ~
    get[String]("title") ~
    get[String]("content") map {
      case blogId~title~content => Blog(blogId, title, content, List(Comment(1, blogId, "User Comment")))
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
