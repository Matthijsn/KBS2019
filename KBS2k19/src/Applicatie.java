import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
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
    private ArrayList<Firewall> Firewalls;
    private ArrayList<Loadbalancer> Loadbalancers;
    private JTree tree;
    //    private JButton
    Panel Panel = new Panel();
    private JMenuBar menuBar;
    private JMenu Bestand;
    private JMenuItem BNew,BSave,BOpen;

    public Applicatie() {
        Componenten = new ArrayList<>();
        DBServers = new ArrayList<>();
        Webservers = new ArrayList<>();
        Firewalls = new ArrayList<>();
        Loadbalancers = new ArrayList<>();
        DBServer HAL9001DB = new DBServer("HAL9001DB ", 90, 5100);
        DBServers.add(HAL9001DB);
        DBServer HAL9002DB = new DBServer("HAL9002DB", 95, 7700);
        DBServers.add(HAL9002DB);
        DBServer HAL9003DB = new DBServer("HAL9003DB", 98, 122000);
        DBServers.add(HAL9003DB);
        Webservers.add(new Webserver("HAL9001W", 80, 2200));
        Webservers.add(new Webserver("HAL9002W", 90, 3200));
        Webservers.add(new Webserver("HAL9003W", 95, 5100));
        Firewalls.add(new Firewall("pfSense", 99.999, 2000));
        Loadbalancers.add(new Loadbalancer("DBloadbalancer", 99.999, 2000));
        Componenten.addAll(Webservers);
        Componenten.addAll(DBServers);
        Componenten.addAll(Firewalls);
        Componenten.addAll(Loadbalancers);

        setTitle("Infrastructuur Applicatie");
        setSize(800, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        Bestand = new JMenu("Bestand");
        Bestand.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                System.out.println("selected bestand");
            }

            public void menuDeselected(MenuEvent e) {
                System.out.println("deselected bestand");

            }

            public void menuCanceled(MenuEvent e) {
                System.out.println("canceled bestand");

            }
        });
        menuBar.add(Bestand);

        BNew = new JMenuItem("New");
        BNew.addActionListener(this);
        Bestand.add(BNew);

        BSave = new JMenuItem("Save");
        BSave.addActionListener(this);
        Bestand.add(BSave);

        BOpen = new JMenuItem("open");
        BOpen.addActionListener(this);
        Bestand.add(BOpen);

        //bestand button
        jbbestand = new JButton("Bestand");
        add(jbbestand);
        //jbbestand.addActionListener(this);
        jbbestand.setPreferredSize(new Dimension(150, 50));
        jbbestand.setAlignmentX(LEFT_ALIGNMENT);

        jlkosten = new JLabel("Kosten: ");
        add(jlkosten);
        jlkosten.setPreferredSize(new Dimension(200, 50));

        jlbeschikbaarheid = new JLabel("Beschikbaarheid: ");
        add(jlbeschikbaarheid);
        jlbeschikbaarheid.setPreferredSize(new Dimension(200, 50));

        jbberekenKosten = new JButton("Bereken Kosten");
        jbberekenKosten.setPreferredSize(new Dimension(200, 50));
        add(jbberekenKosten);
        //        String[] DBS = {" "};

        //create the root node
        DefaultMutableTreeNode Ctree = new DefaultMutableTreeNode("Componenten");

        DefaultMutableTreeNode N1 = new DefaultMutableTreeNode("DBServers");
        Ctree.add(N1);
        DefaultMutableTreeNode N2 = new DefaultMutableTreeNode("Webservers");
        Ctree.add(N2);
        DefaultMutableTreeNode N3 = new DefaultMutableTreeNode("Firewall");
        Ctree.add(N3);
        DefaultMutableTreeNode N4 = new DefaultMutableTreeNode("Loadbalancer                    ");
        Ctree.add(N4);

        for(DBServer D:DBServers){
            ImageIcon imageIcon = new ImageIcon(Applicatie.class.getResource("database.png"));
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(imageIcon);
            N1.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for(Webserver D:Webservers){
            N2.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for(Firewall D:Firewalls){
            N3.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for(Loadbalancer D:Loadbalancers){
            N4.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        tree = new JTree(Ctree);
        JScrollPane JSP = new JScrollPane(tree);
        JSP.setPreferredSize(new Dimension(175, 200));
        add(JSP);
        add(Tx);
        add(Ty);

        System.out.println("hoii");
        add(Panel);
        setVisible(true);
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