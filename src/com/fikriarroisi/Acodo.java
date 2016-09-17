package com.fikriarroisi;

import java.util.Scanner;

/**
 *
 * @author fikriarroisi
 */
public class Acodo {

    private static Scanner input;
    private static boolean error;

    public Acodo() {
        menu();
    }

    public static void main(String[] args) {
        input = new Scanner(System.in);
        menu();
    }

    public static void menu() {
        do {
            General.clear();
            General.printHead();
            if(General.error("Input 1 or 2 or 3 or 0 !", error)){
                error = false;
            }
            System.out.println("===========|Acodo|===========");
            System.out.println(" 1. list Countdown");
            System.out.println(" 2. Toggle Countdown");
            System.out.println(" 3. About");
            System.out.println(" 0. Exit Program");
            System.out.println("=============================");
            System.out.print(" Menu number: ");
            int number = 0;
            try {
                number = input.nextInt();
                switch (number) {
                    case 1:
                        AList list = new AList();
                        break;
                    case 2:
                        AToggle toggle = new AToggle();
                        break;
                    case 3:
                        about();
                        break;
                    case 0:
                        System.out.println("=============================");
                        System.exit(0);
                        break;
                    default:
                        error = true;
                }
            } catch (Exception e) {
                error = true;
                input.next();
            }
        } while (error);
    }

    public static void about() {
        General.clear();
        General.printHead();
        System.out.print("========================================|About|======================================\n"
                + " ACoDo (A Count Down), is a count down timer program, there are two mode for counting :\n"
                + "   1. List countdown   : you specify your task and time as much as you want.\n"
                + "   2. Toggle countdown : you specify just 2 tasks and number of loop for looping\n"
                + "                         that 2 tasks.\n"
                + "\n How to?\n"
                + " - This is how to input a value or time:\n"
                + "     - If you want to input duration in hours, minutes, and seconds:\n"
                + "          - you can just input any positive integer, its read as seconds \n"
                + "            automatically eg. \"121\" (without quotation) for 121 seconds.\n"
                + "          - Example input for 3 minutes \"3/0\" (without quotation).\n"
                + "          - Example input for 4 hours \"4/0/0\" (without quotation).\n"
                + "          - Example input for 2 minutes and 10 seconds \"2/10\" (without quotation).\n"
                + "          - Example input for 11 hours, 14 minutes, and 13 seconds \"11/14/13\" \n"
                + "            (without quotation).\n"
                + "     - If you want to input in specific time:\n"
                + "          - \"23:21:13\" (without quotation) for 23:21:13 using 24 hour format.\n"
                + "          - \"23:21\" (without quotation) for 23:21:00 using 24 hour format.\n"
                + "          - \"23:\" (without quotation) for 23:00:00 using 24 hour format.\n"
                + " - This is little trick for toggle countdown:\n"
                + "     - when you want the countdown to looping FOREVER, input -1 (negative one),\n"
                + "       but remember you have to force close the program to stop it (usually CTRL + C).\n"
                + " - This is how to change the alert sounds:\n"
                + "     - You can change the alert sounds by replacing the .mp3 files in the\n"
                + "      \"Sounds\" directory (located in the same directory as the source file or the .jar file).\n"
                + "     - Make sure you replace it with mp3 file.\n"
                + "     - file named change.mp3 used when the task starting or changing.\n"
                + "       file named almost.mp3 used when the value or time less than\n"
                + "       10 or 10 seconds.\n"
                + "       file named done.mp3 used when all task done.\n"
                + "     - make sure you replace it with the SAME EXACT name! and place the \"Sounds\" directory\n"
                + "       in the same directory as the source file or the .jar file to make it work.\n"
                + " \n Made with <3 by Fikri Arroisi, http://fikriarroisi.com\n"
                + " Version : 160916\n"
                + " Sound Source:\n"
                + "   - task change : http://soundbible.com/1252-Bleep.html\n"
                + "   - task under 10 seconds remaining : http://soundbible.com/535-Alarm-Clock.html\n"
                + "   - All tasks done : http://soundbible.com/1656-House-Fire-Alarm.html\n"
                + " \n If you find any bug, please contact me by sending email to \n"
                + " fikri@fikriarroisi.com , because i'm not sure if this program is bug free,\n"
                + " i have test this program in many cases, but still i'm not sure :D\n"
                + "========================================================================================\n"
                + " Go back(Y/N)? ");
        char enter = input.next().charAt(0);
        if (enter == 'y' || enter == 'Y') {
            menu();
        }else{
            System.out.println("=============================");
            System.exit(0);
        }
    }

}
