package Components;

public class CPU {
    private final double herz;
    private final int coreAmount;
    private final String vendor ;
    private final double weight;

    public static final String INTEL = "INTEL";
    public static final String AMD = "AMD";


    public CPU(double herz, int coreAmount, double weight, String vendor ) {
        this.herz = herz;
        this.coreAmount = coreAmount;
        this.weight = weight;
        this.vendor  = vendor ;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "herz=" + herz +
                ", coreAmount=" + coreAmount +
                ", vendor='" + vendor + '\'' +
                ", weight=" + weight +
                '}';
    }
}
