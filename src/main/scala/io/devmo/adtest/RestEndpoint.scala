package com.clearscore.adtest

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.scaladsl.Flow

trait RestEndpoint {
  this: Sys with SomeRemoteServiceSlice =>

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import akka.http.scaladsl.server.Directives._
  import spray.json.DefaultJsonProtocol._

  def routes: Flow[HttpRequest, HttpResponse, Any] = get {
    path("single") {
      complete {
        timed("single") {
          someService.resourceSize("foo")
        }
      }
    }
  }
}