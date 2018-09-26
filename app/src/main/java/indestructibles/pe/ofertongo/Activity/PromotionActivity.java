package indestructibles.pe.ofertongo.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import indestructibles.pe.ofertongo.Adapters.RecyclerViewAdapterPromotions;
import indestructibles.pe.ofertongo.Entities.CardPromotionsBussines;
import indestructibles.pe.ofertongo.Entities.Products;
import indestructibles.pe.ofertongo.Entities.Promotions;
import indestructibles.pe.ofertongo.Entities.PromotionsProducts;
import indestructibles.pe.ofertongo.R;

public class PromotionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapterPromotions adapter;
    private Toolbar toolbar;
    String TAG="OFERTON GO:";
    List<PromotionsProducts>promos_products;
    List<Products >productos;
    List<Promotions>promos;
    List<CardPromotionsBussines>cardPromos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);


        Bundle b = new Bundle();
        b = getIntent().getExtras();
        promos_products=new ArrayList<>();
        promos=new ArrayList<>();
        productos=new ArrayList<>();


        toolbar=findViewById(R.id.toolbar_promotions);
        setSupportActionBar(toolbar);

        //poner el nombre de la tienda

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        String idStore=Integer.toString(b.getInt("idStore"));

        Toast.makeText(getApplicationContext(),
                "Informacion detallada de la oferta Nº "+idStore ,
                Toast.LENGTH_SHORT).show();

        AndroidNetworking.get("http://ofertongo.gear.host/api/Stores/{store_id}/PromotionProducts")
                .addPathParameter("store_id", idStore)
                /*.addQueryParameter("limit", "3")*/
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(PromotionsProducts.class, new ParsedRequestListener<List<PromotionsProducts>>() {




                    @Override
                    public void onResponse(List<PromotionsProducts> promotionsProducts) {

                        Log.d(TAG, "promotionsProducts List size : " + promotionsProducts.size());
                        for (int i=0;i<promotionsProducts.size();i++) {

                            promos_products.add(promotionsProducts.get(i));
                            Log.d(TAG, " : " + promotionsProducts.get(i).getId_promotion_product());
                            Log.d(TAG, " : " + promotionsProducts.get(i).getId_product());
                            Log.d(TAG, " : " + promotionsProducts.get(i).getId_promotion());
                            Log.d(TAG, " : " + promotionsProducts.get(i).getId_store());

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "error en el api" );
                        // handle error
                    }
                });

       AndroidNetworking.get("http://ofertongo.gear.host/api/Stores/{store_id}/Promotions")
                .addPathParameter("store_id", idStore)
                /*.addQueryParameter("limit", "3")*/
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(Promotions.class, new ParsedRequestListener<List<Promotions>>() {

                    @Override
                    public void onResponse(List<Promotions> promotions) {
                        Log.d(TAG, "promotions List size : " + promotions.size());
                        for (int i=0;i<promotions.size();i++) {

                            promos.add(promotions.get(i));
                            Log.d(TAG, " : " + promotions.get(i).getId_promotion());
                            Log.d(TAG, " : " + promotions.get(i).getIs_active());
                            Log.d(TAG, " : " + promotions.get(i).getDescription());
                            Log.d(TAG, " : " + promotions.get(i).getId_store());

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "error en el api" );
                        // handle error
                    }
                });
       AndroidNetworking.get("http://ofertongo.gear.host/api/Stores/{store_id}/Products")
                .addPathParameter("store_id", idStore)
                /*.addQueryParameter("limit", "3")*/
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(Products.class, new ParsedRequestListener<List<Products>>() {

                    @Override
                    public void onResponse(List<Products> products) {
                        Log.d(TAG, "promotions List size : " + products.size());
                        for (int i=0;i<products.size();i++) {

                            productos.add(products.get(i));
                            Log.d(TAG, " : " + products.get(i).getName());
                            Log.d(TAG, " : " + products.get(i).getImage_url());
                            Log.d(TAG, " : " + products.get(i).getPrice());
                            Log.d(TAG, " : " + products.get(i).getId_store());
                            Log.d(TAG, " : " + products.get(i).getId_product());

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "error en el api" );
                        // handle error
                    }
                });
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                cardPromos=new ArrayList<>();

                Log.e("tamaño ",promos_products.size()+"");
        for(int i=0;i<promos_products.size();i++){

            cardPromos.add(new CardPromotionsBussines(productos.get(i).getName(),promos.get(i).getDescription(),productos.get(i).getPrice(),productos.get(i).getImage_url()));

        }


                recyclerView = findViewById(R.id.recycler_promotions);
                adapter=new RecyclerViewAdapterPromotions(getApplicationContext(),cardPromos);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
                recyclerView.setAdapter(adapter);

            }
        }, 2000);
        FloatingActionButton boton= findViewById(R.id.fab);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "agregar nuevo producto" ,
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==  android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
