package pl.camp.it.login;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.gui.Gui;

import java.io.*;
import java.util.Scanner;

public class Login {

    private Login() {

    }

    private static final Login login = new Login();

    private final String bean = "7d20c198b96a82e";

    private final String loginPassRegex = "^.+$";

    public static Login getLogin() {
        return login;
    }

    //Menu Logowaania
    private void printMenuLogin() {
        System.out.println("\n*************************** MENU LOGOWANIA **************************");
        System.out.println("1. Rejestracja użytkownika");
        System.out.println("2. Logowanie do programu");
        System.out.println("3. Usuwanie użytkownika z rejestru");
        System.out.println("4. Wyjście z programu");
        System.out.println("*********************************************************************");

        System.out.print("\nPodaj numer wybranej opcji: ");
    }

    //******************************************************************************//
    //********************************Metoda wyświetlająca Menu
    public void showMenu() {
        boolean exit = false;

        do {
            printMenuLogin();
            switch (new Scanner(System.in).nextLine()) {
                case "1":
                    registryLoginPasswd();
                    break;
                case "2":
                    loginToDataBase();
                    break;
                case "3":
                    deleteUserFromRegistry();
                    break;
                case "4":
                    System.out.println("Zakończono działanie programu!!!");
                    exit = true;
                    break;
                default:
                    System.out.println("Niepoprawny wybór!!! Spróbuj jeszcze raz!!!");
                    break;
            }
        } while (!exit);
    }

    // Zapis hasła i loginu dp pliku
    private void writeToFile(String login, String pass) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter
                (".\\src\\pl\\camp\\it\\login\\log.txt"))) {

            bufferedWriter.write(DigestUtils.md5Hex(login));
            bufferedWriter.newLine();
            bufferedWriter.write(DigestUtils.md5Hex(pass));

        } catch (IOException exc) {
            System.out.println("Błąd obsługi pliku!!! " + exc.getMessage());
        }
    }

    //Obsługa rejestracji  - Login
    private String registryLogin() {
        String login = "";
        System.out.print("Podaj login: ");
        login = new Scanner(System.in).next();
        while (!login.matches(loginPassRegex)) {
            System.out.println("\nLogin niezgodny w wzorcem!!! Spróbuj jeszcze raz!!!");
            System.out.print("Podaj login: ");
            login = new Scanner(System.in).next();
        }
        return login;
    }

    //Obsługa rejestracji - pass1
    private String registryPass1() {
        String passwd = "";
        System.out.print("Podaj hasło: ");
        passwd = new Scanner(System.in).next();
        while (!passwd.matches(loginPassRegex)) {
            System.out.println("\nHasło niezgodne w wzorcem!!! Spróbuj jeszcze raz!!!");
            System.out.print("Podaj hasło: ");
            passwd = new Scanner(System.in).next();
        }
        return passwd;
    }

    //Obsługa rejestracji - pass2
    private boolean registryPass2(String pass1) {
        boolean equal= true;
        int  counter = 0;
        String passwd2 = "";
        System.out.print("Powtórz hasło: ");
        passwd2 = new Scanner(System.in).next();

        while (!passwd2.equals(pass1)) {
            ++counter;
            System.out.println("\nHasło niezgodne!!! Spróbuj jeszcze raz!!!");
            if (counter ==3){
                equal = false;
                System.out.println("Dokonano zbyt dużej liczby prób!!! Nastąpi powrót do mneu logowania!!!");
                break;
            }
            System.out.print("Powtórz hasło: ");
            passwd2 = new Scanner(System.in).next();
        }
        return equal;
    }

    //obsługa rejetracja hasło passwd razem
    private void registryLoginPasswd(){
        if ((readFromFileLogin()==null) && (readFromFilePassWd()==null)) {
            String login = registryLogin();
            String passwd = registryPass1();
            if (registryPass2(passwd)) {
                writeToFile(login, passwd);
                System.out.println("Zarejestrowano użytkownika w bazie!!!");
            }
        } else {
            System.out.println("W bazie jest już zarejstrowany użytkownik!!! Rejestracja nie jest możliwa!!!");
        }
    }

    //odczyt z pliku loginu
    private String readFromFileLogin() {
        String login="";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                                                            ((".\\src\\pl\\camp\\it\\login\\log.txt")))) {

           login = bufferedReader.readLine();

        } catch (IOException exc) {
            System.out.println("Błąd obsługi pliku!!! " + exc.getMessage());
        }
        return login;
    }

    //odczyt z pliku hasła
    private String readFromFilePassWd() {
        String passwd="";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ((".\\src\\pl\\camp\\it\\login\\log.txt")))) {

            bufferedReader.readLine();
            passwd = bufferedReader.readLine();

        } catch (IOException exc) {
            System.out.println("Błąd obsługi pliku!!! " + exc.getMessage());
        }
        return passwd;
    }

    //Logowanie do bazy
    private void loginToDataBase() {
        String login = readFromFileLogin();
        String passwd = readFromFilePassWd();
        if (login!=null && passwd!=null) {

            try {

                System.out.print("\nPodaj login: ");
                if (DigestUtils.md5Hex(new Scanner(System.in).nextLine()).equals(login)) {
                    System.out.print("\nPodaj hasło: ");
                    if (DigestUtils.md5Hex(new Scanner(System.in).nextLine()).equals(passwd)) {
                        Gui.getGui().showMenu();
                    } else {
                        System.out.print("Wprowadzono niepoprawne hasło!!!\n");
                    }
                } else {
                    System.out.print("Wprowadzono niepoprawny login!!!\n");
                }
            } catch (NullPointerException exc) {
                System.out.println("Brak zarejestrowanych użytkowników!!! " + exc.getMessage());
            }
        } else {
            System.out.println("Brak zarejestrowanych użytkowników!!!");
        }
    }

    //usunięcie użytkownika z rejestru
    private void deleteUserFromRegistry(){
        String login = readFromFileLogin();
        String passwd = readFromFilePassWd();
        if (login!=null && passwd!=null) {

            try {

                System.out.print("\nPodaj login: ");
                if (DigestUtils.md5Hex(new Scanner(System.in).nextLine()).equals(login)) {
                    System.out.print("\nPodaj hasło: ");
                    if (DigestUtils.md5Hex(new Scanner(System.in).nextLine()).equals(passwd)) {
                        clearFileLogin();
                    } else {
                        System.out.print("Wprowadzono niepoprawne hasło!!!\n");
                    }
                } else {
                    System.out.print("Wprowadzono niepoprawny login!!!\n");
                }
            } catch (NullPointerException exc) {
                System.out.println("Brak zarejestrowanych użytkowników!!! " + exc.getMessage());
            }
        } else {
            System.out.println("Brak zarejestrowanych użytkowników!!!");
        }
    }

    //metoda czyszcząca plik login
    private void clearFileLogin(){
        try  {
           File file = new File(".\\src\\pl\\camp\\it\\login\\log.txt");
           file.delete();
           file.createNewFile();

           FileWriter writer = new FileWriter(file);
           writer.close();

           System.out.println("Użytkownik został usunięty!!! ");

        } catch (Exception exc) {
            System.out.println("Błąd obsługi pliku!!! " + exc.getMessage());
        }
    }

    public void changeDataToLogin(){
        System.out.println("Wprowadź nowe dane do logowania.");
        String login = registryLogin();
        String passwd = registryPass1();
        if (registryPass2(passwd)) {
            writeToFile(login, passwd);
            System.out.println("Zmieniono dane użytkownika!!!");
        } else{
            System.out.println("Zmiana danych  użytkownika nie nastąpiła!!!");
        }
    }

}
