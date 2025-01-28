package WebParsing.HtmlSubWay;

public record htmlStation(String stationName, String stationNumber) {

    @Override
    public String toString() {
        return this.stationName() + "\t"
                + this.stationNumber();
    }
}
