package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

class Healthchecks extends Controller {

  private[this] val Result = Json.toJson(Map("status" -> "healthy"))

  def getInternalAndHealthcheck() = Action { request =>
    Ok(Result)
  }

}
