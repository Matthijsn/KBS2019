import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Panel extends JPanel {
    private Applicatie Applicatietje;
    private BufferedImage cloud;
    private double hight = 80.5;
    public Panel(Applicatie A){
        Applicatietje = A;
        setPreferredSize(new Dimension(400, 200));
        setBackground(Color.LIGHT_GRAY);
        try{
            cloud = ImageIO.read(new File("src/internet.png"));
        } catch (IOException ex) {
            System.out.println("Plaatje niet gevonden.");
        }
    }
    public void paint(Graphics g){
        paintComponent(g);
        g.drawImage(cloud, 0, 80, null);
        Applicatietje.TekenOntwerp(g);
        repaint();
    }
}