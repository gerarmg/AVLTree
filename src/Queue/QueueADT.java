

package Queue;

/**
 * @author Gerardo Guerrero <gguerr21@itam.mx> 
 * 203214
 * Apr 19, 2022
 */
public interface QueueADT <T>{
    public void add(T dato);
    public T remove();
    public boolean isEmpty();
    public T examine();
    public String toString();

}
