package indestructibles.pe.ofertongo.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;

import indestructibles.pe.ofertongo.Adapters.RecyclerViewAdapterProducts;
import indestructibles.pe.ofertongo.Adapters.RecyclerViewAdapterPromotions;
import indestructibles.pe.ofertongo.Entities.CardPromotionsBussines;
import indestructibles.pe.ofertongo.Entities.Products;
import indestructibles.pe.ofertongo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductBussinesFragment extends Fragment {
    List<Products> Products;
    RecyclerView recyclerView;
    RecyclerViewAdapterProducts adapter;
String TAG ="ofetron go";

    public ProductBussinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view=inflater.inflate(R.layout.fragment_product_bussines, container, false);


        AndroidNetworking.get("http://ofertongo.gear.host/api/Stores/{store_id}/Products")
                .addPathParameter("store_id", "2")
                /*.addQueryParameter("limit", "3")*/
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(Products.class, new ParsedRequestListener<List<Products>>() {

                    @Override
                    public void onResponse(List<Products> products) {
                        Log.d(TAG, "promotions List size : " + products.size());
                        Products=new ArrayList<>();
                        for (int i=0;i<products.size();i++) {

                            Products.add(products.get(i));
                            Log.d(TAG, " : " + products.get(i).getName());
                            Log.d(TAG, " : " + products.get(i).getImage_url());
                            Log.d(TAG, " : " + products.get(i).getPrice());
                            Log.d(TAG, " : " + products.get(i).getId_store());
                            Log.d(TAG, " : " + products.get(i).getId_product());

                        }
                        recyclerView = view.findViewById(R.id.recycler_products);
                        adapter=new RecyclerViewAdapterProducts(getContext() , Products);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "error en el api" );
                        // handle error
                    }
                });

        FloatingActionButton boton= view.findViewById(R.id.fab);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),
                        "agregar nuevo producto" ,
                        Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

}
