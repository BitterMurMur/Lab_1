package com.lab1


object Client {

  import java.net.{DatagramPacket, InetAddress, MulticastSocket}

  private var address:InetAddress = null
  private var buffer:Array[Byte] = null
  private var packet:DatagramPacket = null
  private var str:String = null
  private var socket:MulticastSocket = null

  @throws[Exception]
  def main(arg: Array[String]): Unit = {
    println("Ожидание сообщения от сервера")
    try { // Создание объекта MulticastSocket, чтобы получать
      // данные от группы, используя номер порта 1502
      socket = new MulticastSocket(1502)
      address = InetAddress.getByName("233.0.0.1")
      // Регистрация клиента в группе
      socket.joinGroup(address)
      while ( {
        true
      }) {
        buffer = new Array[Byte](256)
        packet = new DatagramPacket(buffer, buffer.length)
        // Получение данных от сервера
        socket.receive(packet)
        str = new String(packet.getData)
        System.out.println("Получено сообщение: " + str.trim)
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    } finally try { // Удаление клиента из группы
      socket.leaveGroup(address)
      // Закрытие сокета
      socket.close()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}
