
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
    Button meatStorageButton;

    ArrayList<String> meatArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_storage);
        meatStorageButton = (Button) findViewById(R.id.meatStorageButton);


        meatStorageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStringArrayPref("usersAllergyList", meatArrayList);

                Toast.makeText(getApplicationContext(), meatArrayList.get(0) + meatArrayList.get(1), Toast.LENGTH_LONG).show();




            }
        });



    }
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();


        switch(view.getId()) {
            case R.id.checkCow:
                if (checked)
                    meatArrayList.add("쇠고기");
                break;
            case R.id.checkPig:
                if (checked)
                    meatArrayList.add("돼지고기");
                break;
            case R.id.checkGoat:
                if (checked)
                    meatArrayList.add("염소고기");
                break;
            case R.id.checkYang:
                if (checked)
                    meatArrayList.add("양고기");
                break;
            case R.id.checkOri:
                if (checked)
                    meatArrayList.add("오리고기");
                break;
            case R.id.checkChicken:
                if (checked)
                    meatArrayList.add("닭고기");
                break;
        }
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