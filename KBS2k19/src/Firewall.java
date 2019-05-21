public class Firewall extends Component {
    public Firewall(String naam, double beschikbaarheid, int kosten) {
        super(naam, "Firewall", beschikbaarheid, kosten);
    }
    public Firewall(int id, String naam, String type, double beschikbaarheid, int kosten) {
        super(id, naam, type, beschikbaarheid, kosten);
    }
}
