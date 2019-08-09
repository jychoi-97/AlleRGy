
package com.example.allergy;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class MeatStorageActivity extends AppCompatActivity {

    SharedPreferences appData;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_storage);
        appData = getSharedPreferences("appData", MODE_PRIVATE);

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



//        meatStorageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setStringArrayPref("usersAllergyList", meatArrayList);
//
////                Toast.makeText(getApplicationContext(), meatArrayList.get(0) + meatArrayList.get(1), Toast.LENGTH_LONG).show();
//
//
//
//
//            }
//        });

        meatStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = appData.edit();
                if(meat1.isChecked())
                    meatArrayList.add("쇠고기");
                if(meat2.isChecked())
                    meatArrayList.add("닭고기");
                if(meat3.isChecked())
                    meatArrayList.add("양고기");
                if(meat4.isChecked())
                    meatArrayList.add("돼지고기");
                if(meat5.isChecked())
                    meatArrayList.add("오리고기");
                if(meat6.isChecked())
                    meatArrayList.add("염소고기");
                if(fruit1.isChecked())
                    meatArrayList.add("복숭아");
                if(fruit2.isChecked())
                    meatArrayList.add("키위");
                if(fruit3.isChecked())
                    meatArrayList.add("바나나");
                if(fruit4.isChecked())
                    meatArrayList.add("딸기");
                if(fish1.isChecked())
                    meatArrayList.add("조개");
                if(fish1.isChecked())
                    meatArrayList.add("고등어");
                if(fish2.isChecked())
                    meatArrayList.add("오징어");
                if(fish3.isChecked())
                    meatArrayList.add("게");
                if(fish4.isChecked())
                    meatArrayList.add("새우");
                if(fish5.isChecked())
                    meatArrayList.add("멸치");
                if(nut1.isChecked())
                    meatArrayList.add("호두");
                if(nut2.isChecked())
                    meatArrayList.add("아몬드");
                if(nut3.isChecked())
                    meatArrayList.add("땅콩");
                if(nut4.isChecked())
                    meatArrayList.add("잣");
                if(milk1.isChecked())
                    meatArrayList.add("우유");
                if(milk2.isChecked())
                    meatArrayList.add("계란");
                if(milk3.isChecked())
                    meatArrayList.add("치즈");
                if(milk4.isChecked())
                    meatArrayList.add("버터");

                JSONArray a = new JSONArray();
                for (int i = 0; i < meatArrayList.size(); i++) {
                    a.put(meatArrayList.get(i));
                }
                if (!meatArrayList.isEmpty()) {
                    editor.putString("usersAllergyList", a.toString());
                } else {
                    editor.putString("usersAllergyList", null);
                }
                editor.commit();


//                setStringArrayPref("usersAllergyList", meatArrayList);







            }
        });



    }
//    public void onCheckboxClicked(View view) {
//
//        boolean checked = ((CheckBox) view).isChecked();
//
//
//        switch(view.getId()) {
//            case R.id.meat1:
//                if (checked)
//                    meatArrayList.add("쇠고기");
//                break;
//            case R.id.meat2:
//                if (checked)
//                    meatArrayList.add("닭고기");
//                break;
//            case R.id.meat3:
//                if (checked)
//                    meatArrayList.add("양고기");
//                break;
//            case R.id.meat4:
//                if (checked)
//                    meatArrayList.add("돼지고기");
//                break;
//            case R.id.meat5:
//                if (checked)
//                    meatArrayList.add("오리고기");
//                break;
//            case R.id.meat6:
//                if (checked)
//                    meatArrayList.add("염소고기");
//                break;
//            case R.id.fruit1:
//                if (checked)
//                    meatArrayList.add("복숭아");
//                break;
//            case R.id.fruit2:
//                if (checked)
//                    meatArrayList.add("키위");
//                break;
//            case R.id.fruit3:
//                if (checked)
//                    meatArrayList.add("바나나");
//                break;
//            case R.id.fruit4:
//                if (checked)
//                    meatArrayList.add("딸기");
//                break;
//            case R.id.fish1:
//                if (checked)
//                    meatArrayList.add("조개");
//                break;
//            case R.id.fish2:
//                if (checked)
//                    meatArrayList.add("고등어");
//                break;
//            case R.id.fish3:
//                if (checked)
//                    meatArrayList.add("오징어");
//                break;
//            case R.id.fish4:
//                if (checked)
//                    meatArrayList.add("게");
//                break;
//            case R.id.fish5:
//                if (checked)
//                    meatArrayList.add("새우");
//                break;
//            case R.id.fish6:
//                if (checked)
//                    meatArrayList.add("멸치");
//                break;
//            case R.id.nut1:
//                if (checked)
//                    meatArrayList.add("호두");
//                break;
//            case R.id.nut2:
//                if (checked)
//                    meatArrayList.add("아몬드");
//                break;
//            case R.id.nut3:
//                if (checked)
//                    meatArrayList.add("땅콩");
//                break;
//            case R.id.nut4:
//                if (checked)
//                    meatArrayList.add("잣");
//                break;
//            case R.id.milk1:
//                if (checked)
//                    meatArrayList.add("우유");
//                break;
//            case R.id.milk2:
//                if (checked)
//                    meatArrayList.add("계란");
//                break;
//            case R.id.milk3:
//                if (checked)
//                    meatArrayList.add("치즈");
//                break;
//            case R.id.milk4:
//                if (checked)
//                    meatArrayList.add("버터");
//                break;
//
//        }
//    }
//    public void setStringArrayPref(String key, ArrayList<String> values) {
//        SharedPreferences prefs = getSharedPreferences("users_allery_list", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        JSONArray a = new JSONArray();
//        for (int i = 0; i < values.size(); i++) {
//            a.put(values.get(i));
//        }
//        if (!values.isEmpty()) {
//            editor.putString(key, a.toString());
//        } else {
//            editor.putString(key, null);
//        }
//        editor.commit();
//    }
}