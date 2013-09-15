package models

case class Comment(id: Option[Long], blogId: Long, userId: Long, content: String) {
  def data = (blogId, userId, content)
}