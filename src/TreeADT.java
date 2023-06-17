

/**
 * @author Gerardo Guerrero <gguerr21@itam.mx> 
 * Jun 17, 2023
 */
import java.util.Iterator;

public interface TreeADT <T extends Comparable>{
    boolean isEmpty();
    int size();
    boolean find(T elem);
    Iterator<T> preorder();
    Iterator<T> inorder();
    Iterator<T> postorder();
    Iterator<T> levels ();

    void insert(T elem);
}