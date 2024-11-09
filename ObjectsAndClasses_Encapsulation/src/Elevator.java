public class Elevator {
    private int currentFloor = 1;
    private final int minFloor;
    private final int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    private int getCurrentFloor() {
        return currentFloor;
    }

    private void moveDown() {
        if(!(currentFloor == minFloor))
            currentFloor--;
    }
    private void moveUp() {
        if(!(currentFloor == maxFloor))
            currentFloor++;
    }

    public void move(int floor) {
        if (floor == this.getCurrentFloor()) {
            System.out.println("Ur already on this floor");
        }
        if (floor > maxFloor || floor < minFloor) {
            System.out.println("Invalid floor");
        } else {
            while (floor != this.getCurrentFloor()) {
                if (floor > this.getCurrentFloor()) {
                    this.moveUp();
                    System.out.println(this.getCurrentFloor());
                } else {
                    this.moveDown();
                    System.out.println(this.getCurrentFloor());
                }
            }
        }
    }
}
