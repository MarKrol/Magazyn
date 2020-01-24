package pl.camp.it.sort;

import pl.camp.it.products.Products;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class SortByCode implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Products && o2 instanceof Products) {
            Products p1 = (Products) o1;
            Products p2 = (Products) o2;

            if (Double.parseDouble(p1.getKod())-Double.parseDouble(p2.getKod())>0.00){
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

}
