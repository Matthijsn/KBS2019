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
    private JLabel back1;
    private JButton back3;
    private JLabel jlkosten;
    private JLabel jlbeschikbaarheid;
    private JButton Plaats;
    private ArrayList<Verbinding> Verbindingen;
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
    private JLabel jlkostenontwerp;
    private JLabel jlbeschikbaarheidontwerp;
    private int totaalkosten = 0;
    private double beschikbaarheid = 100;
    private JLabel JLComponent;
    private String bestwebserver;
    private String bestdbserver;
    private String bestloadbalancer;
    private String bestfirewall;
    private String bestconfig;
    private JLabel JLbestconfig;
    private JButton JBKoppelen;
    private boolean BKoppelen;
    private Component SelectedComponent;
    private Component Component1;
    private int id = 0;

    public Applicatie() {
        Componenten = new ArrayList<>();
        DBServers = new ArrayList<>();
        Webservers = new ArrayList<>();
        Firewalls = new ArrayList<>();
        Loadbalancers = new ArrayList<>();
        Ontwerp = new ArrayList<>();
        Verbindingen = new ArrayList<>();
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
        Loadbalancers.add(new Loadbalancer("Loadbalancer", 99.999, 2000));
        Componenten.addAll(Webservers);
        Componenten.addAll(DBServers);
        Componenten.addAll(Firewalls);
        Componenten.addAll(Loadbalancers);

        setTitle("Infrastructuur Applicatie");
        setSize(800, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

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

        BOpen = new JMenuItem("Open");
        BOpen.addActionListener(this);
        Bestand.add(BOpen);

        jlkosten = new JLabel("Kosten: ");
        add(jlkosten);
        jlkosten.setPreferredSize(new Dimension(80, 50));

        jlkostenontwerp = new JLabel("00,00");
        add(jlkostenontwerp);
        jlkostenontwerp.setPreferredSize(new Dimension(170,50));

        jlbeschikbaarheid = new JLabel("Beschikbaarheid: ");
        add(jlbeschikbaarheid);
        jlbeschikbaarheid.setPreferredSize(new Dimension(120, 50));

        jlbeschikbaarheidontwerp = new JLabel("00.00%");
        add(jlbeschikbaarheidontwerp);
        jlbeschikbaarheidontwerp.setPreferredSize(new Dimension(170, 50));

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
        DefaultTreeCellRenderer cr = new DefaultTreeCellRenderer();
        cr.setOpenIcon(null);
        cr.setClosedIcon(null);
        cr.setLeafIcon(null);
        tree.setCellRenderer(cr);
        JScrollPane JSP = new JScrollPane(tree);
        JSP.setPreferredSize(new Dimension(175, 200));
        add(JSP);
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                try {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    SelectComponent = selectedNode.getUserObject().toString();
                }catch(NullPointerException NE){

                }
                for(Component C:Componenten){
                    if(C.getNaam()==SelectComponent){
                        SelectObject=C;
                        JLComponent.setText("<html>Naam: "+C.getNaam()+"<br/>Type:"+C.getType()+"<br/>Beschikbaarheid:"+C.getBeschikbaarheid()+"%<br/> Kosten:"+C.getKosten()+" </html>");

                    }
                }

            }
        });

        Plaats = new JButton("Plaats");
        Plaats.addActionListener(this);
        Panel.addMouseListener(this);
        add(Panel);
        JLComponent = new JLabel("<html>Naam: <br/>" +
                "Type: <br/>" +
                "Beschikbaarheid:00.00%<br/>" +
                "Kosten: </html>");
        add(JLComponent);
        JBKoppelen = new JButton("Koppelen");
        JBKoppelen.addActionListener(this);
        BKoppelen = false;
        add(JBKoppelen);
        add(Plaats);
        back1 = new JLabel("Gewenste beschikbaarheid: (%)");
        add(back1);
        JTextField back2 = new JTextField("");
        add(back2);
        back2.setPreferredSize(new Dimension(80, 50));
        back1.setPreferredSize(new Dimension(180, 50));

        back3 = new JButton("Bereken goedkoopste configuratie");
        back3.setPreferredSize(new Dimension(240, 50));
        add(back3);
        MouseListener backListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(calculate(Double.parseDouble(back2.getText())));
            }
        };
        back3.addMouseListener(backListener);
        //MouseListener beschikbaarheidListener = new MouseAdapter() {
        //    public void mouseClicked(MouseEvent e) {
        //        System.out.println(jtbeschikbaarheid.getText());
        //    }
        //};
        //jbberekenKosten.addMouseListener(beschikbaarheidListener);
//        MouseListener beschikbaarheidListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                System.out.println(jtbeschikbaarheid.getText());
//            }
//        };
//        jbberekenKosten.addMouseListener(beschikbaarheidListener);

        JLbestconfig = new JLabel("");
        add(JLbestconfig);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        int id= 0;
        if(e.getSource()==Plaats){
            for(Component C:Componenten){
                if(C.getNaam()==SelectComponent){
                    SelectObject=C;
                }
            }
            if (SelectObject instanceof DBServer) {
                Ontwerp.add(new DBServer(id, SelectObject.getNaam(), SelectObject.getType(), SelectObject.getBeschikbaarheid(), SelectObject.getKosten()));
            } else if (SelectObject instanceof Webserver) {
                Ontwerp.add(new Webserver(id, SelectObject.getNaam(), SelectObject.getType(), SelectObject.getBeschikbaarheid(), SelectObject.getKosten()));
            } else if (SelectObject instanceof Firewall) {
                Ontwerp.add(new Firewall(id, SelectObject.getNaam(), SelectObject.getType(), SelectObject.getBeschikbaarheid(), SelectObject.getKosten()));
            } else if (SelectObject instanceof Loadbalancer) {
                Ontwerp.add(new Loadbalancer(id, SelectObject.getNaam(), SelectObject.getType(), SelectObject.getBeschikbaarheid(), SelectObject.getKosten()));
            }
            id++;
            repaint();
            for(Component C:Ontwerp) {
                if (C.getType() == "Firewall") {
                    for (Component CO : Ontwerp) {
                        totaalkosten = totaalkosten + CO.getKosten();
                        //andere beschikbaarheid berekening omdat het een firewall is
                        beschikbaarheid = beschikbaarheid * (1-(1-CO.getBeschikbaarheid())*(1-CO.getBeschikbaarheid())) / 100;
                    }
                }
                else if(C.getType() == "Loadbalancer"){
                    for (Component CO : Ontwerp) {
                        totaalkosten = totaalkosten + CO.getKosten();
                        //andere beschikbaarheid berekening omdat het een loadbalancer is
                        beschikbaarheid = (((1 - (1 - (CO.getBeschikbaarheid() / 100)) * (1 - (CO.getBeschikbaarheid() / 100))) * 100));
                        //beschikbaarheid = beschikbaarheid * CO.getBeschikbaarheid() / 100;
                    }
                }
                else if(C.getType() == "Webserver"){
                    for (Component CO : Ontwerp) {
                        totaalkosten = totaalkosten + SelectObject.getKosten();
                        beschikbaarheid = beschikbaarheid * CO.getBeschikbaarheid() / 100;
                    }
                }
                else if(C.getType() == "DBServer"){
                    for (Component CO : Ontwerp) {
                        totaalkosten = totaalkosten + SelectObject.getKosten();
                        beschikbaarheid = beschikbaarheid * CO.getBeschikbaarheid() / 100;
                    }
                }
                jlbeschikbaarheidontwerp.setText(String.valueOf(beschikbaarheid));
                jlkostenontwerp.setText(String.valueOf(totaalkosten));
                totaalkosten = 0;
                beschikbaarheid =100;
            }

            repaint();

        }else if(e.getSource()==opnieuw){
            Ontwerp.clear();
            Verbindingen.clear();
            id = 0;
            JLComponent.setText("<html>Naam: <br/>" +
                    "Type: <br/>" +
                    "Beschikbaarheid:00.00%<br/>" +
                    "Kosten: </html>");
            JLbestconfig.setText("");
            jlkostenontwerp.setText("00.00");
            jlbeschikbaarheidontwerp.setText("00.00");
        }
        if(e.getSource()==JBKoppelen){
            if(!BKoppelen) {
                    BKoppelen = true;
                    Component1 = SelectedComponent;
                    System.out.println(Component1);
            }else{
                System.out.println(SelectedComponent);
                BKoppelen = false;
                Verbinding V1 = new Verbinding(Component1, SelectedComponent);
                Verbindingen.add(V1);
                repaint();
            }

        }
    }
    public void TekenOntwerp(Graphics g) {
        if (Ontwerp.size() >=2) {
            if(drawy==125) {
                g.drawLine(26, 100, drawx + 80, drawy + 25);
                g.drawLine(26, 100, drawx + 80, drawy - 75);
            }else if (drawy==25){
                g.drawLine(26, 100, drawx + 80, drawy + 25);
                g.drawLine(26, 100, drawx, drawy + 125);
            }
        }
        drawx = 340;
        drawy = 125;
        count = 0;
        try {
            for (Component C : Ontwerp) {
                g.drawImage(C.getAfbeelding(), drawx, drawy, null);
                C.Plaats(drawx, drawy);
                if (count >= 2) {
//                    g.drawLine(drawx + 50, drawy + 25, drawx + 80, drawy + 2
//                    5);
                }
                if (drawy == 125) {
                    drawy = drawy - 100;
                } else if (drawy == 25) {
                    drawx = drawx - 80;
                    drawy = 125;
                }
                g.drawString(C.getNaam(), C.getX(), C.getY()+60);
            count++;
                    for (Verbinding V : Verbindingen) {
                        if(V.getC1().getX()>V.getC2().getX()) {
                            g.drawLine(V.getC1().getX()+10, V.getC1().getY()+25, V.getC2().getX()+25, V.getC2().getY()+25);
                        }else if(V.getC1().getX()<V.getC2().getX()){
                            g.drawLine(V.getC1().getX()+10, V.getC1().getY()+25, V.getC2().getX()+25, V.getC2().getY()+25);
                        }else if(V.getC1().getY()>V.getC2().getY()){
                            g.drawLine(V.getC1().getX()+25, V.getC1().getY(), V.getC2().getX()+25, V.getC2().getY()+50);
                        }else if (V.getC1().getY()<V.getC2().getY()){
                            g.drawLine(V.getC1().getX()+25, V.getC1().getY()+50, V.getC2().getX()+25, V.getC2().getY());
                        }
                }
        }

        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(this, "Geen Component Geselecteerd",
                    "Geen component", JOptionPane.WARNING_MESSAGE);
            Ontwerp.clear();
        }
    }
    public void mouseClicked(MouseEvent e) {
        for (Component C : Ontwerp) {
            if (C.getX() <= e.getX() && C.getX() + 50 >= e.getX() && C.getY() <= e.getY() && C.getY() + 50 >= e.getY()) {
                JLComponent.setText("<html>Naam: " + C.getNaam() + "<br/>Type:" + C.getType() + "<br/>Beschikbaarheid:" + C.getBeschikbaarheid() + "%<br/> Kosten:" + C.getKosten() + "</html>");
                SelectedComponent = C;
            }
        }
        if (e.getX() >= 0 && e.getX() <= 50 && e.getY() >= 80 && e.getY() <= 130) {

        }
    }
    public void mousePressed(MouseEvent e){
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
        bestconfig = "Totaalbedrag van de configuratie voor beschikbaarheidspercentage " + percentage + "% is €" + total;
        return "Totaalbedrag van de configuratie voor beschikbaarheidspercentage " + percentage + "% is €" + total;
    }

    private double price(String type, int[] cheapest) {

        double total = 0;
        if(cheapest[0] == -1) {
            JOptionPane.showMessageDialog(this, "Configuratie onmogelijk",
                    "Configuratie onmogelijk", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        switch (type) {
            case "WEBSERVER":
                for(int i = 0; i < cheapest.length; i++) {
                    bestwebserver = "WEBSERVER: " + this.getWebservers().get(cheapest[i]).getNaam()
                            + "(€"+ this.getWebservers().get(cheapest[i]).getKosten() + " | " +
                            this.getWebservers().get(cheapest[i]).getBeschikbaarheid() + "% uptime)";
                    int price = this.getWebservers().get(cheapest[i]).getKosten();
                    total = total + price;
                }
                break;
            case "DBSERVER":
                for(int i = 0; i < cheapest.length; i++) {
                    bestdbserver = "DBSERVER: " + this.getDBServers().get(cheapest[i]).getNaam() +
                            "(€"+ this.getDBServers().get(cheapest[i]).getKosten() + " | " +
                            this.getDBServers().get(cheapest[i]).getBeschikbaarheid() + "% uptime)";
                    int price = this.getDBServers().get(cheapest[i]).getKosten();
                    total = total + price;
                }
                break;
            case "LOADBALANCER":
                for(int i = 0; i < cheapest.length; i++) {
                    bestloadbalancer = "LOADBALANCER: " + this.getLoadbalancers().get(cheapest[i]).getNaam() +
                            "(€"+ this.getLoadbalancers().get(cheapest[i]).getKosten() + " | " +
                            this.getLoadbalancers().get(cheapest[i]).getBeschikbaarheid() + "% uptime)";
                    int price = this.getLoadbalancers().get(cheapest[i]).getKosten();
                    total = total + price;
                }
                break;
            case "FIREWALL":
                for(int i = 0; i < cheapest.length; i++) {
                    bestfirewall = "FIREWALL: " + this.getFirewalls().get(cheapest[i]).getNaam() +
                            "(€"+ this.getFirewalls().get(cheapest[i]).getKosten() + " | " +
                            this.getFirewalls().get(cheapest[i]).getBeschikbaarheid() + "% uptime)";
                    int price = this.getFirewalls().get(cheapest[i]).getKosten();
                    total = total + price;
                }
                break;
        }
        JLbestconfig.setText(bestwebserver + ", " + bestdbserver + ", " + bestloadbalancer + ", " +
                bestfirewall + ", " + bestconfig);
        return total;
    }

    public int[] calc(double[] percentages, double goal) {
        int length = percentages.length;
        for(int i = 0; i < length; i++) {
            if (percentages[i] >= goal) {
                //System.out.println("length = " + length + " & percentages[" + i + "] (" + percentages[i] + ") is >= " + goal);
                return new int[]{i};
            }
        }
        for(int i = 0; i < length; i++) {
            if (percentages[i] >= goal) {
                return new int[]{i};
            }
            for (int j = 0; j < length; j++) {
                if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100))) * 100) >= goal) {
                    return new int[]{i, j};
                }
            }
        }
        for(int i = 0; i < length; i++) {
            if (percentages[i] >= goal) {
                return new int[]{i};
            }
            for (int j = 0; j < length; j++) {
                if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100))) * 100) >= goal) {
                    return new int[]{i, j};
                }
                for (int k = 0; k < length; k++) {
                    if (((1 - (1 - (percentages[i] / 100)) * (1 - (percentages[j] / 100)) * (1 - (percentages[k] / 100))) * 100) >= goal) {
                        return new int[]{i, j, k};
                    }
                }
            }
        }
        for(int i = 0; i < length; i++) {
            if (percentages[i] >= goal) {
                return new int[]{i};
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
                    }
                }
            }
        }
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