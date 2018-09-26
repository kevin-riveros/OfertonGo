package indestructibles.pe.ofertongo.Network;

public class OfertonGoApi {
    private static String  URLBASE="http://ofertongoapp.azurewebsites.net/api";

    public static String getBussines(){

        return URLBASE+"/Businesses";
    }
    public static String getUsers(){

        return URLBASE+"/Users";
    }

}
