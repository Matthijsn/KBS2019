import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Component {
    private String naam;
    private String type;
    private double beschikbaarheid;
    private int kosten;
    private String Return;
    private BufferedImage DBServer, Webserver, Firewall, Loadbalancer;
    private BufferedImage Image;

    public Component(String naam, String type, double beschikbaarheid, int kosten) {
        this.naam = naam;
        this.type = type;
        this.beschikbaarheid = beschikbaarheid;
        this.kosten = kosten;
    }

    @Override
    public String toString() {
        return "Component{" +
                "naam='" + naam + '\'' +
                ", type='" + type + '\'' +
                ", beschikbaarheid=" + beschikbaarheid +
                ", kosten=" + kosten +
                '}';
    }


    public String getNaam() {
        return naam;
    }

    public String getType() {
        if (this instanceof DBServer) {
            Return = "DBServer";
        } else if (this instanceof Webserver) {
            Return = "Webserver";
        } else if (this instanceof Firewall) {
            Return = "Firewall";
        } else if (this instanceof Loadbalancer) {
            Return = "Loadbalancer";
        }
        return Return;
    }

    public BufferedImage getAfbeelding() {
        try {
            DBServer = ImageIO.read(new File("src/Database.png"));
            Webserver = ImageIO.read(new File("src/Webserver.png"));
            Firewall = ImageIO.read(new File("src/Firewall.png"));
            Loadbalancer = ImageIO.read(new File("src/Loadbalancer.png"));
        } catch (IOException ex) {
            System.out.println("Plaatje niet gevonden.");
        }
        if (this instanceof DBServer) {
            Image = DBServer;
        } else if (this instanceof Webserver) {
            Image = Webserver;
        } else if (this instanceof Firewall) {
            Image = Firewall;
        } else if (this instanceof Loadbalancer) {
            Image = Loadbalancer;
        }
        return Image;
    }


    public double getBeschikbaarheid() {
        return beschikbaarheid;
    }

    public int getKosten() {
        return kosten;
    }
}
