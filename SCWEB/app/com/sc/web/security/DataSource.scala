package com.sc.web.security

import com.sc.web.model.CustInfo
import play.api.libs.ws.WSClient
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext.Implicits.global
import com.sc.web.model.Customer
import play.api.libs.json.Json
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success

class DataSource {

  implicit val jsonFormat = Json.format[Customer]
  
  def getCustUser(ws: WSClient, name: String, password: String): Option[CustInfo] =
    if (name != null && password != null) {
      val userInfoUrl: String = ConfigFactory.load().getString("userInfoUrl");
      val futRes = ws.url(userInfoUrl + "/" + name).get()
      val furResult = Await.result(futRes, Duration.Inf).body
      val jsonBody = Json.parse(furResult) \ "userInfo"
      val obj = jsonBody.as[Customer]
      if(name.equalsIgnoreCase(obj.name.get)  && password.equalsIgnoreCase(obj.password.get)){
        Some(CustInfo(1, obj.name.get, "", ""))
      }else None
    } else {
      None
    }
}
