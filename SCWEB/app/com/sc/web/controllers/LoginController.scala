package com.sc.web.controllers

import javax.inject._
import play.api._
import play.api.mvc._

import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global
import com.sc.web.model.Customer
import com.sc.web.utilities.JwtUtility
import com.sc.web.security.DataSource
import scala.concurrent.Future
import com.sc.web.model.CustInfo

@Singleton
class LoginController @Inject() (ws: WSClient, scAuthAction: ScAuthAction, components: ControllerComponents, dataSource: DataSource) extends AbstractController(components) {

  implicit val jsonFormat = Json.format[Customer]

  implicit val custInfoFormat = Json.format[CustInfo]

  def index = Action {
    Ok(views.html.index("Shopping cart Application"))
  }

  def login = Action(parse.json) { implicit request =>
    val jsonBody = request.body
    val obj = jsonBody.as[Customer]
    var payload = jsonBody.toString();
    var deStr = JwtUtility.createToken(payload);
    obj.token = Some(deStr);
    val maybeUserInfo = dataSource.getCustUser(ws, obj.name.get, obj.password.get)
    if (maybeUserInfo.isDefined) {
      Ok(Json.toJson(obj))
    } else {
      obj.token = None;
      Ok(Json.toJson(obj))
    }

  }

}
