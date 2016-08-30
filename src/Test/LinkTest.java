package Test;

/**
 * Created by Administrator on 2015-12-24.
 */
//链表
public class LinkTest {
    public static void main(String[] args){
        Node n1 = new Node("li");
        Node n2 = new Node("yu");
        Node n3 = new Node("bin");
        n1.setNext(n2);
        n2.setNext(n3);

        print(n1);
    }
    //递归输出
    public static void print(Node n){
        System.out.println(n.getData());
        if(n.getNext()!=null){
            print(n.getNext());
        }
    }

}

class Node{
    private String data;
    private Node next;
    public Node(String data){
        this.data=data;
    }
    public String getData(){
        return this.data;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }
    public String getNextdata(){
        return this.next.data;
    }
}
