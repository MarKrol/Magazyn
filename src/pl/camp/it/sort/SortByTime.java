package pl.camp.it.sort;

import pl.camp.it.products.Products;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class SortByTime implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        int time=0;
        if (o1 instanceof Products && o2 instanceof Products) {
            Products p1 = (Products) o1;
            Products p2 = (Products) o2;

            try {
                Date time1 = new SimpleDateFormat("HH:mm:ss").parse(p1.getTime());
                Date time2 = new SimpleDateFormat("HH:mm:ss").parse(p2.getTime());

                time = time1.compareTo(time2);

            } catch (ParseException e) {
                System.out.println("Błąd obsługi formatu daty!!! " + e.getMessage());
            }
        }
        return time;
    }


}
