public class AnotherMain {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Yogurt", 50,4);
        basket.add("Apples", 15,3,1.17);
        basket.add("Banana", 25,2,0.42);
        basket.add("Shotgun", 450000,1,5);
        basket.print("Корзинка тети Томары");

        System.out.println();
        Basket basket2 = new Basket();
        basket2.add("Milk", 40);
        basket2.add("Yogurt", 50,4);
        basket2.add("Apples", 15,3,1.17);
        basket2.add("Banana", 25,2,0.42);
        basket2.add("Grenade", 25000,2,0.6);
        basket2.print("Корзинка тети Виктории");

        System.out.println();
        System.out.println("Цена всех корзин: "+Basket.allBasketPrice);
        System.out.println("Количество единиц товаров в корзинах: "+Basket.allBasketUniCount);
        System.out.println("Всего товаров в корзинах: "+Basket.allBasketCount);

        System.out.println("Средняя цена товара: "+Basket.getAverageProductPrice());

        System.out.println("Средняя цена корзины: "+Basket.getAverageBasketPrice());
//        Arithmetic test = new Arithmetic(52,41);
//        System.out.println("\nСумма: " + test.sum());
//        System.out.println("Произведение: "+test.mul());
//        System.out.println("Максимальное число: "+test.max());
//        System.out.println("Минимальное число: "+test.min() + "\n");
//
//        Arithmetic test2 = new Arithmetic(52,52);
//        System.out.println(test2.max());
    }
}
