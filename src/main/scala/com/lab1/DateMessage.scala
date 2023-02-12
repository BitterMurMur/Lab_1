package com.lab1

import java.util.Date

import java.io.Serializable

class DateMessage(var date: Date, var message: String) extends Serializable {

  def getDate: Date = date

  def setDate(date: Date): Unit = {
    this.date = date
  }

  def getMessage: String = message

  def setMessage(message: String): Unit = {
    this.message = message
  }
}

