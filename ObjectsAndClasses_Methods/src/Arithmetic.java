public class Arithmetic {

    private int firstNum;
    private int secondNum;

    public Arithmetic(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }


    public int sum() {
        return firstNum + secondNum;
    }
    public int mul() {
        return firstNum * secondNum;
    }
    public int max() {
        if(firstNum == secondNum){
            System.out.println("The numbers are equal");
        }
        return Math.max(firstNum, secondNum); // Метод в классе Math
    }
    public int min() {
        if(firstNum == secondNum){
            System.out.println("The numbers are equal");
        }
        return (firstNum <= secondNum) ? firstNum : secondNum; //Здесь тот же метод, но в виде тернарного оператора
    }

}
