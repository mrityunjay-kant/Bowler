package org.bowlerframework.http

import javax.servlet.http.HttpSession
import collection.mutable.MutableList
import org.bowlerframework.Session


class BowlerHttpSession(session: Option[HttpSession]) extends Session {
  def getId = if(!session.isEmpty) session.get.getId else null

  private val errors = "_bowlerValidationErrors"
  private val lastGetName = "_bowlerLastGetUrl"
  private val validationModel = "_bowlerValidationModel"

  def getAttributeNames: List[String] = {
    if(!session.isEmpty) {
      val list = new MutableList[String]
      val enum = session.get.getAttributeNames
      while (enum.hasMoreElements)
        list += enum.nextElement.toString
      return list.toList
    }
    else
      Nil
  }

  def getCreationTime = if(!session.isEmpty) session.get.getCreationTime else -1L

  def invalidate = {
    if(!session.isEmpty) session.get.invalidate
  }

  def removeAttribute(name: String) = if(!session.isEmpty) session.get.removeAttribute(name)

  def setAttribute[T](name: String, value: T) = if(!session.isEmpty) session.get.setAttribute(name, value)

  def getAttribute[T](name: String): Option[T] = {
    if(!session.isEmpty) {
    if (session.get.getAttribute(name) != null)
      return Some(session.get.getAttribute(name).asInstanceOf[T])
    else
      return None
    }
    else
      None
  }

  def getUnderlyingSession = session.getOrElse(sys.error("Attempt to retrieve Underlying session when none has been initialized."))

  def setLastGetPath(path: String) = {
    if(!session.isEmpty) session.get.setAttribute(lastGetName, path)
  }

  def getLastGetPath: Option[String] = {
    if(!session.isEmpty) {
      try {
        val lastGet = session.get.getAttribute(lastGetName).asInstanceOf[String]
        if (lastGet == null || lastGet == None) {
          return None
        } else
          return Some(lastGet)
      } catch {
        case e: NoSuchElementException => return None
      }
    }
    else
      None

  }

  def resetValidations = {
    if(!session.isEmpty) {
      session.get.removeAttribute(errors)
      session.get.removeAttribute(validationModel)
    }
  }

  def getErrors: Option[List[Tuple2[String, String]]] = {
      try {
        val validationErrors = if(!session.isEmpty) session.get.getAttribute(errors).asInstanceOf[List[Tuple2[String, String]]] else null
        if (validationErrors == null || validationErrors == None) {
          return None
        } else
          return Some(validationErrors)
      } catch {
        case e: NoSuchElementException => return None
      }
  }

  def setErrors(errors: List[(String, String)]) = {
    if(!session.isEmpty) session.get.setAttribute(this.errors, errors)
  }

  def getValidatedModel: Option[Seq[Any]] = {
    try {
      val models = if(!session.isEmpty) session.get.getAttribute(validationModel).asInstanceOf[Seq[Any]] else null
      if (models == null || models == None) {
        return None
      } else
        return Some(models)
    } catch {
      case e: NoSuchElementException => return None
    }
  }

  def setValidationModel(model: Seq[Any]) = {
    if(!session.isEmpty) session.get.setAttribute(validationModel, model)
  }

  def setMaxInactiveInterval(interval: Int) = {if(!session.isEmpty) session.get.setMaxInactiveInterval(interval)}

  //try to timeout session asap
  def getMaxInactiveInterval = {if(!session.isEmpty) session.get.getMaxInactiveInterval else 0}
}