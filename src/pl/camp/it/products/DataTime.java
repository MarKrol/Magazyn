package pl.camp.it.products;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataTime {

    private DataTime(){

    }

    private static final DataTime dataTime = new DataTime();

    public static DataTime getDataTime() {
        return dataTime;
    }

    //*************************** Metoda zwracająca data w momencie dodania produktu do bazy ******************//
    public String getDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTimeFormatter.format(LocalDate.now());
    }

    //*********************** Metoda zwracająca godzinę w momencie dodania produktu do bazy ****************//
    public String getTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTimeFormatter.format(LocalTime.now());
    }
}
