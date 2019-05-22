public class Webserver extends Component {
    public Webserver(String naam, double beschikbaarheid, int kosten) {
        super(naam, "Webserver", beschikbaarheid, kosten);
    }
    public Webserver(int id, String naam, String type, double beschikbaarheid, int kosten) {
        super(id, naam, type, beschikbaarheid, kosten);
    }
}
