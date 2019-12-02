package com.example.tansaku;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Locale;

import android.widget.Toast;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng location;
    private Marker onMarkerClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // mMapは private GoogleMap mMap; で宣言しています。
        mMap.setMyLocationEnabled(true);


        // 皇居辺りの緯度経度
        location = new LatLng(35.68, 139.76);
        // marker 追加
        mMap.addMarker(new MarkerOptions().position(location).title("Tokyo"));

        //マーカー設定
        MarkerOptions options = new MarkerOptions();
        options.position(location);
        options.title("クラスメソッド株式会社");
        options.snippet(location.toString());

        // camera 移動
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));

        // タップした時のリスナーをセット
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng tapLocation) {
                // tapされた位置の緯度経度
                location = new LatLng(tapLocation.latitude, tapLocation.longitude);
                double a = tapLocation.latitude;
                double b = tapLocation.longitude;
                String str = String.format(Locale.US, "%f, %f", tapLocation.latitude, tapLocation.longitude);
                mMap.addMarker(new MarkerOptions().position(location).title(str));

                double text = a;
                double text2 = b;

                Toast toast = Toast.makeText(MapsActivity.this, String.format("double：%f", text)+String.format("double：%f", text2), Toast.LENGTH_LONG);
                toast.show();
                

            }
        });



        // 長押しのリスナーをセット
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng longpushLocation) {
                LatLng newlocation = new LatLng(longpushLocation.latitude, longpushLocation.longitude);
                mMap.addMarker(new MarkerOptions().position(newlocation).title(":" + longpushLocation.latitude + " :" + longpushLocation.longitude));
            }
        });

        //マーカーの削除
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                marker.remove();
                return true;
            }
        });

        //マーカーをセットできるよ
        final LatLng MELBOURNE = new LatLng(35.68, 139.76);
        Marker melbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .snippet("Population: 4,137,400"));
        melbourne.showInfoWindow();

    }
}

