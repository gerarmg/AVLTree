
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 * 203214
 * Jun 17, 2023
 */
   public class Tree <T extends Comparable> implements TreeADT<T>{
       protected Node<T> root;
       protected int size;

    public Tree(T elem){
        Node<T> newNode = new Node<T>(elem);
        root = newNode;
        size = 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean find(T elem) {
        return find(root,elem) != null;
    }
    
    private Node<T> find(Node<T> actual, T elem) {
        if (actual == null || actual.getElem().equals(elem))
            return actual;
        Node<T> temp = find(actual.getLeft(), elem); //Searches through left branch
        if(temp == null)//if it doesn't find it, it passes to the next branch
            temp = find(actual.getRight(),elem);
        return temp;
    }

    @Override
    public Iterator<T> preorder() {
        ArrayList<T> list = new ArrayList<T>();
        preorden(root,list);
        return list.iterator();       
    }
    
    private void preorden(Node<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        list.add(actual.getElem());
        preorden(actual.getRight(),list);
        preorden(actual.getLeft(),list);
    }

    @Override
    public Iterator<T> inorder() {
        ArrayList<T> list = new ArrayList<T>();
        inorder(root,list);
        return list.iterator();
    }
    
    private void inorder(Node<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        inorder(actual.getRight(),list);
        list.add(actual.getElem());
        inorder(actual.getLeft(),list);
    }

    @Override
    public Iterator<T> postorder() {
        ArrayList<T> list = new ArrayList<T>();
        postorder(root,list);
        return list.iterator();
    }
    
    private void postorder(Node<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        postorder(actual.getRight(),list);
        postorder(actual.getLeft(),list);
        list.add(actual.getElem());
    }

    @Override
    public Iterator<T> levels() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(T elem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
