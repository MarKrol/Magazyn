package pl.camp.it.sort;

import pl.camp.it.products.Products;

import java.util.Comparator;

public class SortByName implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Products && o2 instanceof Products) {
            Products p1 = (Products) o1;
            Products p2 = (Products) o2;
            return (p1.getName().compareTo(p2.getName()));
        } else {
            return -1;
        }
    }

}
