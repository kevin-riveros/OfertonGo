package indestructibles.pe.ofertongo.LoginActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import indestructibles.pe.ofertongo.Activity.MainActivity;
import indestructibles.pe.ofertongo.Activity.MainBussinesActivity;
import indestructibles.pe.ofertongo.Entities.Bussines;
import indestructibles.pe.ofertongo.Entities.Usuario;
import indestructibles.pe.ofertongo.Network.OfertonGoApi;
import indestructibles.pe.ofertongo.R;

public class LoginBussinesActivity extends AppCompatActivity {

    EditText EditRUC;
    EditText Editpassword;
    Button botonLogin;
    Button NewUser;
    TextView ChangeUser;
    String TAG="OFERTON GO:";
    boolean Succesful=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bussines);
        EditRUC=findViewById(R.id.editText_Name);
        Editpassword=findViewById(R.id.editText_Password);
        botonLogin =findViewById(R.id.button_login);
        NewUser=findViewById(R.id.button_newUser);
        ChangeUser=findViewById(R.id.changeUser);

        botonLogin.setOnClickListener(new View.OnClickListener() {
            String name;
            String pass;
            @Override
            public void onClick(View view) {

                name=EditRUC.getText().toString();
                pass=Editpassword.getText().toString();
                AndroidNetworking.get("http://ofertongo.gear.host/api/Businesses")
                        /*.addQueryParameter("limit", "3")*/
                        .setTag(this)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsObjectList(Bussines.class, new ParsedRequestListener<List<Bussines>>() {




                            @Override
                            public void onResponse(List<Bussines> bussines) {
                                Log.d(TAG, "Bussines list size : " + bussines.size());
                                for (int i=0;i<bussines.size();i++) {
                                    Log.d(TAG, "ruc : " + bussines.get(i).getRUC());
                                    Log.d(TAG, "name : " + bussines.get(i).getName());
                                    Log.d(TAG, "pass : " + bussines.get(i).getPassword());
                                    Log.d(TAG, "email : " + bussines.get(i).getEmail());

                                    if(name.equals(bussines.get(i).getEmail())&&
                                            pass.equals(bussines.get(i).getPassword())){
                                        Succesful=true;
                                        Intent intent=new Intent(LoginBussinesActivity.this, MainBussinesActivity.class);

                                        startActivity(intent);
                                        finish();
                                        return;

                                    }

                                }
                                if(Succesful==false){
                                    Editpassword.setText("");
                                    Toast.makeText(getApplicationContext(),
                                            "Nombre y Password incorrectos",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.e(TAG, "error en el api" );
                                // handle error
                            }
                        });





            }
        });


        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent=new Intent(LoginUserActivity.this, SignUpBussinesActivity.class);
                startActivity(intent);*/
                Toast.makeText(getApplicationContext(), "Registrar Empresa, sin activity", Toast.LENGTH_SHORT).show();
            }
        });
        ChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginBussinesActivity.this, LoginUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
