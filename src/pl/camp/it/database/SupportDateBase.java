package pl.camp.it.database;

import pl.camp.it.file.SupportFile;
import pl.camp.it.products.DataTime;
import pl.camp.it.products.Products;
import pl.camp.it.regex.Regex;

import java.time.Period;
import java.util.Iterator;
import java.util.Scanner;

public class SupportDateBase {

    private String categoryGlobal="";

    private SupportDateBase(){

    }

    private static final SupportDateBase supportDateBase = new SupportDateBase();

    public static SupportDateBase getSupportDateBase() {
        return supportDateBase;
    }

    public String getCategoryGlobal() { return categoryGlobal; }

    //*******************************************************************************************************//
    // *************************** Metoda obsługująca wprowadzanie danych do bazy ***************************//
    public String entry(String text, String regex){
        String data="";
        boolean entry = false;
        do{
            System.out.print("\n"+text);
            data = new Scanner(System.in).nextLine();
            if (data.matches(regex)){
                entry = true;
            } else {
                System.out.println("Nie wprowadzono poprawnie danych!! Spróbuj jeszcze raz!!!");
            }
        } while (!entry);
        return data;
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca sprawdzanie czy w bazie istnieje dana kategoria ********//
    public boolean supportIsCategory(){
        if (DataBase.getDatabase().isCategory
                (categoryGlobal=entry("Podaj nazwę kategorii: ", Regex.getRegex().getNameCategory()))){

            return true;
        } else{
            System.out.println("Kategoria o podanej nazwie nie istnieje w bazie!!! Dodaj kategorię do bazy!!!");
            return false;
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługując dodawanie do bazy kategorii ********//
    public void supportAddCategory(){
        String category="";
        if (DataBase.getDatabase().isCategory
                (category=entry("Podaj nazwę kategorii: ", Regex.getRegex().getNameCategory()))){
            System.out.println("Podana kategoria istnieje już w bazie!!! Wprowadź inną kategorię!!!");
        } else{
            DataBase.getDatabase().addCategory(category);
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługując dodawanie do bazy nowego produktu ********//
    public void supportAddProduct(){
        if (supportIsCategory()){
            DataBase.getDatabase().addProducts(new Products(categoryGlobal.toUpperCase(),
                    entry("Podaj nazwę produktu: ", Regex.getRegex().getNameRegex()),
                    entry("Podaj cenę produktu: ", Regex.getRegex().getPriceRegex()),
                    entry("Podaj kod produktu: ", Regex.getRegex().getKodRegex()),
                    DataTime.getDataTime().getDate(), DataTime.getDataTime().getTime()));
                    //SupportFile.getSupportFile().writeToFileOneProducts();
        } else{
            System.out.println("Produkt do bazy nie został dodany!!!!");
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca usuwanie kategorii z listy ********//
    public void supportDeleteCategory(){
        if (supportIsCategory()){
            for (Iterator<String> stringIterator = DataBase.getDatabase().getCategoriesList().iterator();
                                                                                        stringIterator.hasNext();){
                String s = stringIterator.next();
                if (!categoryGlobal.toUpperCase().equals("NO CATEGORY")) {
                    if (categoryGlobal.toUpperCase().equals(s)) {
                        stringIterator.remove();
                        System.out.println("Kategoria została usunięta z bazy!!!");
                        supportChangeCategoryToNoCategory();
                        break;
                    }
                } else {
                    System.out.println("NO CATEGORY jest kategorią, której nie można usunąć!!!");
                    break;
                }
            }
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca zmianę kategorii po usnięciu na NO CATEGORY w bazie produktów ********//
    private void supportChangeCategoryToNoCategory(){
        for (Products temp: DataBase.getDatabase().getProductsList()){
            if (temp.getCategory().equals(categoryGlobal.toUpperCase())){
                temp.setCategory("NO CATEGORY");
            }
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca uswanie produktów z listy produktów ********//
    public void supportDeleteProduct(){
        boolean delete=false;
        if (!DataBase.getDatabase().getProductsList().isEmpty()){

            System.out.print("\nPodaj nazwę produktu do usunięcia: ");
            String name=new Scanner(System.in).nextLine();
            while(!name.matches(Regex.getRegex().getNameRegex())){
                System.out.println("\nWprowadznono nazwę niezgodną z wzorcem!!!");
                System.out.print("\nPodaj nazwę produktu do usunięcia: ");
                name=new Scanner(System.in).nextLine();
            }

            for(Iterator<Products> productsIterator = DataBase.getDatabase().getProductsList().iterator()
                ;productsIterator.hasNext();){

                Products p = productsIterator.next();

                if (name.equals(p.getName())){
                    productsIterator.remove();
                    delete = true;
                }
            }
            if (delete){
                System.out.println("\nProdukt został usunięty z bazy!!!");
            } else{
                System.out.println("\nPodany produkt nie znajduje się w bazie!!!");
            }
        } else {
            System.out.println("Baza produktów jest pusta!!! Dodaj produkt do bazy!!!");
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca wyświetlanie produktów wg kategorii ********//
    public void supportShowListProductAccordingCategory(){
        boolean isproduct=false;
        if (!DataBase.getDatabase().getProductsList().isEmpty()){
            if (supportIsCategory()){
                for(Products temp: DataBase.getDatabase().getProductsList()){
                    if(categoryGlobal.toUpperCase().equals(temp.getCategory().toUpperCase())){
                        System.out.println(temp);
                        isproduct = true;
                    }
                }
                if(!isproduct){
                    System.out.println("W bazie nie ma produktów z podanej kategorii !!!!");
                }
            }
        } else{
            System.out.println("Baza produktów jest pusta!!! Dodaj produkt do bazy!!!");
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca wyszukiwanie kategorii ********//
    public void supportFindCategory(){
        if (supportIsCategory()){
            System.out.println("\nPodana kategoria znajduje się w bazie!!!");
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca wyszukiwanie produktu po kodzie ********//
    public void supportFindProductByCode(){
        boolean isproduct=false;
        if (!DataBase.getDatabase().getProductsList().isEmpty()){

            System.out.print("\nPodaj kod produktu: ");
            String kod=new Scanner(System.in).nextLine();
            while(!kod.matches(Regex.getRegex().getKodRegex())){
                System.out.println("\nWprowadznono kod niezgodny z wzorcem!!!");
                System.out.print("\nPodaj kod produktu: ");
                kod=new Scanner(System.in).nextLine();
            }

            for(Iterator<Products> productsIterator = DataBase.getDatabase().getProductsList().iterator()
                ;productsIterator.hasNext();){

                Products p = productsIterator.next();

                if (kod.equals(p.getKod())){
                    System.out.println(p.toString());
                    isproduct = true;
                }
            }
            if (!isproduct){
                System.out.println("\nProdukt o podanym kodzie nie znajduje się w bazie!!!");
            }

        } else{
            System.out.println("Baza produktów jest pusta!!! Dodaj produkt do bazy!!!");
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda obsługująca wyszukiwanie produktu po nazwie ********//
    public void supportFindProductByName(){
        boolean isproduct=false;
        if (!DataBase.getDatabase().getProductsList().isEmpty()){

            System.out.print("\nPodaj nazwę produktu: ");
            String name=new Scanner(System.in).nextLine();
            while(!name.matches(Regex.getRegex().getNameRegex())){
                System.out.println("\nWprowadznono nazwę niezgodną z wzorcem!!!");
                System.out.print("\nPodaj nazwę produktu: ");
                name=new Scanner(System.in).nextLine();
            }

            for(Iterator<Products> productsIterator = DataBase.getDatabase().getProductsList().iterator()
                ;productsIterator.hasNext();){

                Products p = productsIterator.next();

                if (name.equals(p.getName())){
                    System.out.println(p.toString());
                    isproduct = true;
                }
            }
            if (!isproduct){
                System.out.println("\nProdukt o podanej nazwie nie znajduje się w bazie!!!");
            }

        } else{
            System.out.println("Baza produktów jest pusta!!! Dodaj produkt do bazy!!!");
        }
    }

}
