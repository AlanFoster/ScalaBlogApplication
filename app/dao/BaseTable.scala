package dao

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.driver.ExtendedDriver

/**
 * Represents the base table that should be extended.
 *
 * @param _schemaName
 * @param _tableName
 * @tparam T The type of the table we map to, this will be your datamodel.
 *           All extending classes should provide a `*` method matching this type
 */
abstract class BaseTable[T](_schemaName: Option[String], _tableName: String) extends Table[T](_schemaName, _tableName) {
  def this(_tableName: String) = this(None, _tableName)

  /*  def all() = Query[this.type, T, this.type](this)*/
  def all() =
    DB.withSession {
      implicit session: scala.slick.session.Session => Query[this.type, T, this.type](this).list
    }
}
