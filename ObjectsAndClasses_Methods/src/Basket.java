public class Basket {

    private static int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0; // grams

    private static int basketCount = 0; //Количество корзин

    public static int allBasketPrice = 0;
    public static int allBasketUniCount = 0; // Виды товаров
    public static int allBasketCount = 0; // Количестов единиц товаров
    /*
    Здесь не считается уникальность общая, то есть если в двух корзинах по позиции Apples, это будет два уникальных товара, а не один
     */
    public Basket() {
        items = "Список товаров:";
        this.limit = 1000000;
        basketCount++;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) { // Здесь неудобный коснтруктор, так как если задать список товаров, то неполучится зарегестрировать их кол-во
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
        increaseAllBasketPrice(allBasketPrice + totalPrice);
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count, int amount) {
        Basket.count = Basket.count + count;
        allBasketUniCount = allBasketUniCount + count;
        allBasketCount = allBasketCount + amount;
    }
    public static void increaseAllBasketPrice(int price){
        allBasketPrice = allBasketPrice + price;
    }



    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;

        increaseAllBasketPrice(count * price);

        increaseCount(1, count);
    }
    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        if (count > 1)
            totalWeight += weight * count;
        else
            totalWeight += weight;

        items = items + " - " + weight * count;
    }

   // private static void

    public void clear() {
        items = "";
        allBasketPrice = allBasketPrice - totalPrice;
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
            System.out.println("Общая масса товаров: "+this.getTotalWeight()+ " кг");
            System.out.println("Общая цена товаров: "+this.getTotalPrice() + " рублей");
        }

    }

    public static double getAverageProductPrice(){
        return (double) allBasketPrice / allBasketUniCount;
    }
    public static double getAverageBasketPrice(){
        return (double) allBasketPrice / basketCount;
    }

}
