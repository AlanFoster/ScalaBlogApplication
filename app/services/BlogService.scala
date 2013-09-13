package services

import models.{Comment, Blog}
import org.squeryl.{Table, Schema}
import org.squeryl.PrimitiveTypeMode._

object BlogDB extends Schema {
  val blogTable: Table[Blog] = table[Blog]("blogs")
  val commentTable: Table[Comment] = table[Comment]("comments")

  val blogToComments =
    oneToManyRelation(blogTable, commentTable)
    .via((blog, comment) => blog.id === comment.blogId)
}

object BlogService {
  import BlogDB._

  def all(): List[Blog] =
    inTransaction {
      from(blogTable)(s => select(s)).toList
    }

  def add(title: String, content: String) {
    inTransaction {
      blogTable.insert(Blog(title, content))
    }
  }

  def delete(id: Long) = {
    inTransaction {
      blogTable.delete(id)
    }
  }
}
