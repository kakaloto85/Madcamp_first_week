package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class tab4 extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";
    private Button send;
    private LinearLayout container;
    private SharedPreferences sp;
    private String imgpath;
    private Context mContext = null;

    //    private String phonename = sp.getString("name", "");
//    private String phonenumber = sp.getString("number", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);
        mContext = this;
        Log.d("__________________", "tab4");

        //editor cursor 없애기
//        EditText et = (EditText)findViewById(R.id.inputText);
//        et.setInputType(EditorInfo.TYPE_NULL);
//        et.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((EditText)view).setInputType(EditorInfo.TYPE_CLASS_TEXT);
//            }
//        });
//        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                String inText = textView.getText().toString();
//                // Do Something...
//                textView.setInputType(EditorInfo.TYPE_NULL);
//                return true;
//            }
//        });

        //sharedpreferences에서 이미지가져오기
        sp = getSharedPreferences("DB", MODE_PRIVATE);
        imgpath = sp.getString("imgPath", "");
//        BitmapFactory.Options bfo = new BitmapFactory.Options();
        ImageView iv = (ImageView) findViewById(R.id.tab4_image);
//        Bitmap bm = BitmapFactory.decodeFile(imgpath, bfo);
//        Bitmap resized = Bitmap.createScaledBitmap(bm, 410, 400, true);
//        iv.setImageBitmap(resized);
        Glide.with(mContext).load(imgpath).into(iv);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Log.d("ITPANGPANG","img("+200 +" x "+300+")");
            }
        }, 2000);
        //container = (LinearLayout)findViewById(R.id.activity_tab4);
        Button send1 = (Button) findViewById(R.id.send);
        send1.setOnClickListener(this);

    }



    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.send:
                View container = getWindow().getDecorView();
                container.buildDrawingCache();
                Bitmap cvt = container.getDrawingCache();
                //원본 이미지 crop
                Bitmap captureView = Bitmap.createBitmap(cvt, 0, 250, cvt.getWidth(), cvt.getHeight() - 450);
                //sharedpreferences에 저장
                sp = getSharedPreferences("DB",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                //이미지 내부 디렉토리 저장하기
                String address = MediaStore.Images.Media.insertImage(getContentResolver(),captureView,"title", null);
                //saveBitmapToJpeg(captureView, "cacheimg");
                //data/user/0/com.example.myapplication/app_imageDir

                //비트맵 저장
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                captureView.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encoded = Base64.encodeToString(b, Base64.DEFAULT);
                editor.putString("screenshot", encoded);
                editor.commit();
                editor.putString("screenshot_internal_path", address);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

    }
}