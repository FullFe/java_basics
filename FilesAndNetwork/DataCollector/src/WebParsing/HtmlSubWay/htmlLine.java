package WebParsing.HtmlSubWay;

public record htmlLine(String number, String name) {

    @Override
    public String toString() {
        return this.number() + "\t"
                + this.name();
    }

}
