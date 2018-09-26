package indestructibles.pe.ofertongo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import indestructibles.pe.ofertongo.Fragments.FavoriteFragment;
import indestructibles.pe.ofertongo.Fragments.MapsFragment;
import indestructibles.pe.ofertongo.Fragments.SettingsFragment;
import indestructibles.pe.ofertongo.R;

public class MainActivity extends AppCompatActivity {

        private Toolbar toolbar;
    private TextView titulo;
    Bundle userBundle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateTo(item);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        titulo=findViewById(R.id.titulo_toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateTo(navigation.getMenu().findItem(R.id.navigation_maps));
        userBundle= getIntent().getExtras();
        //this.setTitle(userBundle.getString("name").toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.homeAsUp) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Fragment getFragmentFor(MenuItem item){
        switch (item.getItemId()) {
            case R.id.navigation_favorite:
                getSupportActionBar().setTitle("");
                titulo.setText("Favorite");
                return new FavoriteFragment() ;
            case R.id.navigation_maps:
                titulo.setText("Oferton GO");
                return new MapsFragment();
            case R.id.navigation_settings:
                titulo.setText("Settings");
                return new SettingsFragment();
        }
        return new MapsFragment();

    }
    private boolean navigateTo(MenuItem item){

        item.setChecked(true);
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,getFragmentFor(item)).commit()>0;

    }
}
