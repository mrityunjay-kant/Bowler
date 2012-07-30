package org.bowlerframework.view.scalate

import org.bowlerframework.view.ViewRenderer
import org.bowlerframework.exception.{ValidationException, HttpException}
import org.bowlerframework.model.ViewModelBuilder
import collection.mutable.MutableList
import org.bowlerframework.{BowlerConfigurator, Response, Request}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: 20/04/2011
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */

trait BrowserViewRenderer extends ViewRenderer{
  def onError(request: Request, response: Response, exception: Exception) = {
    response.setContentType("text/html")
    if (classOf[HttpException].isAssignableFrom(exception.getClass)) {
      if (exception.isInstanceOf[ValidationException]) {
        val validations = exception.asInstanceOf[ValidationException]
        if(!request.getSession.isEmpty) request.getSession.get.setErrors(validations.errors)
        
        BowlerConfigurator.errorRenderMap.get(request.getPath) match{
          case None => {
            if (!request.getSession.isEmpty && request.getSession.get.getLastGetPath != None)
             response.sendRedirect(request.getSession.get.getLastGetPath.get)
//            else   //TODO code this response path !!!!
//              response.sendRedirect()
          }case Some(path) => {
            response.sendRedirect(path)
          }
        }
      } else {
        val http = exception.asInstanceOf[HttpException]
        response.sendError(http.code)
        // TODO
        // render error pages?
      }

    } else {
      throw exception
    }
  }

  def renderView(request: Request, response: Response, models: Seq[Any]) = {
    response.setContentType("text/html")
    val validated = if(!request.getSession.isEmpty) request.getSession.get.getValidatedModel else None
    var tempModel = models
    if (validated != None && validated.get.size > 0)
      tempModel = tempModel ++ validated.get

    val model = ViewModelBuilder(tempModel)
    if (!request.getSession.isEmpty && request.getSession.get.getErrors != None) {
      val list = new MutableList[String]
      request.getSession.get.getErrors.get.foreach(f => list += f._2)
      model += "validationErrors" -> list.toList
    }
    try{
      if(!request.getSession.isEmpty) request.getSession.get.resetValidations
    }catch{
      case e: IllegalStateException => println("IllegalStateException (no session to reset): " +  e) // ignore, there is no active session, hence nothing to reset
    }
    render(request, response, model.toMap)
  }

  protected def render(request: Request, response: Response, model: Map[String, Any])
}