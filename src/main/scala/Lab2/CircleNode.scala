package Lab2

class CircleNode(message:String) {

   var nextNode: CircleNode = null
   var token: String = null

  def echo(nodeNumber:Int) ={
    if (message == token){
      println("Сообщение "+ message + " Узел "+ nodeNumber)
    }
    else {
      println("Узел "+ nodeNumber +" недоступен")
    }

  }
}

