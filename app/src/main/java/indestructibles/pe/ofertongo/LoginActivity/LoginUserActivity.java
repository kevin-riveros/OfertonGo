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
import indestructibles.pe.ofertongo.Entities.Usuario;
import indestructibles.pe.ofertongo.Network.OfertonGoApi;
import indestructibles.pe.ofertongo.R;

public class LoginUserActivity extends AppCompatActivity {

    EditText Editname;
    EditText Editpassword;
    Button botonLogin;
    Button NewUser;
    TextView ChangeUser;
    String TAG="OFERTON GO:";
    Bundle UserBundle;
    Usuario user;
    private boolean Succesful=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
        Editname=findViewById(R.id.editText_Name);
        Editpassword=findViewById(R.id.editText_Password);
        botonLogin =findViewById(R.id.button_login);
        NewUser=findViewById(R.id.button_newUser);
        ChangeUser=findViewById(R.id.changeUser);



        botonLogin.setOnClickListener(new View.OnClickListener() {
            String name;
            String pass;
            @Override
            public void onClick(View view) {

                name=Editname.getText().toString();
                pass=Editpassword.getText().toString();
                AndroidNetworking.get(  "http://ofertongo.gear.host/api/Users/"   )
                        /*.addQueryParameter("limit", "3")*/
                        .setTag(this)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsObjectList(Usuario.class, new ParsedRequestListener<List<Usuario>>() {


                            @Override
                            public void onResponse(List<Usuario> users) {

                                Log.d(TAG, "userList size : " + users.size());
                                for (int i=0;i<users.size();i++) {
                                    Log.d(TAG, "email : " + users.get(i).getEmail());
                                    Log.d(TAG, "name : " + users.get(i).getName());
                                    Log.d(TAG, "pass : " + users.get(i).getPassword());
                                    if(name.equals(users.get(i).getEmail())&&
                                            pass.equals(users.get(i).getPassword())){

                                        user=new Usuario(users.get(i).getName(),users.get(i).getEmail(),
                                                users.get(i).getPassword());
                                        UserBundle=user.toBundle();
                                        Intent intent=new Intent(LoginUserActivity.this, MainActivity.class)
                                        ;
                                        startActivity(intent);
                                        Succesful=true;
                                        return;

                                        //arreglar error
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
                Intent intent=new Intent(LoginUserActivity.this, SignUpActivity.class);
                startActivity(intent);*/
                Toast.makeText(getApplicationContext(), "Registrar usuario, sin activity", Toast.LENGTH_SHORT).show();
            }
        });
        ChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginUserActivity.this, LoginBussinesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
