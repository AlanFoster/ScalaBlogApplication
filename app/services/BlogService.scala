package services

import models.{NewBlog, Blog, BlogDAO}

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
