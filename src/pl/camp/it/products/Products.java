package pl.camp.it.products;

public class Products {
    String category;
    String name;
    String price;
    String date;
    String kod;
    String time;

    public Products(String category, String name, String price, String kod, String date, String time){
        this.category = category;
        this.name = name;
        this.price = price;
        this.kod =kod;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Products{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", kod='" + kod + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
