package com.sc.web.model

import play.api.mvc.WrappedRequest
import play.api.mvc.Request

case class CustInfo(id: Int,
                    firstName: String,
                    lastName: String,
                    email: String)

case class Customer(name: Option[String], password: Option[String], var token:Option[String])


case class CustRequest[A](userInfo: CustInfo, request: Request[A]) extends WrappedRequest[A](request)

  
