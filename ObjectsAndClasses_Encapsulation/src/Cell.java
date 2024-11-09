/*
Класс ячейки груза
 */
public record Cell(Dimensions dimensions, double mass, String deliveryAddress, boolean rotatable, String regNumber,
                   boolean fragile) {

    public Cell setDimensions(Dimensions dimensions) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Cell setMass(double mass) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Cell setDeliveryAddress(String deliveryAddress) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Cell setRotatable(boolean rotatable) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Cell setRegNumber(String regNumber) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Cell setFragile(boolean fragile) {
        return new Cell(dimensions, mass, deliveryAddress, rotatable, regNumber, fragile);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "\ndimensions=" + dimensions +
                ", \nmass=" + mass +
                ", \ndeliveryAddress='" + deliveryAddress + '\'' +
                ", \nrotatable=" + rotatable +
                ", \nregNumber='" + regNumber + '\'' +
                ", \nfragile=" + fragile +
                ", \nHash of object=" + this.getClass().getName() + "@"+Integer.toHexString(this.hashCode()) + // Добавлен для проверки, что объект новый, это дефолтный toString()
                "}\n";
    }
}
