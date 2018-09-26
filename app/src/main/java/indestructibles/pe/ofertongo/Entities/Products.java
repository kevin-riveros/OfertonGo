package indestructibles.pe.ofertongo.Entities;

public class Products {
    private int id_product;
    private int id_store;
    private String name;
    private Float price;
    private String image_url;

    public Products(int id_product, int id_store, String name, Float price, String image_url) {
        this.id_product = id_product;
        this.id_store = id_store;
        this.name = name;
        this.price = price;
        this.image_url = image_url;
    }

    public int getId_product() {
        return id_product;
    }

    public Products setId_product(int id_product) {
        this.id_product = id_product;
        return this;
    }

    public int getId_store() {
        return id_store;
    }

    public Products setId_store(int id_store) {
        this.id_store = id_store;
        return this;
    }

    public String getName() {
        return name;
    }

    public Products setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }
    public String getPriceString() {
        return price+"";
    }

    public Products setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Products setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
