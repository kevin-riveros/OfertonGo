package indestructibles.pe.ofertongo.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import indestructibles.pe.ofertongo.Activity.PromotionActivity;
import indestructibles.pe.ofertongo.Entities.Location;
import indestructibles.pe.ofertongo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsBussinesFragment extends Fragment implements OnMapReadyCallback,LocationListener {

    private static final LatLng PROMOCION1 = new LatLng(-12.1041971, -76.9637792);
    private static final LatLng PROMOCION2 = new LatLng(-12.1004289, -76.9711751);
    private static final LatLng PROMOCION3 = new LatLng(-12.1041971, -76.9645792);
    String TAG="OFERTON GO:";


    List<LatLng> PointsBussines = new ArrayList<>();
    private AlertDialog dialog;
    private GoogleMap gMap;
    private static final float zoom = 15;
    private LocationManager locationManager;
    private android.location.Location location;
    private static double latitud = 0;
    private double longitud = 0;

    private String latitudnew;
    private String longitudenew;
    public MapsBussinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps_bussines, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = gMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.mapstyle));

            if (!success) {
                Log.e("OFERTONGO-:", "Maps Fragment,Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("OFERTONGO-:", "Maps Fragment,Can't find style. Error: ", e);
        }

        //PointsBussines.add(PROMOCION1);
        //PointsBussines.add(PROMOCION2);
        //PointsBussines.add(PROMOCION3);
        //intarOfertas(PointsBussines);
        AndroidNetworking.get("http://ofertongo.gear.host/api/Stores/{store_id}/Locations")
                .addPathParameter("store_id", "2")
                /*.addQueryParameter("limit", "3")*/
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Location.class, new ParsedRequestListener<List<Location>>() {


                    @Override
                    public void onResponse(List<Location> BussinesPoints) {
                        Log.d(TAG, "ubicaciones List size : " + BussinesPoints.size());
                        for (int i=0;i<BussinesPoints.size();i++) {
                            Log.d(TAG, "id : " + BussinesPoints.get(i).getId_location());
                            Log.d(TAG, "idstore : " + BussinesPoints.get(i).getId_store());
                            Log.d(TAG, "lat : " + BussinesPoints.get(i).getLatitude());
                            Log.d(TAG, "long : " + BussinesPoints.get(i).getLongitude());

                        }
                        pintarBussinesPoints(BussinesPoints);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "error en el api" );
                        // handle error
                    }
                });

        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {



                Intent intent = new Intent(getContext(), PromotionActivity.class).putExtra("idStore",2);
                startActivity(intent);
                Toast.makeText(getContext(),
                        marker.getZIndex() +
                                " has been clicked ",
                        Toast.LENGTH_SHORT).show();


                return false;
            }


        });
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                final LatLng punto=latLng;
                latitudnew=""+punto.latitude;
                longitudenew=""+punto.longitude;

                AlertDialog.Builder addLocation=new AlertDialog.Builder(getContext());
                View ADview=getLayoutInflater().inflate(R.layout.add_item_location_bussines,null);
                EditText elati=ADview.findViewById(R.id.edit_latitude);
                EditText elongi=ADview.findViewById(R.id.edit_longitude);

                elati.setText(latitudnew);
                elongi.setText(longitudenew);
                Button agregar=ADview.findViewById(R.id.btn_add);
                Button cancelar=ADview.findViewById(R.id.btn_cancel);
                addLocation.setView(ADview);
                dialog=addLocation.create();
                dialog.show();

                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext()," agregando lat "+punto.latitude+" long "+punto.longitude,
                                Toast.LENGTH_SHORT).show();


                        AndroidNetworking.post("http://ofertongo.gear.host/api/Locations")

                                .addBodyParameter("latitude", latitudnew)
                                .addBodyParameter("longitude", longitudenew)
                                .addBodyParameter("id_store", "1")
                                .setTag("test")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsObject(Location.class, new ParsedRequestListener() {
                                    @Override
                                    public void onResponse(Object response) {

                                    }

                                    @Override
                                    public void onError(ANError anError) {

                                    }
                                });


                        PointsBussines.add(punto);
                        PintarOfertas(PointsBussines);
                        dialog.dismiss();


                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"cancelar ",
                                Toast.LENGTH_SHORT).show();

                    }
                });







            }
        });

        locationManager = (LocationManager) getActivity().getSystemService(getContext().LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            Log.d ("OFERTONGO-:", "No posee permisos de localizacion");
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION },1);
        }
        else{
            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if(location!=null) {

                latitud = location.getLatitude();
                longitud = location.getLongitude();
                Log.d ("OFERTONGO-:", "las coordenadas actuales son-> latitud:"+latitud+" longitud:"+longitud);
            }
            else{
                Log.e("OFERTONGO-:", "location is null");
                return;
            }

        }


        goToLocationZoom(latitud, longitud,zoom);
        UiSettings uiSettings = gMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

    }

    private void PintarOfertas(List<LatLng> PointsBussines) {
        for (int i = 0; i < PointsBussines.size(); i++) {

            MarkerOptions markerOptions;
            markerOptions= new MarkerOptions();

            markerOptions.position(PointsBussines.get(i)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_store_48));

            markerOptions.zIndex(i);
            gMap.addMarker(markerOptions);
        }

    }
    private void pintarBussinesPoints(List<Location> bussinesPoints) {

        for (int i = 0; i < bussinesPoints.size(); i++) {

            LatLng point=new LatLng(bussinesPoints.get(i).getLatitude(),
                    bussinesPoints.get(i).getLongitude());
            MarkerOptions markerOptions;
            markerOptions= new MarkerOptions();
            markerOptions.position(point).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_store_48));

            markerOptions.zIndex(i);
            gMap.addMarker(markerOptions);
        }
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng latLng=new LatLng(lat,lng);
        drawMarkerUser(latLng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        gMap.moveCamera(update);
    }

    private void drawMarkerUser(LatLng point) {
        // Creating an instance of MarkerOptions and define a icon

        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_bussines_place));
        // Setting latitude and longitude for the marker
        markerOptions.position(point);
        // Adding marker on the Google Map
        gMap.addMarker(markerOptions);
    }
}
