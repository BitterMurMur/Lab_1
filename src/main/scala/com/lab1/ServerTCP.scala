package com.lab1

import java.io.ObjectOutputStream
import java.net.{ServerSocket, Socket}
import java.util.Calendar

object ServerTCP extends Thread{
  // Объявляется ссылка
  // на объект - сокет сервера
   var serverSocket:ServerSocket = null;

  /**
   * Конструктор по умолчанию
   */
  def ServerTCP():Unit = {
    try {
      // Создается объект ServerSocket, который получает
      // запросы клиента на порт 1500
      serverSocket = new ServerSocket(1500)
      println("Starting the server ")
      // Запускаем процесс
      start();
    } catch  {
        case e => e.printStackTrace();
    }
  }

  /**
   * Запуск процесса
   */
  override def start():Unit = {
    try {
      val test = getFile()
      while (true) {
        // Ожидание запросов соединения от клиентов
         var clientSocket:Socket = serverSocket.accept();
        println("Connection accepted from " + clientSocket.getInetAddress().getHostAddress());

        // Получение выходного потока,
        // связанного с объектом Socket
        var  out:ObjectOutputStream =
          new ObjectOutputStream(
            clientSocket.getOutputStream());

        // Создание объекта для передачи клиентам
        var dateMessage = new DateMessage(
          Calendar.getInstance().getTime,
          "Текущая дата/время на сервере")
        // Запись объекта в выходной поток
        out.writeObject(dateMessage)
        out.close();
      }
    } catch {
        case e => e.printStackTrace();
    }
  }
  def getFile (): String ={
    val source = scala.io.Source.fromFile("D:\\Java Projects\\Lab_1\\src\\main\\scala\\com\\lab1\\data")
    try source.mkString finally source.close()
  }
  def main(arg: Array[String]):Unit = {
    // Запуск сервера
    ServerTCP()
  }

}


