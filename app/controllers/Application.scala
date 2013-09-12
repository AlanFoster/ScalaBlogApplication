package controllers

import services.BlogService
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views.html
import models.BlogForm

object Application extends Controller {
  val blogForm: Form[BlogForm] = Form(
    mapping(
      "title" -> text(minLength = 4),
      "content" -> nonEmptyText
    )(BlogForm.apply)(BlogForm.unapply)
  )

  def index = Action {
    Redirect(routes.Application.blogs())
  }

  def blogs = Action {
    Ok(views.html.blogs(BlogService.all(), blogForm))
  }

  def newBlog = Action { implicit request =>
    blogForm.bindFromRequest.fold(
      errorModel => BadRequest(views.html.blogs(BlogService.all(), errorModel)),
      successModel => {
        BlogService.add(successModel.title, successModel.content)
        Redirect(routes.Application.blogs)
      }
    )
  }

  def viewBlog(id: Long) = Action {
    Ok("View blog :: " + id)
  }
  
}