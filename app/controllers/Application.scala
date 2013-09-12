package controllers

import services.BlogService
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views.html

object Application extends Controller {
  val blogForm: Form[(String, String)] = Form(
    tuple(
      "title" -> text(minLength = 4),
      "content" -> nonEmptyText
    )
  )

  def index = Action {
    Redirect(routes.Application.blogs())
  }

  def blogs = Action {
    Ok(views.html.blogs(BlogService.all(), blogForm))
  }

  def newBlog = Action { implicit request =>
    blogForm.bindFromRequest.fold(
      errors => BadRequest(views.html.blogs(BlogService.all(), errors)),
      success => {
        BlogService.add(success._1, success._2)
        Redirect(routes.Application.blogs)
      }
    )
  }

  def viewBlog(id: Long) = Action {
    Ok("View blog :: " + id)
  }
  
}