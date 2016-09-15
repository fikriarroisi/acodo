package com.fikriarroisi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fikriarroisi
 */
public class General {

    static int nOut;

    public static int getSecondDuration(String input) {
        input = input.trim();
        int second = 0;
        int h = 0;
        int m = 0;
        int s = 0;
        String time = input;
        int n = time.split("/").length;
        try {
            if (n == 3) {
                h = Integer.parseInt(time.split("/")[0]);
                m = Integer.parseInt(time.split("/")[1]);
                s = Integer.parseInt(time.split("/")[2]);
            } else if (n == 2) {
                m = Integer.parseInt(time.split("/")[0]);
                s = Integer.parseInt(time.split("/")[1]);
            } else if (n == 1) {
                s = Integer.parseInt(time.split("/")[0]);
            } else {
                return -1;
            }
            second = h * 3600 + m * 60 + s;
        } catch (Exception e) {
            return -1;
        }
        return second;
    }

    public static int getSecondTime(String input) {
        input = input.trim();
        int second = 0;
        int hi = 0;
        int mi = 0;
        int si = 0;
        int hn = Integer.parseInt(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()));
        int mn = Integer.parseInt(new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()));
        int sn = Integer.parseInt(new SimpleDateFormat("ss").format(Calendar.getInstance().getTime()));
        String time = input;
        int n = time.split(":").length;
        try {
            if (n == 3) {
                hi = Integer.parseInt(time.split(":")[0]);
                mi = Integer.parseInt(time.split(":")[1]);
                si = Integer.parseInt(time.split(":")[2]);
            } else if (n == 2) {
                hi = Integer.parseInt(time.split(":")[0]);
                mi = Integer.parseInt(time.split(":")[1]);
            } else if (n == 1) {
                hi = Integer.parseInt(time.split(":")[0]);
            } else {
                return -1;
            }
            //for second
            if (si >= sn) {
                second += si - sn;
            } else {
                mi -= 1;
                second += (si + 60) - sn;
            }
            //for minute
            if (mi >= mn) {
                second += (mi - mn) * 60;
            } else {
                hi -= 1;
                second += ((mi + 60) - mn) * 60;
            }
            //for hour
            if (hi >= hn) {
                second += (hi - hn) * 3600;
            } else {
                second += ((hi + 24) - hn) * 3600;
            }
        } catch (Exception e) {
            return -1;
        }
        return second;
    }

    public static int getType(String input) {
        input = input.trim();
        int type = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                type = 1;
            } else if (input.charAt(i) == ':') {
                type = 2;
            }
        }
        return type;
    }

    public static int getSecond(String input) {
        input = input.trim();
        int second = 0;
        int type = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                type = 1;
            } else if (input.charAt(i) == ':') {
                type = 2;
            }
        }
        switch (type) {
            case 0:
                second = Integer.parseInt(input);
                break;
            case 1:
                second = getSecondDuration(input);
                break;
            case 2:
                second = getSecondTime(input);
                break;
            default:
                second = -1;
        }
        return second;
    }

    public static String showTime(int second) {
        String string = "";
        if (second >= 3600) {
            int h = second / 3600;
            int m = (second - h * 3600) / 60;
            int s = second - h * 3600 - m * 60;
            if ((s < 10) && (m < 10)) {
                string = h + " h : " + m + "  m : " + s + "  s";
            } else if (s < 10) {
                string = h + " h : " + m + " m : " + s + "  s";
            } else if (m < 10) {
                string = h + " h : " + m + "  m : " + s + " s";
            } else {
                string = h + " h : " + m + " m : " + s + " s";
            }
        } else if (second >= 60) {
            int m = second / 60;
            int s = second - m * 60;
            if ((s < 10) && (m < 10)) {
                string = m + "  m : " + s + "  s";
            } else if (s < 10) {
                string = m + " m : " + s + "  s";
            } else if (m < 10) {
                string = m + "  m : " + s + " s";
            } else {
                string = m + " m : " + s + " s";
            }
        } else if (second < 10) {
            string = second + "  s";
        } else {
            string = second + " s";
        }
        return string;
    }

    public static String showAdded(String second) {
        String string = "";
        second = second.trim();
        int type = General.getType(second);
        if (type == 2) {
            string = second;
        } else {
            string = showTime(General.getSecond(second));
        }
        return string;
    }

    public static String showPerc(int max, int counter) {
        String perc = "";
        int percd = counter * 100 / max;
        if (percd < 10) {
            perc = percd + "  %";
        } else if (percd < 100) {
            perc = percd + " %";
        } else {
            perc = percd + "%";
        }
        return perc;
    }

    public static void clear() {
        if (System.getProperty("os.name").toLowerCase().charAt(0) == 'l') {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else if (System.getProperty("os.name").toLowerCase().charAt(0) == 'w') {
            try {
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(General.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");

        }
    }

    public static boolean error(String ms, boolean error) {
        if (error) {
            System.out.println("==========|ERROR|============");
            System.out.println(ms);
            System.out.println("=============================");
            return true;
        }
        return false;
    }

    public static boolean inputError(boolean error) {
        return General.error(" Input data Correctly !\n"
                + " example :\n"
                + " 121 for 121 seconds.\n"
                + " 1/2/3 for 1 hours 2 minutes and 3 seconds.\n"
                + " 12/13 for 12 minutes and 13 seconds.\n"
                + " 23:15:16 for specified time (using 24 hour format).", error);
    }

    public static String inputString() {
        String string = null;
        Scanner input = new Scanner(System.in);
        string = input.nextLine();
        return string;
    }

    public static void printHead() {
//        Random randObj = new Random();
//        int rand = randObj.nextInt(4);
//        System.out.println("=============================");
//        System.out.println("||                         ||");
//        switch (rand) {
//            case 0:
//                System.out.println("||          -___-          ||");
//                break;
//            case 1:
//                System.out.println("||          .___.          ||");
//                break;
//            case 2:
//                System.out.println("||          o___o          ||");
//                break;
//            case 3:
//                System.out.println("||          0___0          ||");
//                break;
//        }
//        System.out.println("||                         ||");
    }

    public static void printProgress(String name, int counter, int max) {
        String out = "";
        out += "  " + counter + "\t[";
        double n = (counter * 30) / max;
        for (int i = 0; i < n; i++) {
            out += "=";
        }
        for (int a = 0; a < (30 - (int) n); a++) {
            out += " ";
        }
        if (counter > 0) {
            out += "] |" + General.showPerc(max, counter) + "| " + General.showTime(counter);
        } else {
            out += "] |" + General.showPerc(max, counter) + "| Finish, time: " + General.showTime(max)+"\n";
        }
        for (int i = 0; i <= nOut + 2; i++) {
            System.out.print("\b");
        }
        for (int i = 0; i <= nOut + 2; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i <= nOut + 2; i++) {
            System.out.print("\b");
        }
        System.out.print(out);
        nOut = out.length();
    }
}
