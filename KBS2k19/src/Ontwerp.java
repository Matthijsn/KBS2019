import java.awt.*;
import java.util.ArrayList;
public class Ontwerp {
    private ArrayList<Component> Componenten;
    public Ontwerp(){
        Componenten = new ArrayList<>();
    }
    public void voegToe(Component C){
        Componenten.add(C);
    }
    public void tekenOntwerp(Graphics g){
    }
}
