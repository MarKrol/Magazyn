package pl.camp.it.sort;

import pl.camp.it.database.DataBase;
import pl.camp.it.products.DataTime;
import pl.camp.it.products.Products;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class SortByDate implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int date=0;
        if (o1 instanceof Products && o2 instanceof Products) {
            Products p1 = (Products) o1;
            Products p2 = (Products) o2;

            try {
                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(p1.getDate());
                Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(p2.getDate());

                date = date1.compareTo(date2);

            } catch (ParseException e) {
                System.out.println("Błąd obsługi formatu daty!!! " + e.getMessage());
            }
        }
        return date;
    }

}
