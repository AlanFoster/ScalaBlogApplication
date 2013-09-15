package services

import models._
import models.NewBlog
import models.Blog
import models.User

object BlogService {
  def all(): List[Blog] =
    BlogDAO.all()

  def add(userId: Long, title: String, content: String): Long =
    BlogDAO.insert(NewBlog(userId, title, content))

  def delete(id: Long) =
    BlogDAO.delete(id)

  def blogUserPairs() =
    BlogDAO.blogUserPairs()

  def triple(blogId: Long): Option[(Blog, User, List[Comment])] =
    BlogDAO.findTriple(blogId)
}
