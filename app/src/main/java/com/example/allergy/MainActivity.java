package com.example.allergy;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Map;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;

    LatLng coord = new LatLng(37.570694 , 126.968870);
    //플러팅 액션바1 (아래 3줄)
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;

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
                    NaverMap.DEFAULT_CAMERA_POSITION.target, NaverMap.DEFAULT_CAMERA_POSITION.zoom,20,45)));
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        //locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        //플러팅 액션바 여기부터2
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        //플러팅 액션바 여기까지2

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //naverMap.setLocationSource(locationSource);

        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.570694 , 126.968870));
        marker.setMap(naverMap);
        marker.setIcon(MarkerIcons.BLACK);
    }


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
                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();  //나중에 지우던가 문구를 바꾸던가 해야함
                break;
            case R.id.fab1:
                anim();
                Intent intent1 = new Intent(this, PopupActivity.class);
                intent1.putExtra("data", "Test Popup");
                startActivity(intent1);
                Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show();  //나중에 지우던가 문구를 바꾸던가 해야함
//아래 두 줄은 저장된 거 확인하는 xml만들어지면 수정하기
//                Intent intent = new Intent(getApplicationContext(), MeatStorageActivity.class);
//                startActivity(intent);
                break;
            case R.id.fab2:
                anim();
                Toast.makeText(this, "MeatStorageActivity", Toast.LENGTH_SHORT).show();  //나중에 지우던가 문구를 바꾸던가 해야함
                Intent intent2 = new Intent(getApplicationContext(), MeatStorageActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void anim(){
        if(isFabOpen){
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        }else{
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}
