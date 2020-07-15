package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class letter_popup extends Activity {
    private Context mContext = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_pop);



    }
    public void letter_yes(View v){
        SharedPreferences sp = getSharedPreferences("DB", MODE_PRIVATE);

        String picture_exist=sp.getString("imgPath","");
        if(picture_exist!="") {
            String pathofBmp = sp.getString("screenshot_internal_path", "");
            Uri bmpUri = Uri.parse(pathofBmp);
            if (pathofBmp != "") {
                sendMMS(bmpUri);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("screenshot");
                editor.remove("screenshot_internal_path");
                editor.remove("imgPath");
                editor.commit();
            } else {
                Toast.makeText(this, "편지를 써주세요!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "사진을 선택해주세요!", Toast.LENGTH_SHORT).show();

        }





    }
    public void letter_no(View v){
        //액티비티(팝업) 닫기
        finish();
    }


    public void sendMMS(Uri uri) {
        SharedPreferences sp = getSharedPreferences("DB", MODE_PRIVATE);
        String number = sp.getString("number", "");
        String name = sp.getString("name", "");
        if (number != "") {
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra("address", number);
                intent.putExtra("subject", "MMS Test");
                intent.setType("image/jpg");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            startActivityForResult(Intent.createChooser(intent, "send"), 0);
//        }
//        catch (ActivityNotFoundException e) {
//            Toast.makeText(getActivity().getApplicationContext(), "no sns", Toast.LENGTH_SHORT).show();
                startActivity(Intent.createChooser(intent, "send"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else{
            Toast.makeText(this,"편지 받을 사람을 알려주세요!",Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
