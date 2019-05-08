public abstract class Component {
    private String naam;
    private String type;
    private double beschikbaarheid;
    private int kosten;
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
}
