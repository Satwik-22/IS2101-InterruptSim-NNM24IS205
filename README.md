# IS2101: Implementing Interrupt Controller Simulater using Java

This repository contains a program which simulates the behavior of an **Interrupt Controller** managing multiple I/O devices with **priorities** and **masking**. The simulation demonstrates how interrupts are triggered, handled by an **Interrupt Service Routine (ISR)**, and optionally ignored if the device is masked.

The goal is to understand how **priority, masking, and asynchronous ISR** handls work in a real computer system, using real-world programming logic and event simulation.
***

## 📂 Repository Structure

This project contains source code file in the root directory and corresponding output screenshots in the `Outputs` directory.

```
IS2101-InterruptSim-NNM24IS205/
│
├── InterruptController.java
│
├──Outputs/
│   ├── 1)Interrupt message execution.png
│   ├── 2)log of ISR execution.png
│   └── 3)End of simulation.png
│
└── README.md
```
***

## ⚙️ Compilation & Execution

A software like `JDK` is required to compile and run the program and the following commands can be used in the terminal.

  * **Compile:**
    ```bash
    javac InterruptController.java
    ```
  * **Execute:**
    ```bash
    java InterruptController
    ```
***

## ⚡ Features

**1) Automatic Interrupt Simulation**
Devices generate interrupts at random intervals with configurable delays.

**2) Priority Handling**
The system always handles the highest-priority interrupt available first: `Keyboard > Mouse > Printer`.

**3)Device Masking and Unmasking**
Specific devices can be temporarily disabled (masked) or re-enabled (unmasked) at runtime via the command menu.

**4) Interrupt Logging with Timestamps**
A record of all handled interrupts is maintained in memory and can be displayed on demand.

**5) User-Controlled Execution**
Can exit the program at user command rather than abrupt stop.
***

## 📥 Inputs
The program accepts commands via a command menu that can be accessed while the simulation is running.
To open the command menu, **type `c` and press Enter** 

Once inside the menu, you can enter the following commands:
1) `mask <device_name>`	which Temporarily disables interrupts from the specified device.
Example, `mask mouse` will prevent mouse interrupts from being handled until unmasked.
2) `unmask <device_name>`	Re-enables interrupts for the specified device if previously masked.
Example: unmask Mouse.
3) `log`	Displays a history of all handled interrupts with timestampsand and **Only interrupts that were not masked are included.**
4) `exit`	Stops the simulation and exits the program.
***

## ✅ Sample Outputs

Here are the output screenshots from running the program along with the input commands added to **mask/unmask** the i/o device, to get the **log** and to **exit** the program.

![Interrupt Controller Output](Outputs/1%29Interrupt%20message%20execution.png)
![Interrupt Controller Output](Outputs/2%29log%20of%20ISR%20execution.png)
![Interrupt Controller Output](Outputs/3%29End%20of%20simulation.png)
