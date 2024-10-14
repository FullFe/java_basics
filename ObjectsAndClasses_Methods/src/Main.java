public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Yogurt", 50,4);
        basket.add("Apples", 15,3,1.17);
        basket.add("Banana", 25,2,0.42);
        basket.add("Shotgun", 450000,1,5);
        basket.print("Корзинка тети Томары");


        Arithmetic test = new Arithmetic(52,41);
        System.out.println("\nСумма: " + test.sum());
        System.out.println("Произведение: "+test.mul());
        System.out.println("Максимальное число: "+test.max());
        System.out.println("Минимальное число: "+test.min() + "\n");

        Arithmetic test2 = new Arithmetic(52,52);
        System.out.println(test2.max());
    }
}
