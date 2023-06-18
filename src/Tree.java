
import Queue.Queue;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
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
    
    public Tree(){
        
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
        Queue<Node<T>> queue = new Queue<>();
        Node<T> actual;
        ArrayList<T> lista = new ArrayList<>();

        actual = root;
        queue.add(actual);
        while(!queue.isEmpty()){
            actual = queue.remove();
            lista.add(actual.getElem());
            if(actual.getLeft()!=null)
                queue.add(actual.getRight());
            if(actual.getRight()!=null)
                queue.add(actual.getRight());
        }

        return lista.iterator();
    }

    @Override
    public void insert(T elem) {
        Node<T> nuevo = new Node<>(elem);
        if(!isEmpty()) {
            Node<T>  dad;
            Node actual;
            boolean in = false;

            actual = root;
            dad = root;
            while (actual != null) {
                dad = actual;
                if (elem.compareTo(actual.getElem()) == 0)//non-repeated elements only
                    return;
                else if (elem.compareTo(actual.getElem()) < 0) //if lesser, goes left
                    actual = actual.getLeft();
                else
                    actual = actual.getRight();
            }
            actual = dad;
            actual.hang(nuevo);
            size++;
        }
        else
            root = nuevo;
            size++;
    }
    
    public void insertaArreglo(T[] array){
        for(int i=0;i< array.length;i++)
            insert(array[i]);
    }
    
    public void delete(T elem){
        if(!this.isEmpty()) {
            Node<T> deletion = find(root,elem);
            
            if (deletion != null){
                deleteByCases(deletion);
            }
        }
    }
    
    private int height(Node<T> node){
        if (node == null)
            return 0;
        int right = 0,left = 0;
        if(node.getRight() != null)
            right = height(node.getRight()) + 1;
        if(node.getLeft() != null)
            left = height(node.getLeft()) + 1;
        
        return Math.max(right,left);
  
    }
    
    protected Node<T> deleteByCases(Node node) {
        Node dad;

        if (node != root) {
            dad = node.getDad();
            Node hijoUnico;

            //if node is a leaf, whe only need to delete the link to dad
            if (node.getLeft() == null && node.getRight() == null) {
                if (node.getElem().compareTo(dad.getElem()) < 0) { //it was left son
                    dad.setLeft(null);
                    dad.setEf(dad.getEf()+1);
                }
                else {
                    dad.setRight(null);//right son
                    dad.setEf(dad.getEf()-1);
                }
                return dad; //climb to root
            }
            //1 or 2 child nodes
            else
                //2 child nodes
                if (node.getLeft() != null && node.getRight() != null) {
                //we find de succesor 
                    Node<T> actual = node.getRight();
                    Node<T> successor;
                    if(actual.getLeft()!=null) { //if the successor is on the left branch
                        while (actual.getLeft() != null)
                            actual = actual.getLeft();
                        successor = actual;
                        dad = actual.getDad();
                        dad.setLeft(null);//eliminamos la dirección al successor
                        dad.cuelgaAVL(successor.getRight(),"der");
                        dad.setEf(alturaDer(dad)-alturaIzq(dad));
                    }
                    else{//el successor es el inmediato a la derecha
                        successor = actual;
                        dad = actual.getDad();
                        dad.setRight(null);
                        //si el successor tiene un hijo derecho,
                        // le colgamos dicho hijo al dad del successor en su rama derecha
                        if(successor.getRight()!=null){//si el successor tenía hijos derechos, se cuelgan a la derecha de su dad
                            dad.setRight(successor.getRight());
                        }
                        dad.setEf(alturaDer(dad)-alturaIzq(dad));
                    }
                    node.setElem(successor.getElem());
                }
                //si solo tiene un hijo
                else {
                    //salvamos al hijo del node a borrar
                    if (node.getLeft() != null) {
                        hijoUnico = node.getLeft();
                        dad.setEf(dad.getEf()+1);
                    }
                    else {
                        hijoUnico = node.getRight();
                        dad.setEf(dad.getEf()-1);
                    }
                    dad.cuelga(hijoUnico);
                }
                return dad;
        }
        else {
            //si el node es la raíz
            //si la raíz es el único node
            if (node.getLeft() == null && node.getRight() == null)
                root = null;
            else {
                if (node.getLeft() != null && node.getRight() != null) { //si ambas ramas tienen algo, hacemos el proceso del successor
                    Node<T> actual, successor;
                    actual = node.getRight();
                    successor = actual;
                    if(actual.getLeft()!=null) { //si el successor está en la rama izquierda
                        while (actual.getLeft() != null)
                            actual = actual.getLeft();
                        successor = actual;
                        dad = actual.getDad();
                        dad.setLeft(null);//eliminamos la dirección al successor
                        dad.cuelgaAVL(successor.getRight(),"der");
                        dad.setEf(alturaDer(dad)-alturaIzq(dad));
                    }
                    else{//el successor es el inmediato a la derecha
                        successor = actual;
                        dad = actual.getDad();
                        dad.setRight(null);
                        //si el successor tiene un hijo derecho,
                        // le colgamos dicho hijo al dad del successor en su rama derecha
                        if(successor.getRight()!=null){//si el successor tenía hijos derechos, se cuelgan a la derecha de su dad
                            dad.setRight(successor.getRight());
                        }
                        dad.setEf(alturaDer(dad)-alturaIzq(dad));
                    }
                    node.setElem(successor.getElem());
                    return dad;
                } else //si solo alguna rama está vacía
                    if (node.getLeft() != null) {
                        node.getLeft().setPapa(null);
                        root = node.getLeft();
                    } else {
                        node.getRight().setPapa(null);
                        root = node.getRight();
                      
                    }
                    root.setEf(alturaDer(root)-alturaDer(root));
            }
            return root;
        }
    }
    
    private void showTrunks(Trunk p){
        if(p == null)
            return;
        showTrunks(p.getPrevious());
        System.out.print(p.getStr());
    }

    public void printTree(){
        printTree(root,null,false);
    }

    private void printTree(Node<T> node, Trunk previous, boolean itsLeft){

        if(node == null)
            return;

        String previous_str ="    ";
        Trunk trunk = new Trunk(previous,previous_str);
        if(node.getRight()!=null)
            printTree(node.getRight(),trunk,true);

        if(previous == null){
            trunk.setStr("——");
        }
        else if(itsLeft){
            trunk.setStr(".———");
            previous_str = "   |";
        }
        else{
            trunk.setStr("`———");
            trunk.getPrevious().setStr(previous_str);
        }

        showTrunks(trunk);
        System.out.println(" "+node.getElem());

        if(trunk.getPrevious() != null)
            trunk.getPrevious().setStr(previous_str);
        trunk.setStr("   |");
        if(node.getLeft()!=null)
            printTree(node.getLeft(),trunk,false);
    }
}
