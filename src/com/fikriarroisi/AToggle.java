package com.fikriarroisi;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author fikriarroisi
 */
public class AToggle {

    Scanner input;
    String oneTime;
    String twoTime;
    int loop;
    int currentLoop;
    String oneName = "";
    String twoName = "";
    private Sound sound;
    boolean error;
    private boolean almost;

    public AToggle() throws URISyntaxException, IOException {
        input = new Scanner(System.in);
        this.sound = new Sound();
        menu();
    }

    public void menu() {
        do {
            General.clear();
            General.printHead();
            if (General.error("Input 1 or 2 or 0 !", error)) {
                error = false;
            }
            this.showSet();
            System.out.println("==========|AToggle|==========");
            System.out.println(" 1. Start");
            System.out.println(" 2. Set");
            System.out.println(" 0. Back");
            System.out.println("=============================");
            System.out.print(" Menu number: ");
            int number = 0;
            try {
                number = input.nextInt();
                switch (number) {
                    case 1:
                        start();
                        break;
                    case 2:
                        set();
                        break;
                    case 0:
                        Acodo acodo = new Acodo();
                        break;
                    default:
                        error = true;
                }
            } catch (Exception e) {
                input.next();
                error = true;
            }

        } while (error);
    }

    public void start() {
        General.clear();
        General.printHead();
        this.showSet();
        currentLoop = 1;
        if (this.oneName.equals("")) {
            set();
        } else {
            start1();
        }
    }

    public void start1() {
        almost = false;
        Timer timer = new Timer();
        sound.changePlay();
        String display = "";
        if (loop == -1) {
            display = "FOREVER";
        } else {
            display = loop + "";
        }
        System.out.println("\n|Round Number: " + this.currentLoop + " / " + display + "|");
        System.out.println("\n------|" + this.oneName + "|-------");
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = General.getSecond(oneTime);
            int time = General.getSecond(oneTime);

            @Override
            public void run() {
                General.printProgress(oneName, counter, time);
                if (counter <= 10 && almost == false) {
                    sound.almostPlay();
                    almost = true;
                }
                counter--;
                if (counter < 0) {
                    sound.almostStop();
                    timer.cancel();
                    start2();
                }
            }
        }, 0, 1000);
    }

    public void start2() {
        almost = false;
        Timer timer = new Timer();
        sound.changePlay();
        System.out.println("\n------|" + this.twoName + "|-------");
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = General.getSecond(twoTime);
            int time = General.getSecond(twoTime);

            @Override
            public void run() {
                General.printProgress(twoName, counter, time);
                if (counter <= 10 && almost == false) {
                    sound.almostPlay();
                    almost = true;
                }
                counter--;
                if (counter < 0) {
                    sound.almostStop();
                    timer.cancel();
                    currentLoop++;
                    if (loop == -1) {
                        start1();
                    } else if (currentLoop <= loop) {
                        start1();
                    } else {
                        sound.donePlay();
                        System.out.println("\n------|DONE|------");
                        System.out.print("\r Repeat (Y/N)? ");
                        String ans = input.next();
                        sound.doneStop();
                        if (ans.toLowerCase().charAt(0) == 'y') {
                            start();
                        } else {
                            oneName = "";
                            menu();
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    public void set() {
        do {
            General.printHead();
            if (General.inputError(error)) {
                error = false;
            }
            System.out.println("============|Set|============");
            System.out.print(" State 1 Name\t:");
            this.oneName = General.inputString();
            if (this.oneName.trim().equals("")) {
                this.oneName = "State 1";
            }
            System.out.print(" State 1 Time\t:");
            String string1 = input.next();
            int time1 = General.getSecond(string1);
            if (time1 < 0) {
                error = true;
                continue;
            } else {
                this.oneTime = string1;
            }
            System.out.println("-----------------------------");
            System.out.print(" State 2 Name\t:");
            this.twoName = General.inputString();
            if (this.twoName.trim().equals("")) {
                this.twoName = "State 2";
            }
            System.out.print(" State 2 Time\t:");
            String string2 = input.next();
            int time2 = General.getSecond(string2);
            if (time2 < 0) {
                continue;
            } else {
                this.twoTime = string2;
            }
            System.out.println("");
            System.out.print(" Number of round (-1 for infinite loop)\t:");

            this.loop = input.nextInt();
            if (loop < -1) {
                error = true;
                continue;
            } else if (!error) {
                menu();
            }

        } while (error);
    }

    public void showSet() {
        if (this.oneName.equals(null) || this.oneName.equals("")) {
            System.out.println("============|Set|============");
            System.out.println(" Not setted");
            System.out.println("=============================");
        } else {
            System.out.println("============|Set|============");
            this.listingTask();
            System.out.println("=============================");
        }
    }

    public void listingTask() {
        System.out.println(" 1. " + this.oneName + "\t" + General.showAdded(this.oneTime));
        System.out.println(" 2. " + this.twoName + "\t" + General.showAdded(this.twoTime));
        if (loop == -1) {
            System.out.println(" Number of round : FOREVER");
        } else {
            System.out.println(" Number of round : " + this.loop);
        }
    }

}
