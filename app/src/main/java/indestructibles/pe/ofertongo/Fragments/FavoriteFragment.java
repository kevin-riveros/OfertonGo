package indestructibles.pe.ofertongo.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import indestructibles.pe.ofertongo.Adapters.RecyclerViewAdapterPromotions;
import indestructibles.pe.ofertongo.Entities.CardPromotionsBussines;
import indestructibles.pe.ofertongo.Entities.Products;
import indestructibles.pe.ofertongo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    List<CardPromotionsBussines> Promotions;
    RecyclerView recyclerView;
    RecyclerViewAdapterPromotions adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        Promotions=new ArrayList<>();
        Promotions.add(new CardPromotionsBussines( "promocion1","desxcripcion de la promo", (float) 10.1,"http://de10.com.mx/sites/default/files/styles/galeria_photo_760x508/public/2018/05/24/hamburguesas_galeria_1.jpg"));

        recyclerView = view.findViewById(R.id.recycler_favorites);
        adapter=new RecyclerViewAdapterPromotions(getContext() ,Promotions);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);






        return view;
    }

}
