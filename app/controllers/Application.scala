package controllers

import services.BlogService
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views.html
import models.{UsersDAO, BlogForm}

object Application extends Controller {
  val blogForm: Form[BlogForm] = Form(
    mapping(
      "title" -> text(minLength = 1),
      "content" -> nonEmptyText
    )(BlogForm.apply)(BlogForm.unapply)
  )

  def index = Action {
    Redirect(routes.Application.blogs())
  }

  def blogs = Action {
    Ok(views.html.blogs(BlogService.blogUserPairs(), blogForm))
  }

  def newBlog = Action { implicit request =>
    blogForm.bindFromRequest.fold(
      errorModel => BadRequest(views.html.blogs(BlogService.blogUserPairs(), errorModel)),
      successModel => {
        // Hardcoded user id of 1, since we can't log in yet
        BlogService.add(1, successModel.title, successModel.content)
        Redirect(routes.Application.blogs())
      }
    )
  }

  def viewBlog(id: Long) = Action {
    // TODO This should be in my data access layer
    Ok(views.html.viewBlog(BlogService.blogUserPairs().find(_._1.id == id)))
  }
  
}