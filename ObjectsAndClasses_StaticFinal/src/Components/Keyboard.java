package Components;

public class Keyboard {
    public static final String Membrane = "Membrane";
    public static final String Mechanical = "Mechanical";

    private final String type;
    private final boolean lightOn;
    private final double weight;

    public Keyboard(String type, boolean lightOn, double weight) {
        this.type = type;
        this.lightOn = lightOn;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "type='" + type + '\'' +
                ", lightOn=" + lightOn +
                ", weight=" + weight +
                '}';
    }
}
