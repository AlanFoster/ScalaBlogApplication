package models

case class Blog(id: Long, title: String, content: String) {
  //lazy val comments: List[Comment] = BlogDB.blogToComments.left(this).toList
}