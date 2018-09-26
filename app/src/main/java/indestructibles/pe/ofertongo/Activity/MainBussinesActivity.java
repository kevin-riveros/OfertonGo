package indestructibles.pe.ofertongo.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import indestructibles.pe.ofertongo.Fragments.FavoriteFragment;
import indestructibles.pe.ofertongo.Fragments.MapsBussinesFragment;
import indestructibles.pe.ofertongo.Fragments.MapsFragment;
import indestructibles.pe.ofertongo.Fragments.ProductBussinesFragment;
import indestructibles.pe.ofertongo.Fragments.SettingsBussinesFragment;
import indestructibles.pe.ofertongo.Fragments.SettingsFragment;
import indestructibles.pe.ofertongo.R;

public class MainBussinesActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main_bussines);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bussines);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateTo(navigation.getMenu().findItem(R.id.navigation_maps));

    }
    private Fragment getFragmentFor(MenuItem item){
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return new ProductBussinesFragment() ;
            case R.id.navigation_maps:
                return new MapsBussinesFragment();
            case R.id.navigation_settings:
                return new SettingsBussinesFragment();
        }
        return new MapsBussinesFragment();

    }
    private boolean navigateTo(MenuItem item){

        item.setChecked(true);
        return getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,getFragmentFor(item)).commit()>0;

    }

}
