
/**
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 * Jun 17, 2023
 */
public class Trunk {
    private Trunk previous;
    private String str;
    
    Trunk(Trunk previous,String str){
        this.previous = previous;
        this.str = str;
    }

    public Trunk getPrevious() {
        return previous;
    }

    public void setPrevious(Trunk previous) {
        this.previous = previous;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    
}
