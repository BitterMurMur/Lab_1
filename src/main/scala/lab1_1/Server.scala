package lab1_1

import java.util.{Calendar,Timer, TimerTask}
import java.net.{DatagramPacket, DatagramSocket, InetAddress}


object Server {



  var msg:String = null
  private var buffer:Array[Byte] = null
  private var packet:DatagramPacket = null
  private var address:InetAddress = null
  private var socket: DatagramSocket = null

  def transmit() {
    try {

      val timer = new Timer()
      timer.schedule(new TimerTask() {
        override def run(): Unit = {
          socket = new DatagramSocket()
          msg = getFile
          buffer = msg.getBytes
          address = InetAddress.getByName("233.0.0.1")
          // Посылка пакета датаграмм на порт номер 1502
          packet = new DatagramPacket(buffer, buffer.length, address, 1502)
          socket.send(packet)
          println(Calendar.getInstance().getTime() + " сообщение отправлено")
        }
      }, 1000 * 10, 1000 * 10)

      } catch {
        case e:Exception => e.printStackTrace();
      } finally {
        try {
          socket.close();
        } catch {
          case e:Exception => e.printStackTrace();
        }
      }
  }
  def main(arg: Array[String]):Unit={

    transmit()
  }

  def getFile: String = {
    val source = scala.io.Source.fromFile("D:\\Java Projects\\Lab_1\\src\\main\\scala\\lab1_1\\data")
    try source.mkString finally source.close()
  }
}
