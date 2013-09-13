package models

case class Blog(id: Long, title: String, content: String, comments: List[Comment])