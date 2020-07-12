package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class checkpermission extends Activity {
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    { //권한을 허용 했을 경우
        Log.d("what","----------------------------");
        if(requestCode == 1 || requestCode == 100){
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의

                }
            }

        }
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();


    }

    public void checkSelfPermission() {
        String temp1 = "";
        String temp2 = "";

        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        { temp1 += Manifest.permission.READ_EXTERNAL_STORAGE + " "; }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        { temp1 += Manifest.permission.WRITE_EXTERNAL_STORAGE + " "; }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        { temp1+= Manifest.permission.READ_CONTACTS + " "; }


        if (TextUtils.isEmpty(temp1) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(this, temp1.trim().split(" "),1);
        }
        else { // 모두 허용 상태
            Toast.makeText(this, "권한을 모두 허용1", Toast.LENGTH_SHORT).show(); } }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSelfPermission();

    }
}
