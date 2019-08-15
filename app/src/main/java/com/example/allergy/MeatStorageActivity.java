
package com.example.allergy;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class MeatStorageActivity extends AppCompatActivity {



    boolean saveLoginData[];



    Button meatStorageButton;
    CheckBox meat1;
    CheckBox meat2;
    CheckBox meat3;
    CheckBox meat4;
    CheckBox meat5;
    CheckBox meat6;
    CheckBox fruit1;
    CheckBox fruit2;
    CheckBox fruit3;
    CheckBox fruit4;
    CheckBox fish1;
    CheckBox fish2;
    CheckBox fish3;
    CheckBox fish4;
    CheckBox fish5;
    CheckBox fish6;
    CheckBox nut1;
    CheckBox nut2;
    CheckBox nut3;
    CheckBox nut4;
    CheckBox milk1;
    CheckBox milk2;
    CheckBox milk3;
    CheckBox milk4;

    ArrayList<String> meatArrayList = new ArrayList<>();
//    public void setStringArrayList(String key, ArrayList<String> valueList){
//        SharedPreferences prefs = getSharedPreferences("users_allery_list", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        JSONArray jsonArray = new JSONArray();
//        for(int i=0; i<valueList.size(); i++){
//            jsonArray.put(valueList.get(i));
//        }
//        if(!valueList.isEmpty()){
//            editor.putString(key, jsonArray.toString());
//        } else {
//            editor.putString(key, null);
//        }
//        editor.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_storage);
        SharedPreferences pref = getSharedPreferences("users_allergy_list", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();








        meatStorageButton = (Button) findViewById(R.id.allergyStorageButton);
        meat1 = (CheckBox) findViewById(R.id.meat1);
        meat2 = (CheckBox) findViewById(R.id.meat2);
        meat3 = (CheckBox) findViewById(R.id.meat3);
        meat4 = (CheckBox) findViewById(R.id.meat4);
        meat5 = (CheckBox) findViewById(R.id.meat5);
        meat6 = (CheckBox) findViewById(R.id.meat6);
        fruit1 = (CheckBox) findViewById(R.id.fruit1);
        fruit2 = (CheckBox) findViewById(R.id.fruit2);
        fruit3 = (CheckBox) findViewById(R.id.fruit3);
        fruit4 = (CheckBox) findViewById(R.id.fruit4);
        fish1 = (CheckBox) findViewById(R.id.fish1);
        fish2 = (CheckBox) findViewById(R.id.fish2);
        fish3 = (CheckBox) findViewById(R.id.fish3);
        fish4 = (CheckBox) findViewById(R.id.fish4);
        fish5 = (CheckBox) findViewById(R.id.fish5);
        fish6 = (CheckBox) findViewById(R.id.fish6);
        nut1 = (CheckBox) findViewById(R.id.nut1);
        nut2 = (CheckBox) findViewById(R.id.nut2);
        nut3 = (CheckBox) findViewById(R.id.nut3);
        nut4 = (CheckBox) findViewById(R.id.nut4);
        milk1 = (CheckBox) findViewById(R.id.milk1);
        milk2 = (CheckBox) findViewById(R.id.milk2);
        milk3 = (CheckBox) findViewById(R.id.milk3);
        milk4 = (CheckBox) findViewById(R.id.milk4);

        Boolean chk1 = pref.getBoolean("check1", false);
        Boolean chk2 = pref.getBoolean("check2", false);
        Boolean chk3 = pref.getBoolean("check3", false);
        Boolean chk4 = pref.getBoolean("check4", false);
        Boolean chk5 = pref.getBoolean("check5", false);
        Boolean chk6 = pref.getBoolean("check6", false);
        Boolean chk7 = pref.getBoolean("check7", false);
        Boolean chk8 = pref.getBoolean("check8", false);
        Boolean chk9 = pref.getBoolean("check9", false);
        Boolean chk10 = pref.getBoolean("check10", false);
        Boolean chk11 = pref.getBoolean("check11", false);
        Boolean chk12 = pref.getBoolean("check12", false);
        Boolean chk13 = pref.getBoolean("check13", false);
        Boolean chk14 = pref.getBoolean("check14", false);
        Boolean chk15 = pref.getBoolean("check15", false);
        Boolean chk16 = pref.getBoolean("check16", false);
        Boolean chk17 = pref.getBoolean("check17", false);
        Boolean chk18 = pref.getBoolean("check18", false);
        Boolean chk19 = pref.getBoolean("check19", false);
        Boolean chk20 = pref.getBoolean("check20", false);
        Boolean chk21 = pref.getBoolean("check21", false);
        Boolean chk22 = pref.getBoolean("check22", false);
        Boolean chk23 = pref.getBoolean("check23", false);
        Boolean chk24 = pref.getBoolean("check24", false);

        meat1.setChecked(chk1);
        meat2.setChecked(chk2);
        meat3.setChecked(chk3);
        meat4.setChecked(chk4);
        meat5.setChecked(chk5);
        meat6.setChecked(chk6);
        fruit1.setChecked(chk7);
        fruit2.setChecked(chk8);
        fruit3.setChecked(chk9);
        fruit4.setChecked(chk10);
        fish1.setChecked(chk11);
        fish2.setChecked(chk12);
        fish3.setChecked(chk13);
        fish4.setChecked(chk14);
        fish5.setChecked(chk15);
        fish6.setChecked(chk16);
        nut1.setChecked(chk17);
        nut2.setChecked(chk18);
        nut3.setChecked(chk19);
        nut4.setChecked(chk20);
        milk1.setChecked(chk21);
        milk2.setChecked(chk22);
        milk3.setChecked(chk23);
        milk4.setChecked(chk24);




        meatStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(meat1.isChecked()) {
                    meatArrayList.add("쇠고기");
                    editor.putBoolean("check1", meat1.isChecked());

                } else{
                    editor.putBoolean("check1", false);

                }
                if(meat2.isChecked()) {
                    meatArrayList.add("닭고기");
                    editor.putBoolean("check2", meat2.isChecked());
                } else{
                    editor.putBoolean("check2", false);

                }
                if(meat3.isChecked()) {
                    meatArrayList.add("양고기");
                    editor.putBoolean("check3", meat3.isChecked());
                } else{
                    editor.putBoolean("check3", false);

                }
                if(meat4.isChecked()) {
                    meatArrayList.add("돼지고기");
                    editor.putBoolean("check4", meat4.isChecked());
                } else{
                    editor.putBoolean("check4", false);

                }
                if(meat5.isChecked()) {
                    meatArrayList.add("오리고기");
                    editor.putBoolean("check5", meat5.isChecked());
                } else{
                    editor.putBoolean("check5", false);

                }
                if(meat6.isChecked()) {
                    meatArrayList.add("염소고기");
                    editor.putBoolean("check6", meat6.isChecked());
                } else{
                    editor.putBoolean("check6", false);

                }
                if(fruit1.isChecked()) {
                    meatArrayList.add("복숭아");
                    editor.putBoolean("check7", fruit1.isChecked());
                } else{
                    editor.putBoolean("check7", false);

                }
                if(fruit2.isChecked()) {
                    meatArrayList.add("키위");
                    editor.putBoolean("check8", fruit2.isChecked());
                } else{
                    editor.putBoolean("check8", false);

                }
                if(fruit3.isChecked()) {
                    meatArrayList.add("바나나");
                    editor.putBoolean("check9", fruit3.isChecked());
                } else{
                    editor.putBoolean("check9", false);

                }
                if(fruit4.isChecked()) {
                    meatArrayList.add("딸기");
                    editor.putBoolean("check10", fruit4.isChecked());
                } else{
                    editor.putBoolean("check10", false);

                }
                if(fish1.isChecked()){
                    meatArrayList.add("조개");
                    editor.putBoolean("check11", fish1.isChecked());
                } else{
                    editor.putBoolean("check11", false);

                }
                if(fish2.isChecked()) {
                    meatArrayList.add("고등어");
                    editor.putBoolean("check12", fish2.isChecked());
                } else{
                    editor.putBoolean("check12", false);

                }
                if(fish3.isChecked()) {
                    meatArrayList.add("오징어");
                    editor.putBoolean("check13", fish3.isChecked());
                } else{
                    editor.putBoolean("check13", false);

                }
                if(fish4.isChecked()) {
                    meatArrayList.add("게");
                    editor.putBoolean("check14", fish4.isChecked());
                } else{
                    editor.putBoolean("check14", false);

                }
                if(fish5.isChecked()) {
                    meatArrayList.add("새우");
                    editor.putBoolean("check15", fish5.isChecked());
                } else{
                    editor.putBoolean("check15", false);

                }
                if(fish6.isChecked()){
                    meatArrayList.add("멸치");
                    editor.putBoolean("check16", fish6.isChecked());
                } else{
                    editor.putBoolean("check16", false);

                }
                if(nut1.isChecked()) {
                    meatArrayList.add("호두");
                    editor.putBoolean("check17", nut1.isChecked());
                } else{
                    editor.putBoolean("check17", false);

                }
                if(nut2.isChecked()) {
                    meatArrayList.add("아몬드");
                    editor.putBoolean("check18", nut2.isChecked());
                } else{
                    editor.putBoolean("check18", false);

                }
                if(nut3.isChecked()) {
                    meatArrayList.add("땅콩");
                    editor.putBoolean("check19", nut3.isChecked());
                } else{
                    editor.putBoolean("check19", false);

                }
                if(nut4.isChecked()) {
                    meatArrayList.add("잣");
                    editor.putBoolean("check20", nut4.isChecked());
                } else{
                    editor.putBoolean("check20", false);

                }
                if(milk1.isChecked()) {
                    meatArrayList.add("우유");
                    editor.putBoolean("check21", milk1.isChecked());
                } else{
                    editor.putBoolean("check21", false);

                }
                if(milk2.isChecked()) {
                    meatArrayList.add("계란");
                    editor.putBoolean("check22", milk2.isChecked());
                } else{
                    editor.putBoolean("check22", false);

                }
                if(milk3.isChecked()) {
                    meatArrayList.add("치즈");
                    editor.putBoolean("check23", milk3.isChecked());
                } else{
                    editor.putBoolean("check23", false);

                }
                if(milk4.isChecked()) {
                    meatArrayList.add("버터");
                    editor.putBoolean("check24", milk4.isChecked());
                } else{
                    editor.putBoolean("check24", false);

                }

                editor.commit();
                setStringArrayPref("usersAllergyList", meatArrayList);











            }
        });



    }


    public void setStringArrayPref(String key, ArrayList<String> values) {
        SharedPreferences prefs = getSharedPreferences("users_allery_list", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }
}