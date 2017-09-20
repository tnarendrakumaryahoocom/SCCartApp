package com.sc.web.controllers


import javax.inject.Inject

import play.api.libs.json.Json
import javax.inject.Singleton
import scala.concurrent.Future
import play.api.http.Status
import play.api.mvc.Request
import play.api.mvc.BodyParsers
import scala.concurrent.ExecutionContext
import play.api.mvc.ActionBuilderImpl
import play.api.mvc.Result
import play.api.Logger
import play.api.mvc.Results
import play.api.mvc.WrappedRequest
import com.sc.web.security.DataSource
import com.sc.web.model.Customer
import com.sc.web.model.CustRequest
import com.sc.web.utilities.JwtUtility
import play.api.libs.ws.WSClient





@Singleton
class ScAuthAction @Inject() (ws: WSClient, parser: BodyParsers.Default, dataSource: DataSource)(implicit ec: ExecutionContext) extends ActionBuilderImpl(parser) {
  
 
   implicit val jsonFormat = Json.format[Customer]

  
    override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]):Future[Result] = {
    Logger.info("Calling action")
    val jwtToken = request.headers.get("jw_token").getOrElse("")

      if (JwtUtility.isValidToken(jwtToken)) {
        JwtUtility.decodePayload(jwtToken).fold {
          Future { Results.Status(Status.UNAUTHORIZED) }
        } { payload =>
          val userCredentials = Json.parse(payload).validate[Customer].get

          val maybeUserInfo = dataSource.getCustUser(ws, userCredentials.name.get, userCredentials.password.get)

          maybeUserInfo.fold(Future {Results.Status(Status.INTERNAL_SERVER_ERROR)})(userInfo => return block(CustRequest(userInfo, request)))
        
        }
      } else {
        Future.successful(Results.Status(Status.UNAUTHORIZED) )
      }
  }
  
  
  
  
  
}