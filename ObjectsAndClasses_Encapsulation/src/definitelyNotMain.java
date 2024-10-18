import java.util.Scanner;

public class definitelyNotMain {
    public static void main(String[] args) {

        Cell test = new Cell(new Dimensions(25,25,25), 5.23, "Moscow, str 83", false, "134afwf511",true);
        System.out.println(test);
        Cell secondTest = test.setFragile(false);
        System.out.println(secondTest);
        Cell thirdTest = test.setFragile(true).setDeliveryAddress("St Petersburg, str 52");
        System.out.println(thirdTest);


        Elevator elevator = new Elevator(-3, 26);
        while (true) {
            System.out.print("Введите номер этажа: ");
            int floor = new Scanner(System.in).nextInt();
            elevator.move(floor);
        }

    }
}
