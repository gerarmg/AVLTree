
/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 * Jun 17, 2023
 */
public class Node <T extends Comparable>{
    
    private Node<T> dad;
    private T elem;
    private Node<T> left;
    private Node<T> right;
    private int ef;
    
    public Node(T elem){
        if (elem!=null){
            this.elem = elem;
        }
        else
            throw new RuntimeException("\n Elemento nulo");
    }
    
    public Node<T> getDad(){
        return dad;
    }

    public T getElem() {
        return elem;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public int getEf() {
        return ef;
    }

    public void setDad(Node<T> dad) {
        this.dad = dad;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setEf(int ef) {
        this.ef = ef;
    }
    
    public void hang(Node<T> newNode){
        if(newNode.elem.compareTo(elem) < 0)
            left = newNode;
        else
            right = newNode;
        
        newNode.setDad(this);
    }
    
}
