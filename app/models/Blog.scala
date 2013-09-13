package models

import org.squeryl.KeyedEntity
import org.squeryl.dsl.OneToMany
import services.BlogDB

case class Blog(title: String, content: String) extends KeyedEntity[Long] {
  val id: Long = 0
  lazy val comments: List[Comment] = BlogDB.blogToComments.left(this).toList
}