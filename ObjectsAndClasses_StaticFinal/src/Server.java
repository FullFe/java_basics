import Components.*;

public class Server {
    public static void main(String[] args) {
        Computer compA = new Computer("DNS","UltraMonster",
                new CPU(2.0, 2, 0.05, CPU.INTEL),
                new RAM(typeRAM.DDR, 1, 0.02),
                new ROM(256, ROM.HDD, 0.03),
                new Display(24.2, typeMatrix.IPS, 2.5),
                new Keyboard(Keyboard.Mechanical, true, 0.6));
        System.out.println(compA);

        compA.setCpu(2.3,4,0.02, CPU.AMD);
        System.out.println(compA);

        Computer compB = new Computer("HyperPC","ChadPC",
                new CPU(3.5, 8, 0.02, CPU.INTEL),
                new RAM(typeRAM.DDR3, 8, 0.02),
                new ROM(1024, ROM.SSD, 0.03),
                new Display(27.2, typeMatrix.OLED, 1.5),
                new Keyboard(Keyboard.Membrane, true, 0.6));

        System.out.println(compB);
        System.out.println("Вес компьютера B: " + compB.getWeight());
    }

}
