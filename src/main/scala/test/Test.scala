import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ExampleCircleWave extends App{

  case class NodeCounter(n:Int, token:String, actor:ActorRef)
  case class NextNode(n:Int, token:String)

  class NodeActor(nodes:Int) extends Actor{
    var currentNode = 0

    def receive ={

      case NodeCounter(n,token,actor) => println("Всего узлов " + n)
        actor ! NextNode(currentNode + 1, token)

      case NextNode(currentNode, token) =>
        if (currentNode <= nodes){
          print("Узел " + currentNode + message(token) + "\n")
          sender ! NextNode(currentNode + 1, token)
        }
        else{
          println("Возврат в начальный узел")
          sender ! NodeCounter(nodes,"token",actor2)
        }
        context.system.terminate()
    }
    def message(token:String):String = if (token != "") " ok " else " узел недоступен "
  }
  val nodes = 5
  val system = ActorSystem("System")
  val actor1 = system.actorOf(Props(new NodeActor(nodes)), "ExampleCircleWave")
  val actor2 = system.actorOf(Props(new NodeActor(nodes)), "ExampleCircleWave2")
  actor1 ! NodeCounter(nodes,"token", actor2)

}