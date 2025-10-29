import java.util.*;
import java.text.SimpleDateFormat;

class InterruptController {

    static final int PRIORITY_KEYBOARD = 3;
    static final int PRIORITY_MOUSE = 2;
    static final int PRIORITY_PRINTER = 1;

    static boolean maskKeyboard = false;
    static boolean maskMouse = false;
    static boolean maskPrinter = false;

    static List<String> interruptLog = new ArrayList<>();
    static boolean running = true;

    static class DeviceSimulator extends Thread {
        Random rand = new Random();

        public void run() {
            while (running) {
                try {
                    Thread.sleep(8000 + rand.nextInt(4000)); // 8-12 seconds gap

                    int dev = 1 + rand.nextInt(3);
                    handleInterrupt(dev);

                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    synchronized static void handleInterrupt(int device) {
        String deviceName = "";
        boolean isMasked = false;

        switch (device) {
            case 3: deviceName = "Keyboard"; isMasked = maskKeyboard; break;
            case 2: deviceName = "Mouse"; isMasked = maskMouse; break;
            case 1: deviceName = "Printer"; isMasked = maskPrinter; break;
        }

        if (isMasked) {
            System.out.println(deviceName + " Interrupt Ignored (Masked)");
            return;
        }

        System.out.print(deviceName + " Interrupt Triggered -> Handling ISR");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(" -> Completed");

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        interruptLog.add("[" + time + "] " + deviceName + " handled");
    }

    static class UserInput extends Thread {
        Scanner sc = new Scanner(System.in);

        public void run() {
            while (running) {
                String input = sc.nextLine().trim();

                if (input.equalsIgnoreCase("c")) {
                    // Show command menu
                    System.out.println("\n--- Command Menu ---");
                    System.out.println("mask <device_name>");
                    System.out.println("unmask <device_name>");
                    System.out.println("log");
                    System.out.println("exit");
                    System.out.print("> ");

                    String cmd = sc.nextLine().trim();
                    handleCommand(cmd);
                    System.out.println();
                }
            }
            sc.close();
        }

        private void handleCommand(String cmdLine) {
            String[] parts = cmdLine.split("\\s+");
            if (parts.length == 0) return;

            String cmd = parts[0];

            if (cmd.equalsIgnoreCase("exit")) {
                running = false;
            } else if ((cmd.equalsIgnoreCase("mask") || cmd.equalsIgnoreCase("unmask")) && parts.length == 2) {
                String dev = parts[1];
                boolean maskValue = cmd.equalsIgnoreCase("mask");

                if (dev.equalsIgnoreCase("Keyboard")) maskKeyboard = maskValue;
                else if (dev.equalsIgnoreCase("Mouse")) maskMouse = maskValue;
                else if (dev.equalsIgnoreCase("Printer")) maskPrinter = maskValue;
                else {
                    System.out.println("Invalid device name!");
                    return;
                }
                System.out.println(dev + " is now " + (maskValue ? "masked (disabled)" : "unmasked (enabled)"));
            } else if (cmd.equalsIgnoreCase("log")) {
                System.out.println("\n------ Interrupt Log ------");
                if (interruptLog.isEmpty()) {
                    System.out.println("(No interrupts handled yet)");
                } else {
                    for (String entry : interruptLog) System.out.println(entry);
                }
                System.out.println("---------------------------");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=======  Interrupt Controller Simulation  =======");
        System.out.println("Devices: Keyboard (High), Mouse (Medium), Printer (Low)");
        System.out.println("Simulation running automatically.");
        System.out.println("Type 'c' and Enter to open command menu anytime.\n");

        DeviceSimulator deviceThread = new DeviceSimulator();
        UserInput userThread = new UserInput();

        deviceThread.start();
        userThread.start();

        try { userThread.join(); } catch (InterruptedException e) {}

        running = false;
        deviceThread.interrupt();

        System.out.println("\n***Simulation ended***");
    }
}
