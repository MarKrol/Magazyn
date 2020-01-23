package pl.camp.it.regex;

public class Regex {

    private final String nameCategory = "^.+$";
    private final String nameRegex = "^.+$";
    private final String priceRegex = "^[0]{1}[.][0-9]{2}$|^[1-9]+[.][0-9]{2}$";
    private final String kodRegex = "^\\d{13}$";

    private final String productRegex = "^Products[{]{1}category='.+', name='.+', price='[0-9]+[.]{1}\\d{2}" +
            "', kod='\\d{13}', date='\\d{2}[-]{1}\\d{2}[-]{1}\\d{4}', time='\\d{2}[:]{1}\\d{2}[:]{1}\\d{2}['][}]$";

    private Regex(){

    }

    private static Regex regex = new Regex();

    public String getKodRegex() {
        return kodRegex;
    }

    public String getNameRegex() {
        return nameRegex;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public static Regex getRegex() {
        return regex;
    }

    public String getPriceRegex() {
        return priceRegex;
    }

    public String getProductRegex() {
        return productRegex;
    }
}
