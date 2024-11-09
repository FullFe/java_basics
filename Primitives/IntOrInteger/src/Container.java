public class Container {
    private Integer count = 0; // Так как это объект изначально там null, несмотря на объявление переменной класса

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }
}
