/*
Класс для размеров груза
 */
public class Dimensions {
    private final double width; //mm
    private final double height; //mm
    private final double length; //mm

    public Dimensions(int width, int height, int length) {
        if(width <= 0 || height <= 0 || length <= 0) {
            this.width = 10;
            this.height = 10;
            this.length = 10;
            System.out.println("Dimensions not valid, Standard set 10x10x10");
        }else {
            this.width = width;
            this.height = height;
            this.length = length;
        }


    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double calculateVolume(){
       return this.getWidth() * this.getHeight() * this.getLength();
    }

    @Override
    public String toString() {
        return  "width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", volume=" + this.calculateVolume();
    }
}
