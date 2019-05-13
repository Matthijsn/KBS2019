import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Applicatie extends JFrame implements ActionListener, MouseListener {
    private final JLabel back1;
    private final JButton back3;
    private JButton jbbestand;
    private JLabel jlkosten;
    private JLabel jlbeschikbaarheid;
    private JButton jbberekenKosten;
    private JButton Plaats;
    private ArrayList<Component> Componenten;
    private ArrayList<DBServer> DBServers;
    private ArrayList<Webserver> Webservers;
    private ArrayList<Firewall> Firewalls;
    private ArrayList<Loadbalancer> Loadbalancers;
    private ArrayList<Component> Ontwerp;
    private JTree tree;
    private String SelectComponent;
    private Component SelectObject;
    private Image plaatje;
    //    private JButton
    Panel Panel = new Panel(this);
    private JMenuBar menuBar;
    private JMenu Bestand;
    private JMenuItem BNew, BSave, BOpen;
    private int drawx;
    private int drawy;
    private JButton opnieuw;
    private int count;

    public Applicatie() {
        Componenten = new ArrayList<>();
        DBServers = new ArrayList<>();
        Webservers = new ArrayList<>();
        Firewalls = new ArrayList<>();
        Loadbalancers = new ArrayList<>();
        Ontwerp = new ArrayList<>();
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
        JLabel jLbeschikbaarheid = new JLabel("");
        add(jlbeschikbaarheid);
        add(jLbeschikbaarheid);
        jLbeschikbaarheid.setPreferredSize(new Dimension(80, 50));
        jlbeschikbaarheid.setPreferredSize(new Dimension(120, 50));

        jbberekenKosten = new JButton("Bereken Kosten");
        jbberekenKosten.setPreferredSize(new Dimension(200, 50));
        add(jbberekenKosten);
        opnieuw = new JButton("Opnieuw");
        opnieuw.addActionListener(this);
        add(opnieuw);

        DefaultMutableTreeNode Ctree = new DefaultMutableTreeNode("Componenten");

        DefaultMutableTreeNode N1 = new DefaultMutableTreeNode("DBServers");
        Ctree.add(N1);
        DefaultMutableTreeNode N2 = new DefaultMutableTreeNode("Webservers");
        Ctree.add(N2);
        DefaultMutableTreeNode N3 = new DefaultMutableTreeNode("Firewall");
        Ctree.add(N3);
        DefaultMutableTreeNode N4 = new DefaultMutableTreeNode("Loadbalancer");
        Ctree.add(N4);

        for (DBServer D : DBServers) {
            ImageIcon imageIcon = new ImageIcon(Applicatie.class.getResource("database.png"));
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setLeafIcon(imageIcon);
            N1.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for (Webserver D : Webservers) {
            N2.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for (Firewall D : Firewalls) {
            N3.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        for (Loadbalancer D : Loadbalancers) {
            N4.add(new DefaultMutableTreeNode(D.getNaam()));
        }
        tree = new JTree(Ctree);
        JScrollPane JSP = new JScrollPane(tree);
        JSP.setPreferredSize(new Dimension(175, 200));
        add(JSP);
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                SelectComponent = selectedNode.getUserObject().toString();
            }
        });

        Plaats = new JButton("Plaats");
        Plaats.addActionListener(this);
        add(Panel);
        add(Plaats);
        back1 = new JLabel("Beschikbaarheid: ");
        add(back1);
        JTextField back2 = new JTextField("");
        add(back2);
        back2.setPreferredSize(new Dimension(80, 50));
        back1.setPreferredSize(new Dimension(120, 50));

        back3 = new JButton("Bereken Kosten");
        back3.setPreferredSize(new Dimension(200, 50));
        add(back3);
        MouseListener backListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(calculate(Double.parseDouble(back2.getText())));
            }
        };
        back3.addMouseListener(backListener);
//        MouseListener beschikbaarheidListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                System.out.println(jtbeschikbaarheid.getText());
//            }
//        };
//        jbberekenKosten.addMouseListener(beschikbaarheidListener);
//        MouseListener beschikbaarheidListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                System.out.println(jtbeschikbaarheid.getText());
//            }
//        };
//        jbberekenKosten.addMouseListener(beschikbaarheidListener);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Plaats){
            for(Component C:Componenten){
                if(C.getNaam()==SelectComponent){
                    SelectObject=C;
                }
            }
            Ontwerp.add(SelectObject);

        }else if(e.getSource()==opnieuw){
            Ontwerp.clear();
        }
        repaint();
    }
    public void TekenOntwerp(Graphics g){
        drawx = 340;
        drawy = 125;
        count = 0;
        for(Component C:Ontwerp){
            try {
                g.drawImage(C.getAfbeelding(), drawx, drawy, null);
                count++;
                if(drawy == 125){
                    drawy = drawy - 100;
                }else if(drawy == 25){
                    drawx = drawx - 80;
                    drawy = 125;
                }if(count > 3){
                    g.drawLine(drawx+25, drawy+125, drawx+80, drawy+125);
                }
            }catch(NullPointerException ex){
                System.out.println("Geen Component geselecteerd!");
            }
        }
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

    private String calculate(double percentage) {
        double total = 0.0;
        double[] dbservers = new double[this.getDBServers().size()];
        for(int i = 0; i < this.getDBServers().size(); i++) {
            dbservers[i] = this.getDBServers().get(i).getBeschikbaarheid();
        }
        int[] cheapest_dbservers = calc(dbservers, percentage);
        total = total + price("DBSERVER", cheapest_dbservers);

        double[] firewalls = new double[this.getFirewalls().size()];
        for(int i = 0; i < this.getFirewalls().size(); i++) {
            firewalls[i] = this.getFirewalls().get(i).getBeschikbaarheid();
        }
        int[] cheapest_firewalls = calc(firewalls, percentage);
        total = total + price("FIREWALL", cheapest_firewalls);

        double[] loadbalancers = new double[this.getLoadbalancers().size()];
        for(int i = 0; i < this.getLoadbalancers().size(); i++) {
            loadbalancers[i] = this.getLoadbalancers().get(i).getBeschikbaarheid();
        }
        int[] cheapest_loadbalancers = calc(loadbalancers, percentage);
        total = total + price("LOADBALANCER", cheapest_loadbalancers);
        double[] webservers = new double[this.getWebservers().size()];
        for(int i = 0; i < this.getWebservers().size(); i++) {
            webservers[i] = this.getWebservers().get(i).getBeschikbaarheid();
        }
        int[] cheapest_webservers = calc(webservers, percentage);
        total = total + price("WEBSERVER", cheapest_webservers);
        return "Totaalbedrag van de configuratie voor beschikbaarheidspercentage " + percentage + "% is €" + total;
    }

    private double price(String type, int[] cheapest) {
        double total = 0;
        if(cheapest[0] == -1) {
            System.out.println("Configuratie onmogelijk!");
            return 0;
        }
        switch (type) {
            case "WEBSERVER":
                for(int i = 0; i < cheapest.length; i++) {
                    System.out.println("WEBSERVER: " + this.getWebservers().get(i).getNaam() + "(" + this.getWebservers().get(i).getKosten() + ")");
                    int price = this.getWebservers().get(i).getKosten();
                    total = total + price;
                }
                break;
            case "DBSERVER":
                for(int i = 0; i < cheapest.length; i++) {
                    System.out.println("DBSERVER: " + this.getDBServers().get(i).getNaam() + "(" + this.getDBServers().get(i).getKosten() + ")");
                    int price = this.getDBServers().get(i).getKosten();
                    total = total + price;
                }
                break;
            case "LOADBALANCER":
                for(int i = 0; i < cheapest.length; i++) {
                    System.out.println("LOADBALANCER: " + this.getLoadbalancers().get(i).getNaam() + "(" + this.getLoadbalancers().get(i).getKosten() + ")");
                    int price = this.getLoadbalancers().get(i).getKosten();
                    total = total + price;
                }
                break;
            case "FIREWALL":
                for(int i = 0; i < cheapest.length; i++) {
                    System.out.println("FIREWALL: " + this.getFirewalls().get(i).getNaam() + "(" + this.getFirewalls().get(i).getKosten() + ")");
                    int price = this.getFirewalls().get(i).getKosten();
                    total = total + price;
                }
                break;
        }
        return total;
    }

    public int[] calc(double[] percentages, double goal) {
        int length = percentages.length;
        for(int i = 0; i < length; i++) {
            if(percentages[i] >= goal) {
                return new int[]{ i };
            }
            for (int j = 0; j < length; j++) {
                if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100))) * 100) >= goal) {
                    return new int[]{i, j};
                }
                for (int k = 0; k < length; k++) {
                    if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100)) * (1 - (percentages[k] / 100))) * 100) >= goal) {
                        return new int[]{i, j, k};
                    }
                    for (int l = 0; l < length; l++) {
                        if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100)) * (1 - (percentages[k] / 100)) * (1 - (percentages[l] / 100))) * 100) >= goal) {
                            return new int[]{i, j, k, l};
                        }
                        for (int m = 0; m < length; m++) {
                            if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100)) * (1 - (percentages[k] / 100)) * (1 - (percentages[l] / 100)) * (1 - (percentages[m] / 100))) * 100) >= goal) {
                                return new int[]{i, j, k, l, m};
                            }
                        }
                    }
                }
            }
        }

        return new int[] {-1};
    }

    public ArrayList<Component> getComponenten() {
        return Componenten;
    }

    public ArrayList<DBServer> getDBServers() {
        return DBServers;
    }

    public ArrayList<Webserver> getWebservers() {
        return Webservers;
    }

    public ArrayList<Firewall> getFirewalls() {
        return Firewalls;
    }

    public ArrayList<Loadbalancer> getLoadbalancers() {
        return Loadbalancers;
    }
}