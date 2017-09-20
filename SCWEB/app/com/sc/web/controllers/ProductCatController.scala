package com.sc.web.controllers

import javax.inject._
import play.api._
import play.api.mvc._

import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global
import com.typesafe.config.ConfigFactory
import com.sc.web.model.Customer

@Singleton
class ProductCatController @Inject() (ws: WSClient, scAuthAction: ScAuthAction, components: ControllerComponents) extends AbstractController(components) {

  implicit val jsonFormat = Json.format[Customer]

  def getAllProducts = Action.async { request =>
    val apiUrl: String = ConfigFactory.load().getString("productApi");

    ws.url(apiUrl).get().map {
      response =>
        Ok(response.body)
    }

  }

}
