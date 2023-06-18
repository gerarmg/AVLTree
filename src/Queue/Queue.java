
package Queue;

/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 * Apr 19, 2022
 */
public class Queue<T> implements QueueADT<T> {
    private T[] queue;
    private int start;
    private int end;
    private final int MAX = 20;
    
    public Queue(){
        queue = (T[]) new Object[MAX];
        start = -1;
        end = -1;
    }
    
    public Queue(int max){
        queue = (T[]) new Object[max];
        start = -1;
        end = -1;
    }

    @Override
    public void add(T elem) {
        if(isEmpty())
            start++;
        else
            if(( end + 1 ) % queue.length == start) //Cola llena
                expand();
        
        end = ( end + 1 ) % queue.length;
        queue[end] = elem;
    }

    @Override
    public T remove() {
        if(isEmpty())
            throw new RuntimeException("Empty queue");

        T result;
        
        result = queue[start];
        queue[start] = null;
        
        if (start == end){
            start = -1;
            end = -1;
        }
        else
            start=(start+1) % queue.length;
        
        return result;
        
    }

    @Override
    public boolean isEmpty() {
        return start == -1;
    }

    @Override
    public T examine() {
        if(!this.isEmpty())
            return queue[start];
        throw new RuntimeException("Empty queue");
    }

    private void expand() {
        T[] bigger = (T[]) new Object[queue.length*2];
        int size = queue.length;
        
        for(int i=0; i < size; i++)
            bigger[i] = queue[(start + i) % size];
        
        start = 0;
        end = size - 1;
        queue = bigger;
    }
    
    public String toString(){
        StringBuilder bob = new StringBuilder();
        if(!this.isEmpty()) {
            int i = 0;
            int j;
            boolean flag = true;
            
            j = (start + i) % queue.length;
            
            if(end == start-1){
                while( flag ){
                bob.append(queue[j]+"\n");
                i++;
                j = (start + i) % queue.length; 
                if(j == end + 1 )
                    flag = false;
                }
            }
            else
                while( j != end + 1 ){
                    bob.append(queue[j]+"\n");
                    i++;
                    j = (start + i) % queue.length;                
                }
        }
        return bob.toString();
    }
    
    
    public void reverse(){
        int i;
        T aux;
        
        if(start!=0){
            while(start < queue.length){
                //Circulate one step
                aux = queue[queue.length-1];
                for(i=1;i<queue.length;i++){
                    queue[queue.length-i]=queue[queue.length-i-1];
                }
            queue[0]=aux;
            start++;
            end++;
            }
            start = 0; //guarantee that start<=end y que queue[start] == queue[0]
            end = (end % queue.length);
        }
        // reverse the array
        for(i=start; i<=end/2; i++){
            aux = queue[i];
            queue[i] = queue[end-i];
            queue[end-i] = aux;
        }  
    }
}
