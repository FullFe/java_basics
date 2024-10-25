package Components;

public class ROM {
    public static final String SSD = "SSD";
    public static final String HDD = "HDD";
    public final int volume;
    public final String type;
    public final double weight;

    public ROM(int volume, String type, double weight) {
        this.volume = volume;
        this.type = type;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "ROM{" +
                "volume=" + volume +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }
}
