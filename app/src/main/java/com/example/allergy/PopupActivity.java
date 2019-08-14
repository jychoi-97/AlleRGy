package com.example.allergy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.common.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PopupActivity extends Activity {


    TextView txtText;
    StringBuilder sb;


    public ArrayList<String> getStringArrayList(String key){
        SharedPreferences prefs = getSharedPreferences("users_allery_list", MODE_PRIVATE);
        String json = prefs.getString(key, null);
        ArrayList<String> valueList = new ArrayList<>();
        if(json!=null){
            try {
                JSONArray jArray = new JSONArray(json);
                for(int i=0; i<jArray.length(); i++){
                    String data = jArray.optString(i);
                    valueList.add(data);
                }
            } catch (JSONException e){
                e.getMessage();
            }
        }
        return valueList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sb = new StringBuilder();
        for(int i=0; i<getStringArrayList("usersAllergyList").size(); i++){
            if(i!=getStringArrayList("usersAllergyList").size()-1)
                sb.append(getStringArrayList("usersAllergyList").get(i)).append(", ");
            else
                sb.append(getStringArrayList("usersAllergyList").get(i));
        }






        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);

//        appData = getSharedPreferences("appData", MODE_PRIVATE);



        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);
        txtText.setText(sb);


        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");

    }



    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}



