package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class phonepopup extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.phone_popup);


    }
    //확인 버튼 클릭
    public void yes(View v){
        //데이터 전달받기
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String name= extras.getString("name");
        String number = extras.getString( "number");

        //데이터 기록하기
        SharedPreferences db = getSharedPreferences("DB", MODE_PRIVATE);
        SharedPreferences.Editor editor = db.edit();
        editor.putString("name", name);
        editor.putString("number", number);
        editor.commit();

        //액티비티(팝업) 닫기
        finish();
    }
    public void no(View v){
        //액티비티(팝업) 닫기
        finish();
    }



}
