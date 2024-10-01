import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> pingQ;
    Queue<Integer> pangQ;
    public MyStack() {
        pingQ = new LinkedList<Integer>();
        pangQ = new LinkedList<Integer>();
    }
    public void push(int x) {
        if(pingQ.isEmpty()){
            pingQ.add(x);
            while(!pangQ.isEmpty()){
                pingQ.add(pangQ.remove());
            }
        }else{
            pangQ.add(x);
            while(!pingQ.isEmpty()){
                pingQ.add(pangQ.remove());
            }
        }
    }

    public int pop() {
        if(pangQ.isEmpty()){
            return pingQ.remove();
        }else
            return pangQ.remove();
    }
    public int top() {
        if(pangQ.isEmpty()){
            return pingQ.peek();
        }else
            return pangQ.peek();
    }
    public boolean empty() {
        return pingQ.isEmpty() && pangQ.isEmpty();
    }
}
