package models

import org.squeryl.KeyedEntity

case class Comment(blogId: Long, content: String) extends KeyedEntity[Long] {
  val id: Long = 0
}