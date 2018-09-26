package indestructibles.pe.ofertongo.Entities;

public class CardPromotionsBussines {

    String nombre;
    String descripcion;
    Float precio;
    String image;

    public CardPromotionsBussines(String nombre, String descripcion, Float precio, String image) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public CardPromotionsBussines setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public CardPromotionsBussines setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Float getPrecio() {
        return precio;
    }

    public String getPrecioString() {
        return precio+"";
    }

    public CardPromotionsBussines setPrecio(Float precio) {
        this.precio = precio;
        return this;
    }

    public String getImage() {
        return image;
    }

    public CardPromotionsBussines setImage(String image) {
        this.image = image;
        return this;
    }
}
