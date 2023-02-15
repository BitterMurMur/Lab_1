package Lab2

class CircleNodeList {

    var headNode:CircleNode = null
    var tailNode:CircleNode = null

    def add(message:String) ={
        var node = new CircleNode(message)

        if (headNode.equals(null)){
            headNode = node
            tailNode = node
            node.nextNode = headNode
        }
        else{
            tailNode.nextNode = node
            tailNode = node
            tailNode.nextNode = headNode
          }

    }
}
//if self.head.data is None:
//#If list is empty, both head and tail would point to new node.
//self.head = newNode;
//self.tail = newNode;
//newNode.next = self.head;
//else:
//#tail will point to new node.
//self.tail.next = newNode;
//#New node will become new tail.
//self.tail = newNode;
//#Since, it is circular linked list tail will point to head.
//self.tail.next = self.head;
