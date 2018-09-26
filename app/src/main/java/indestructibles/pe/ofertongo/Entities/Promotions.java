package indestructibles.pe.ofertongo.Entities;

public class Promotions {

int id_promotion;
int is_active;
String description;
int id_store;


    public Promotions(int id_promotion, int is_active, String description, int id_store) {
        this.id_promotion = id_promotion;
        this.is_active = is_active;
        this.description = description;
        this.id_store = id_store;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public Promotions setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
        return this;
    }

    public int getIs_active() {
        return is_active;
    }

    public Promotions setIs_active(int is_active) {
        this.is_active = is_active;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Promotions setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getId_store() {
        return id_store;
    }

    public Promotions setId_store(int id_store) {
        this.id_store = id_store;
        return this;
    }
}
