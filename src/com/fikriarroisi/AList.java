package com.fikriarroisi;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author fikriarroisi
 */
public class AList {

    private Timer timer;
    private Timer timerBreak;
    private ArrayList<Task> tasks;
    private Scanner input;
    private boolean done;
    private int num = 0;
    private File file;
    private Sound sound;
    private boolean error;
    private boolean almost;
    private String breakTime = "no";
    private int currentTask;
    private int taskMax;

    public AList() throws IOException, URISyntaxException {
        input = new Scanner(System.in);
        this.sound = new Sound();
        tasks = new ArrayList<Task>();

        menu();
    }

    public void input() {
        do {
            General.clear();
            General.printHead();
            if (General.inputError(error)) {
                error = false;
            }
            System.out.println("==========|Add New|==========");
            System.out.print(" Task Name\t:");
            String name = General.inputString();
            if (name.trim().equals("")) {
                name = "New Task";
            }
            System.out.print(" Task Time\t:");
            String time = "";
            int second = 0;
            time = input.next();
            second = General.getSecond(time);
            if (second < 0) {
                error = true;
                continue;
            } else {
                Task task = new Task(name, time);
                tasks.add(task);
                menu();
            }
        } while (error);
    }

    public void inputBreak() {
        do {
            General.clear();
            General.printHead();
            if (General.inputError(error)) {
                error = false;
            }
            System.out.println("==========|Add Break Time|==========");
            System.out.print(" Break Time\t:");
            String time = "";
            int second = 0;
            time = input.next();
            second = General.getSecond(time);
            if (second < 0) {
                error = true;
                continue;
            } else {
                breakTime = time;
                menu();
            }
        } while (error);
    }

    public void menu() {
        do {
            General.clear();
            General.printHead();
            if (General.error("Input 1 or 2 or 3  or 0!", error)) {
                error = false;
            }
            added();
            System.out.println("===========|AList|===========");
            System.out.println(" 1. Start");
            System.out.println(" 2. Add new Task");
            System.out.println(" 3. Add break time");
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
                        input();
                        break;
                    case 3:
                        inputBreak();
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
        if (tasks.size() == 0) {
            input();
        } else {
            General.clear();
            General.printHead();
            System.out.println("===========|Acodo|===========");
            System.out.println(" Task list :");
            this.listingTask();
            System.out.println("=============================");
            startTimer(tasks.get(num));
        }
    }

    public void startTimer(Task task) {
        almost = false;
        timer = new Timer();
        sound.changePlay();
        currentTask++;
        System.out.println("\n------|" + task.name + " ("+this.currentTask +"/"+ this.taskMax+")|-------");
        timer.scheduleAtFixedRate(new TimerTask() {
            int max = General.getSecond(task.max);
            int counter = max;

            @Override
            public void run() {
                General.printProgress(task.name, counter, max);
                if (counter <= 10 && almost == false) {
                    sound.almostPlay();
                    almost = true;
                }
                counter--;
                if (counter < 0) {
                    timer.cancel();
                    counter = max;
                    sound.almostStop();
                    if (currentTask < tasks.size()) {
                        num++;
                        if (!breakTime.equals("no")) {
                            startBreak();
                        } else {
                            startTimer(tasks.get(num));
                        }
                    } else {
                        sound.donePlay();
                        System.out.println("\n------|DONE|------");
                        System.out.print("\r Repeat (Y/N)? ");
                        String ans = input.next();
                        sound.doneStop();
                        num = 0;
                        if (ans.toLowerCase().charAt(0) == 'y') {
                            currentTask = 0;
                            start();
                        } else {
                            tasks.clear();
                            currentTask = 0;
                            breakTime = "no";
                            menu();
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    public void startBreak() {
        almost = false;
        timerBreak = new Timer();
        sound.changePlay();
        System.out.println("\n------|Break Time|-------");
        timerBreak.scheduleAtFixedRate(new TimerTask() {
            int counter = General.getSecond(breakTime);
            int max = General.getSecond(breakTime);
            int time = General.getSecond(breakTime);

            @Override
            public void run() {
                General.printProgress("Break", counter, max);
                if (counter <= 10 && almost == false) {
                    sound.almostPlay();
                    almost = true;
                }
                counter--;
                if (counter < 0) {
                    timerBreak.cancel();
                    counter = time;
                    sound.almostStop();
                    startTimer(tasks.get(num));
                }
            }
        }, 0, 1000);
    }

    public void added() {
        int n = tasks.size();
        if (n <= 0) {
            System.out.println("===========|Added|===========");
            System.out.println(" 0 added tasks");
            System.out.println("=============================");
        } else {
            System.out.println("===========|Added|===========");
            this.listingTask();
            System.out.println("=============================");
        }
    }

    public void listingTask() {
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).name + "\t" + General.showAdded(tasks.get(i).max));
        }
        if (breakTime.equals("no")) {
            System.out.println("\n No break time .__.");
        } else {
            System.out.println(" -. Break time\t" + General.showAdded(this.breakTime));
        }
        this.taskMax = n;
    }
}
