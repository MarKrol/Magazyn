package pl.camp.it.file;

import pl.camp.it.database.DataBase;
import pl.camp.it.products.Products;
import pl.camp.it.regex.Regex;

import java.io.*;

public class SupportFile {

    private int positionCursorGlobal = 0;

    private SupportFile(){
    }

    private static final SupportFile supportFile = new SupportFile();

    public static SupportFile getSupportFile() {
        return supportFile;
    }

    // **************************************************************************************************************//
    // ************************ Całkowity zapis bazy produktów z listy do pliku przed wyjściem z programu **********//
    public void writeToFileAllProducts(){
        try(BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter
                                                        (".\\src\\pl\\camp\\it\\file\\warehouse.txt"))){

            for(int i = 0; i<DataBase.getDatabase().getProductsList().size(); i++ ) {
                bufferedWriter.write(DataBase.getDatabase().getProductsList().get(i).toString());
                bufferedWriter.newLine();
            }

        }catch (IOException exc){
            System.out.println("Bład obsługi pliku!!! "+exc.getMessage());
        }
    }

    // **************************************************************************************************************//
    // ************************ Dodawanie do pliku pojedynczo wprowadzonego porduktu **********//
    /*public void writeToFileOneProducts(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter
                                            (".\\src\\pl\\camp\\it\\file\\warehouse.txt",true))){
            bufferedWriter.newLine();
            bufferedWriter.write(DataBase.getDatabase().getProductsList().
                                                get(DataBase.getDatabase().getProductsList().size()-1).toString());

        }catch (IOException exc){
            System.out.println("Bład obsługi pliku!!! "+exc.getMessage());
        }
    }*/

    // **************************************************************************************************************//
    // ************************ Wczytanie z pliku produktów do bazy listy produktów **********//
    public void readFileToListPorducts(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader
                                                        (".\\src\\pl\\camp\\it\\file\\warehouse.txt"))){
            String line="";

            while ((line=bufferedReader.readLine())!=null) {
                if (line.matches(Regex.getRegex().getProductRegex())) {

                    DataBase.getDatabase().addProductsReadFromFile(line);

                }
            }

        }catch(IOException exc){
            System.out.println("Błąd obsługi pliku!!! "+exc.getMessage());
        }

    }

    // **************************************************************************************************************//
    // ************************ Metoda wczytująca poszczególne dane produktu do lisy  **********//

    public String writeToListElementsProductReadFromFile(String line, int positionCursor){
        String text="";
        for(int i=positionCursor; i<line.length(); i++){
            if ((line.charAt(i)=='\'')){
                ++i;
                do {
                    text+=line.charAt(i);
                } while(line.charAt(++i)!='\'');
                positionCursorGlobal=++i;
                break;
            }
        }
        return text;
    }

    public int positionCursor(){
        return positionCursorGlobal++;
    }

    // **************************************************************************************************************//
    // ************************ Zapisanie w pliku wszystkich kategorii z listy  **********//
    public void writeToFileCategory(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter
                                            (".\\src\\pl\\camp\\it\\file\\category.txt"))){

            for (int i=0; i<DataBase.getDatabase().getCategoriesList().size();i++){

                bufferedWriter.write(DataBase.getDatabase().getCategoriesList().get(i));
                bufferedWriter.newLine();
            }
        }catch(IOException exc){
            System.out.println("Błąd obsługi pliku!!! "+exc.getMessage());
        }

    }

    // **************************************************************************************************************//
    // ************************ Metoda wczytująca listę kategorii z pliku do bazy**********//
    public void readFileToCategoryList(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader
                                            (".\\src\\pl\\camp\\it\\file\\category.txt"))){

            String line="";

            while ((line=bufferedReader.readLine())!=null) {
                if (line.matches(Regex.getRegex().getNameCategory()) && (!line.equals("no category".toUpperCase()))) {

                    DataBase.getDatabase().addCategory(line);

                }
            }

        }catch(IOException exc){
            System.out.println("Błąd obsługi pliku!!! "+exc.getMessage());
        }
    }
}
