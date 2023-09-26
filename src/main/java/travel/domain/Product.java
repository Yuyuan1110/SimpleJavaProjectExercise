package travel.domain;

public class Product {
    private int id;
    private String brand;
    private String product_name;
    private int price;

    public Product() {
    }

    public Product(int id, String brand, String product_name, int price) {
        this.id = id;
        this.brand = brand;
        this.product_name = product_name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                '}';
    }
}
