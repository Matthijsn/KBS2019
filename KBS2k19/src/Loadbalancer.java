public class Loadbalancer extends Component {
    public Loadbalancer(String naam, double beschikbaarheid, int kosten) {
        super(naam, "Loadbalancer", beschikbaarheid, kosten);
    }

    public Loadbalancer(int id, String naam, String type, double beschikbaarheid, int kosten) {
        super(id, naam, type, beschikbaarheid, kosten);
    }
}
