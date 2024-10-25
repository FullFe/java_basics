import Components.*;

public class Computer {
    private final String vendor;
    private final String name;
    private CPU cpu;
    private RAM ram;
    private ROM rom;
    private Display display;
    private Keyboard keyboard;

    public Computer(String vendor, String name, CPU cpu, RAM ram, ROM rom, Display display, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.display = display;
        this.keyboard = keyboard;
    }

    public CPU getCpu() {
        return cpu;
    }
    public void setCpu(double herz, int amountCore, double weight, String vendor) {
        this.cpu = new CPU(herz, amountCore, weight, vendor);
    }


    public RAM getRam() {
        return ram;
    }

    public void setRam(typeRAM type, int volume, double weight) {
        this.ram =  new RAM(type, volume, weight);
    }

    public ROM getRom() {
        return rom;
    }

    public void setRom(int volume, String type, double weight) {
        this.rom = new ROM(volume, type, weight);
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(double diagonal, typeMatrix type, double weight) {
        this.display = new Display(diagonal, type, weight);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String type, boolean lightOn, double weight) {
        this.keyboard = new Keyboard(type, lightOn, weight);
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------------------------\n" +
                "Computer{\n" +
                "Vendor='" + vendor + '\'' +
                ", \nName='" + name + '\'' +
                ", \n" + cpu +
                ", \n" + ram +
                ", \n" + rom +
                ", \n" + display +
                ", \n" + keyboard +
                "}\n###################################################################";
    }

    public double getWeight(){
        double weight = 0;
        weight += cpu.getWeight();
        weight += ram.getWeight();
        weight += rom.getWeight();
        weight += display.getWeight();
        weight += keyboard.getWeight();

        return weight;
    }



}
