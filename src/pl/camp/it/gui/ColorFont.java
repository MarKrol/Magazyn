package pl.camp.it.gui;

import java.util.Scanner;

public class ColorFont  {

    private static void printListColor(){
        System.out.println("*************************** KOLORY TEKSTU ******************************");
        System.out.println("1. BIAŁY");
        System.out.println("2. ZIELONY");
        System.out.println("3. ŻÓŁTY");
        System.out.println("4. NIEBIESKI");
        System.out.println("5. CZERWONY");
        System.out.println("6. CYAN");
        System.out.println("************************************************************************");
    }

    public static void selectColorFont(){
        printListColor();

        System.out.print("\nWprowadź indeks wybranego koloru: ");

        switch(new Scanner(System.in).nextLine()){
            case "1":
                System.out.print("\033[0;37m");
                break;
            case "2":
                System.out.print("\033[0;32m");
                break;
            case "3":
                System.out.print("\033[0;33m");
                break;
            case "4":
                System.out.print("\033[0;34m");
                break;
            case "5":
                System.out.print("\033[0;31m");
                break;
            case "6":
                System.out.print("\033[0;36m");
                break;
            default:
                System.out.println("\nNie dokonałeś poprawnego wyboru !!!");
                break;
        }
    }

}
