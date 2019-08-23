package com.example.allergy;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    final ArrayList<StoreInfo> storeInfos = new ArrayList<>();
    final ArrayList<Marker> markers = new ArrayList<>();
    final ArrayList<String> foodKeyList=new ArrayList<>();

//    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item);


    //플러팅 액션바1 (아래 3줄)
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab_check, fab_save;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NaverMapSdk.getInstance(this).setClient(new NaverMapSdk.NaverCloudPlatformClient("amqmjryw28"));
        setContentView(R.layout.activity_main);





        ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
           }



        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {

            mapFragment = mapFragment.newInstance(new NaverMapOptions().camera(new CameraPosition(
                    new LatLng(37.5913103 , 127.02213119999999), NaverMap.DEFAULT_CAMERA_POSITION.zoom,20,45)));
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
        //locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        //플러팅 액션바 여기부터2
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_check = (FloatingActionButton) findViewById(R.id.fab_check);
        fab_save = (FloatingActionButton) findViewById(R.id.fab_save);

        fab.setOnClickListener(this);
        fab_check.setOnClickListener(this);
        fab_save.setOnClickListener(this);
        //플러팅 액션바 여기까지2

        //성북구 음식점 위도 경도
        String file ="foodstore.json";
        String result="";

        final Geocoder geocoder = new Geocoder(this);

        try{
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            result = new String(buffer, "utf-8");

            JSONObject json = new JSONObject(result);
            JSONObject json1 =(JSONObject) json.get("SbModelRestaurantDesignate");
            JSONArray jarr=json1.getJSONArray("row");
            List<Address> list = null;

            for(int i = 0; i<jarr.length(); i++){
                json1=jarr.getJSONObject(i);
                String name = json1.getString("UPSO_NM");
                String addres_rd = json1.getString("SITE_ADDR_RD");
                String food = json1.getString("MAIN_EDF");
                JSONObject foodObject = new JSONObject(food);
                Iterator iterator = foodObject.keys();
                while(iterator.hasNext()){
                    String b = iterator.next().toString();
                    Log.d("food",b);
                    foodKeyList.add(b);
                }
                list = geocoder.getFromLocationName(addres_rd,10);

                double latitude = list.get(0).getLatitude();
                double longitude = list.get(0).getLongitude();

                storeInfos.add(i,new StoreInfo(name,addres_rd,latitude,longitude));


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
//        for (int j = 0; j < storeInfos.size(); j++) {
//            info.add(new InfoWindow());
//            info.get(j).setAdapter(new InfoWindow.DefaultTextAdapter(context) {
//                @NonNull
//                @Override
//                public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                    int k = 0;
//                    return storeInfos.get(k++).getName();
//                }
//            });
//        }

//        final InfoWindow infoWindow = new InfoWindow();
//        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
//            @NonNull
//            @Override
//            public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                return (CharSequence)infoWindow.getMarker().getTag();
//            }
//        });
//
//        final int k=0;

        for (int i = 0; i < storeInfos.size(); i++) {

            markers.add(new Marker());
            markers.get(i).setPosition(new LatLng(storeInfos.get(i).getLatitude(), storeInfos.get(i).getLongitude()));
            markers.get(i).setMap(naverMap);
            markers.get(i).setIcon(MarkerIcons.BLACK);
            markers.get(i).setTag(storeInfos.get(i).getName());
            final String storeName = storeInfos.get(i).getName();

            markers.get(i).setOnClickListener(new Overlay.OnClickListener() {
                @Override
                public boolean onClick(@NonNull Overlay overlay) {
                    final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_item);
//                    String foodName = null;
                    for(int h =0; h<foodKeyList.size();h++){
                        adapter.add(foodKeyList.get(h));
                    }
                    alert.setTitle(storeName);
//                    alert.setMessage();

                    alert.setAdapter(adapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = adapter.getItem(which);
//                            AlertDialog.Builder innBuilder = new AlertDialog.Builder(MainActivity.this);
                        }
                    });
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"확인",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.show();
                    return true;
                }


            });


        }

    }
//    public void showDialog(){
//        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//
//        alert.setTitle("가게이름");
//        alert.setMessage("메뉴정보");
//        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this,"메뉴를 고르세요",Toast.LENGTH_SHORT).show();
//            }
//        });
//        alert.setNegativeButton("이동안함", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                Toast.makeText(MainActivity.this,"메뉴를 고르세요",Toast.LENGTH_SHORT).show();
//            }
//        });
//        alert.show();
//    }


    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    //플러팅 액션바3 onClick메소드, anim메소드
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();

                break;
            case R.id.fab_check:
                anim();
                Intent intent1 = new Intent(this, PopupActivity.class);
                intent1.putExtra("data", "Test Popup");
                startActivity(intent1);
                break;
            case R.id.fab_save:
                anim();
                Intent intent2 = new Intent(getApplicationContext(), MeatStorageActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void anim(){
        if(isFabOpen){
            fab_check.startAnimation(fab_close);
            fab_save.startAnimation(fab_close);
            fab_check.setClickable(false);
            fab_save.setClickable(false);
            isFabOpen = false;
        }else{
            Toast.makeText(this, "원하는 메뉴를 선택하세요.", Toast.LENGTH_SHORT).show();
            fab_check.startAnimation(fab_open);
            fab_save.startAnimation(fab_open);
            fab_check.setClickable(true);
            fab_save.setClickable(true);
            isFabOpen = true;

        }
    }

//    public String foodName(){
//        for(int i =0; i<foodKeyList.size();i++) {
//           return foodKeyList.get(i)+" ";
//        }
//        return null;
//    }

}
