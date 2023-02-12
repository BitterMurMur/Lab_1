package com.lab1

import java.io.InputStreamReader

object Server {

  import java.io.BufferedReader
  import java.net.{DatagramPacket, DatagramSocket, InetAddress}

  private var in:BufferedReader = null
  private var str:String = null
  private var buffer:Array[Byte] = null
  private var packet:DatagramPacket = null
  private var address:InetAddress = null
  private var socket: DatagramSocket = new DatagramSocket()

  def transmit() {
    try {
      // создается входной поток, чтобы принимать
      // данные с консоли
      in = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        println(
          "Введите строку для передачи клиентам: ");
        str = in.readLine();
        buffer = str.getBytes();
        address = InetAddress.getByName("233.0.0.1");
        // Посылка пакета датаграмм на порт номер 1502
        packet = new DatagramPacket(
          buffer,
          buffer.length,
          address,
          1502);

        //Посылка сообщений всем клиентам в группе
         socket.send(packet);
      }
      } catch {
        case e:Exception => e.printStackTrace();
      } finally {
        try {
          // Закрытие потока и сокета
          in.close();
          socket.close();
        } catch {
          case e:Exception => e.printStackTrace();
        }
      }
  }
  def main(arg: Array[String]):Unit={
    transmit()
  }
}
