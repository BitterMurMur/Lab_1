package lab1_1

import java.io.ObjectInputStream
import java.net.Socket

object Client2 {

  import java.net.{DatagramPacket, InetAddress, MulticastSocket}

  var msgList:List[String] = null
  private var address:InetAddress = null
  private var buffer:Array[Byte] = null
  private var packet:DatagramPacket = null
  private var str:String = null
  private var socket:MulticastSocket = null

  @throws[Exception]
  def main(arg: Array[String]): Unit = {
    println("Ожидание сообщения от сервера")
    try {
      while(true){
        // для соединения с сервером
        val clientSocket = new Socket("localhost", 1500)
        // Получаем ссылку на поток, связанный с сокетом
        val in = new ObjectInputStream(clientSocket.getInputStream)
        // Извлекаем объект из входного потока
        val msg = in.readObject.asInstanceOf[String]
        clientSocket.close()
        // Выводим полученные данные в консоль
        if (msg.contains("\n")) {
          println("Получено сообщение: " + msg)
        }
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

}
