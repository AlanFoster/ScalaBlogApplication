package services

import models.{Blog, BlogDAO}

object BlogService {
  def all(): List[Blog] =
    BlogDAO.all()

  def add(userId: Long, title: String, content: String): Blog =
    BlogDAO.insert(Blog(None, userId, title, content))

  def delete(id: Long) =
    BlogDAO.delete(id)

  def blogUserPairs() =
    BlogDAO.blogUserPairs()
}
