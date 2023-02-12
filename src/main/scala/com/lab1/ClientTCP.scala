package com.lab1

import java.io.ObjectInputStream
import java.net.Socket

object ClientTCP {
  def main(args: Array[String]): Unit = {
    try { // Создается объект Socket
      // для соединения с сервером
      val clientSocket = new Socket("localhost", 1500)
      // Получаем ссылку на поток, связанный с сокетом
      val in = new ObjectInputStream(clientSocket.getInputStream)
      // Извлекаем объект из входного потока
      val dateMessage = in.readObject.asInstanceOf[DateMessage]
      clientSocket.close()
      // Выводим полученные данные в консоль
      println(dateMessage.getMessage)
      println(dateMessage.getDate)
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}

