package indestructibles.pe.ofertongo.Entities;

public class PromotionsProducts {

    int id_promotion_product ;
    int id_promotion;
    int id_product;
    int id_store;

    public PromotionsProducts(int id_promotion_product, int id_promotion, int id_product, int id_store) {
        this.id_promotion_product = id_promotion_product;
        this.id_promotion = id_promotion;
        this.id_product = id_product;
        this.id_store = id_store;
    }


    public int getId_promotion_product() {
        return id_promotion_product;
    }

    public PromotionsProducts setId_promotion_product(int id_promotion_product) {
        this.id_promotion_product = id_promotion_product;
        return this;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public PromotionsProducts setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
        return this;
    }

    public int getId_product() {
        return id_product;
    }

    public PromotionsProducts setId_product(int id_product) {
        this.id_product = id_product;
        return this;
    }

    public int getId_store() {
        return id_store;
    }

    public PromotionsProducts setId_store(int id_store) {
        this.id_store = id_store;
        return this;
    }
}
