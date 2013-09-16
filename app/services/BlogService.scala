package services

import model.domain.{Comment, User, NewBlog, Blog}
import dao.Blogs

object BlogService {
  def all(): List[Blog] =
    Blogs.all()

  def add(userId: Long, title: String, content: String): Long =
    Blogs.insert(NewBlog(userId, title, content))

  def delete(id: Long) =
    Blogs.delete(id)

  def blogUserPairs() =
    Blogs.blogUserPairs()

  def triple(blogId: Long): Option[(Blog, User, List[Comment])] =
    Blogs.findTriple(blogId)
}
