package pl.camp.it.gui;

import pl.camp.it.database.DataBase;
import pl.camp.it.login.Login;
import pl.camp.it.sort.*;
import pl.camp.it.database.SupportDateBase;
import pl.camp.it.file.SupportFile;

import java.net.CookieHandler;
import java.util.Collections;
import java.util.Scanner;

public class Gui{

    private Gui() {
    }

    private static final Gui gui = new Gui();

    public static Gui getGui() {
        return gui;
    }

    //******************************************************************************//
    //********************************Metoda wypisująca Menu
    private void printMenu(){
        System.out.println("\n********************************* MENU *********************************");
        System.out.println("********************* WYŚWIETLANIE REKORDÓW Z BAZY *********************");
        System.out.println("1. Wyświetl listę produków z bazy");
        System.out.println("2. Wyświetl listę produków z bazy po podanej kategorii");
        System.out.println("3. Wyświetl listę kategorii z bazy");
        System.out.println("********************* DODAWANIE REKORDÓW DO BAZY ***********************");
        System.out.println("4. Dodaj produkt do bazy");
        System.out.println("5. Dodaj kategorię do bazy");
        System.out.println("*********************** USUWANIE REKORDÓW Z BAZY ***********************");
        System.out.println("6. Usuń produkt z bazy");
        System.out.println("7. Usuń kategorię z bazy");
        System.out.println("********************* WYSZUKIWANIE REKORDÓW W BAZIE ********************");
        System.out.println("8. Wyszukaj produkt po kodzie");
        System.out.println("9. Wyszukaj produkt po nazwie");
        System.out.println("10. Wyszukaj kategorię w bazie");
        System.out.println("********************************* OPCJE ********************************");
        System.out.println("11. Sortuj produkty w bazie");
        System.out.println("12. Zmień kolor wyświetlanego tekstu");
        System.out.println("13. Zmień dane do logowania");
        System.out.println("************************************************************************");
        System.out.println("14. Powrót do menu logowania");
        System.out.println("************************************************************************");

        System.out.print("\nPodaj numer wybranej opcji: ");
    }

    //******************************************************************************//
    //********************************Metoda wyświetlająca Menu
    public void showMenu(){
        boolean exit = false;

        SupportFile.getSupportFile().readFileToListPorducts();  //wczytanie z plik do listy produktów
        SupportFile.getSupportFile().readFileToCategoryList();  //wczytanie z pliku do listy kategorii

        do {
            printMenu();
            switch(new Scanner(System.in).nextLine()){
                case "1":
                    System.out.println("\nLista produktów znajdujących się w bazie:");
                    DataBase.getDatabase().printProducts();
                    break;
                case "2":
                    System.out.println("\nList produktów z danej kategorii:");
                    SupportDateBase.getSupportDateBase().supportShowListProductAccordingCategory();
                    break;
                case "3":
                    System.out.println("\nLista kategorii znajdujących się w bazie:");
                    DataBase.getDatabase().printCategory();
                    break;
                case "4":
                    SupportDateBase.getSupportDateBase().supportAddProduct();
                    break;
                case "5":
                    SupportDateBase.getSupportDateBase().supportAddCategory();
                    break;
                case "6":
                    SupportDateBase.getSupportDateBase().supportDeleteProduct();
                    break;
                case "7":
                    SupportDateBase.getSupportDateBase().supportDeleteCategory();
                    break;
                case "8":
                    SupportDateBase.getSupportDateBase().supportFindProductByCode();
                    break;
                case "9":
                    SupportDateBase.getSupportDateBase().supportFindProductByName();
                    break;
                case "10":
                    SupportDateBase.getSupportDateBase().supportFindCategory();
                    break;
                case "11":
                    SupportSortGui.getSupporSortGui().selectSort();
                    break;
                case "12":
                    ColorFont.selectColorFont();
                    break;
                case "13":
                    Login.getLogin().changeDataToLogin();
                    break;
                case "14":
                    SupportFile.getSupportFile().writeToFileAllProducts();  //Zapisanie do pliku produktów z listy
                    SupportFile.getSupportFile().writeToFileCategory();     //Zapisanie do pliku kategorii z listy
                    DataBase.getDatabase().getProductsList().clear();
                    DataBase.getDatabase().getCategoriesList().clear();
                    DataBase.getDatabase().getCategoriesList().add("NO CATEGORY");
                    System.out.println("Nastąpił powrót do menu logowania!!! ");
                    exit = true;
                    break;
                default:
                    System.out.println("Niepoprawny wybór!!! Spróbuj jeszcze raz!!!");
                    break;
            }
        }while (!exit);
    }

}
