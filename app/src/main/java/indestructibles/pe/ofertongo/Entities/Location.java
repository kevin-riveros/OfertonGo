package indestructibles.pe.ofertongo.Entities;

public class Location {
    private int  id_location;
    private float latitude;
    private float longitude;
    private int  id_store;

    public Location(int id_location, float latitude, float longitude, int id_store) {
        this.id_location = id_location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_store = id_store;
    }

    public int getId_location() {
        return id_location;
    }

    public Location setId_location(int id_location) {
        this.id_location = id_location;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Location setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Location setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getId_store() {
        return id_store;
    }

    public Location setId_store(int id_store) {
        this.id_store = id_store;
        return this;
    }
}
