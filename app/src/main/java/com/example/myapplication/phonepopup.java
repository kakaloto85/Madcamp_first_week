package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
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
        //데이터 전달하기
        /*Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);*/

        //액티비티(팝업) 닫기
        finish();
    }
    public void no(View v){
        //데이터 전달하기
        /*Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);*/

        //액티비티(팝업) 닫기
        finish();
    }



}
