import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Applicatie extends JFrame implements ActionListener, MouseListener {
    private JButton jbbestand;
    private JLabel jlkosten;
    private JLabel jlbeschikbaarheid;
    private JButton jbberekenKosten;
    private JTextField Tx;
    private JTextField Ty;
    private ArrayList<Component> Componenten;
    private ArrayList<DBServer> DBServers;
    private ArrayList<Webserver> Webservers;
    private String[] types = {"DBServer", "Webserver", "Loadbalancer    ", "Firewall"};
    private JList<String> cb = new JList<>(types);
    private String[] DBS = {"HAL9001DB", "HAL9002DB", "HAL9003DB", " "};
    JList<String> DB = new JList<>(DBS);
    private String Input;
    //    private JButton
    Panel Panel = new Panel();
    JFrame frame = new JFrame();

    public Applicatie() {
        Componenten = new ArrayList<>();
        DBServers = new ArrayList<>();
        Webservers = new ArrayList<>();
        DBServer HAL9001DB = new DBServer("HAL9001DB ", 90, 5100);
        DBServers.add(HAL9001DB);
        DBServer HAL9002DB = new DBServer("HAL9002DB", 95, 7700);
        DBServers.add(HAL9002DB);
        DBServer HAL9003DB = new DBServer("HAL9003DB", 98, 122000);
        DBServers.add(HAL9003DB);
        Webservers.add(new Webserver("HAL9001W", 80, 2200));
        Webservers.add(new Webserver("HAL9002W", 90, 3200));
        Webservers.add(new Webserver("HAL9003W", 95, 5100));
        Componenten.addAll(Webservers);
        Componenten.addAll(DBServers);
        Componenten.add(new Firewall("pfSense", 99.999, 2000));
        Componenten.add(new Loadbalancer("DBloadbalancer", 99.999, 2000));

        frame.setTitle("Infrastructuur Applicatie");
        frame.setSize(800, 400);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //bestand button
        jbbestand = new JButton("Bestand");
        frame.add(jbbestand);
        //jbbestand.addActionListener(this);
        jbbestand.setPreferredSize(new Dimension(150, 50));
        jbbestand.setAlignmentX(LEFT_ALIGNMENT);

        jlkosten = new JLabel("Kosten: ");
        frame.add(jlkosten);
        jlkosten.setPreferredSize(new Dimension(200, 50));

        jlbeschikbaarheid = new JLabel("Beschikbaarheid: ");
        frame.add(jlbeschikbaarheid);
        jlbeschikbaarheid.setPreferredSize(new Dimension(200, 50));

        jbberekenKosten = new JButton("Bereken Kosten");
        jbberekenKosten.setPreferredSize(new Dimension(200, 50));
        frame.add(jbberekenKosten);
        //        String[] DBS = {" "};

        String[] WS = {"HAL9001W", "HAL9002W", "HAL9003W", " "};
        frame.add(cb);
        if(Input == "DBServer"){
            frame.add(DB);
        }
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    System.out.println(cb.getSelectedValue());
            }
        };
        System.out.println("hoii");
        cb.addMouseListener(mouseListener);
        frame.add(Panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
    }
    public void mousePressed(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
}