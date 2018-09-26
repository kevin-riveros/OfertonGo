package indestructibles.pe.ofertongo.Entities;

import android.os.Bundle;

import com.orm.SugarRecord;

public class Usuario extends SugarRecord{
    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario() {

    }

    public Usuario(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putString("email", getEmail());
        bundle.putString("password", getPassword());
        return bundle;
    }


}
