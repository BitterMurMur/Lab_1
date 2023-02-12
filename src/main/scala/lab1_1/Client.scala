package lab1_1

import java.io.ObjectOutputStream
import java.net.{ServerSocket, Socket}

import scala.collection.mutable.ListBuffer

object Client {

  import java.net.{DatagramPacket, InetAddress, MulticastSocket}

  private var msgList  = new ListBuffer[String]()
  private var address:InetAddress = null
  private var buffer:Array[Byte] = null
  private var packet:DatagramPacket = null
  private var str:String = null
  private var socket:MulticastSocket = null
  var serverSocket = new ServerSocket(1500)
  var clientSocket:Socket = serverSocket.accept();

  @throws[Exception]
  def main(arg: Array[String]): Unit = {
    println("Ожидание сообщения от сервера")
    try { // Создание объекта MulticastSocket, чтобы получать
      // данные от группы, используя номер порта 1502
      socket = new MulticastSocket(1502)
      address = InetAddress.getByName("233.0.0.1")
//      // Регистрация клиента в группе
      socket.joinGroup(address)
      while ( true) {
        buffer = new Array[Byte](256)
        packet = new DatagramPacket(buffer, buffer.length)
        // Получение данных от сервера
        socket.receive(packet)
        str = new String(packet.getData)
        msgList.addOne(filterMessages(str))

        if (msgList.size > 5){
           transmit
        }

      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    } finally try {
      // Удаление клиента из группы
      socket.leaveGroup(address)
      // Закрытие сокета
      socket.close()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
  def transmit:Unit = {
    try {

      // Получение выходного потока,
      // связанного с объектом Socket
      val out: ObjectOutputStream =
      new ObjectOutputStream(
        clientSocket.getOutputStream());

      out.writeObject(createMsg)

    } catch {
      case e:Exception => e.printStackTrace();
    }
  }
  def filterMessages(data:String):String={
    var tmpData = data.replace("{", "")
    tmpData = tmpData.replace("}","")
    if (msgList.nonEmpty & !(msgList contains tmpData)){
      println("исходный файл изменился "+ tmpData)
    }
    tmpData
  }
  def createMsg:String = {
    var outStr = ""
    if (msgList.size > 5){
      for (i <- msgList.size - 5 until msgList.size)
      {
        outStr += msgList(i) + "\n"
      }
    }
    outStr
  }
}
