public class DBServer extends Component {
    public DBServer(String naam, double beschikbaarheid, int kosten) {
        super(naam, "DBServer", beschikbaarheid, kosten);
    }
    public DBServer(int id, String naam, String type, double beschikbaarheid, int kosten) {
        super(id, naam, type, beschikbaarheid, kosten);
    }
}
