package pl.camp.it.database;

import pl.camp.it.file.SupportFile;
import pl.camp.it.products.Products;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataBase {

    private List<Products> productsList = new ArrayList<>();
    private List<String> categoriesList = new ArrayList<String>(){{add("NO CATEGORY");}};

    private DataBase(){

    }

    private static final DataBase database = new DataBase();

    public static DataBase getDatabase() {
        return database;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public List<String> getCategoriesList() {
        return categoriesList;
    }

    //*******************************************************************************************************//
    // ******* Metoda sprawdzająca czy w bazie istnieje dana kategoria i zwracjąca wartość logiczną ********//
    public boolean isCategory(String category){
        boolean isCategory = false;
        for (int i=0; i<categoriesList.size();i++) {
            if(category.toUpperCase().equals(categoriesList.get(i).toUpperCase())){
                isCategory=true;
                break;
            }
        }
        return isCategory;
    }

    //*******************************************************************************************************//
    // ******* Metoda dodająca do bazy nową kategorię ********//
    public void addCategory(String category){
        categoriesList.add(category.toUpperCase());
    }

    //*******************************************************************************************************//
    // ******* Metoda dodająca do bazy nowy produkt ********//
    public void addProducts(Products temp){
        DataBase.getDatabase().productsList.add(temp);
    }

    //*******************************************************************************************************//
    // ******* Metoda dodajaca produkty wczytane z bazy do listy produktów na starcie programu ********//
    public void addProductsReadFromFile(String line){
        DataBase.getDatabase().getProductsList().add(new Products(
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(line,0),
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(
                        line, SupportFile.getSupportFile().positionCursor()),
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(
                        line, SupportFile.getSupportFile().positionCursor()),
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(
                        line, SupportFile.getSupportFile().positionCursor()),
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(
                        line, SupportFile.getSupportFile().positionCursor()),
                SupportFile.getSupportFile().writeToListElementsProductReadFromFile(
                        line, SupportFile.getSupportFile().positionCursor())));
    }


    //*******************************************************************************************************//
    // ******* Metoda wypisująca listę kategorii ********//
    public void printCategory(){
        for(String temp: categoriesList){
            System.out.println(temp);
        }
    }

    //*******************************************************************************************************//
    // ******* Metoda wypisująca listę kategorii ********//
    public void printProducts(){
        for(Products temp: productsList){
            System.out.println(temp);
        }
    }

}
