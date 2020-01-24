package pl.camp.it.sort;

import pl.camp.it.database.DataBase;

import java.util.Collections;
import java.util.Scanner;

public class SupportSortGui {

    private SupportSortGui(){

    }

    private static final SupportSortGui supporSortGui = new SupportSortGui();

    public static SupportSortGui getSupporSortGui() {
        return supporSortGui;
    }

    private static void printSort(){
        System.out.println("\n*************************** SORTOWANIE ******************************");
        System.out.println("1. Sortowanie według kategorii");
        System.out.println("2. Sortowanie według nazwy produktu");
        System.out.println("3. Sortowanie według ceny produktu");
        System.out.println("4. Sortowanie według kodu produktu");
        System.out.println("5. Sortowanie według daty dodania produktu");
        System.out.println("6. Sortowanie według godziny dodania produktu");
        System.out.println("*********************************************************************");
    }

    public  void selectSort(){
        printSort();

        System.out.print("\nWprowadź indeks dokonanego wyboru: ");

        switch(new Scanner(System.in).nextLine()){
            case "1":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByCategory());
                break;
            case "2":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByName());
                break;
            case "3":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByPrice());
                break;
            case "4":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByCode());
                break;
            case "5":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByDate());
                break;
            case "6":
                Collections.sort(DataBase.getDatabase().getProductsList(), new SortByTime());
                break;
            default:
                System.out.println("\nNie dokonałeś poprawnego wyboru !!!");
                break;
        }
    }


}
