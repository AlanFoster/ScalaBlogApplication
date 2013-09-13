package services


object Blogs extends Table[Blog]("Blogs") {
  def id = column[Long]("id", O.PrimaryKey)
  def title = column[String]("title")
  def content = column[String]("content")

  def * = id ~ title ~ content <> (Blog, Blog.unapply _)
}

object BlogService {

  def all(): List[Blog] =
    Database.forDataSource(DB.getDataSource()) withSession {
      Query(Blogs).list
    }

  def add(title: String, content: String): Long = {
    Database.forDataSource(DB.getDataSource()) withSession {
      Blogs.insert(Blog(0, title, content))
    }
  }

  def delete(id: Long) = {
    Database.forDataSource(DB.getDataSource()) withSession {
      Query(Blogs).where(b => b.id === id).delete
    }
  }
}
