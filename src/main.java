
/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 * 203214
 * Jun 17, 2023
 */
public class main {
    public static void main(String[] args) {
        System.out.println("Inserta");
        Tree<Integer> arbol = new Tree<Integer>();
        Integer[] array = {100,200,300,50,25,60};
        arbol.insertaArreglo(array);
        arbol.printTree();
    }

}
