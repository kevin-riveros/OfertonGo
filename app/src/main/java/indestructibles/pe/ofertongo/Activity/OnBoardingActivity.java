package indestructibles.pe.ofertongo.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import indestructibles.pe.ofertongo.LoginActivity.LoginUserActivity;
import indestructibles.pe.ofertongo.R;

public class OnBoardingActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent intentLogin = new Intent(OnBoardingActivity.this,LoginUserActivity.class);
                OnBoardingActivity.this.startActivity(intentLogin);
                OnBoardingActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
