package indestructibles.pe.ofertongo.Entities;

import com.orm.SugarRecord;

import java.util.List;

public class Bussines extends SugarRecord{

    private String name;
    private String RUC;
    private String password;
    private String email;
    private List<Products> productos;

    public List<Products> getProductos() {
        return productos;
    }

    public Bussines setProductos(List<Products> productos) {
        this.productos = productos;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public String getEmail() {
        return email;
    }

    public Bussines setEmail(String email) {
        this.email = email;
        return this;
    }

    public Bussines() {
    }


}
