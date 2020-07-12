package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        sp = getSharedPreferences("DB", MODE_PRIVATE);
        imgpath = sp.getString("imgPath", "");
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        ImageView iv = (ImageView) findViewById(R.id.tab4_image);
        Bitmap bm = BitmapFactory.decodeFile(imgpath, bfo);
        Bitmap resized = Bitmap.createScaledBitmap(bm, 410, 400, true);
        iv.setImageBitmap(resized);
        container = (LinearLayout)findViewById(R.id.activity_tab4);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    public Fragment calltabs(){
//        sp = getSharedPreferences("DB", MODE_PRIVATE);
//        String finalimg = sp.getString("screenshot", "");
//        if(finalimg != null){
//            BitmapFactory.Options bfo = new BitmapFactory.Options();
//            ImageView iv = (ImageView) findViewById(R.id.letter);
//            Bitmap bm = BitmapFactory.decodeFile(finalimg, bfo);
//            Bitmap resized = Bitmap.createScaledBitmap(bm, 400, 677, true);
//            iv.setImageBitmap(resized);
//        }else{
//
//        }

        tab3 f = new tab3();
        return f;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.send:
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    sp = getSharedPreferences("DB",MODE_PRIVATE);
                    String adress = Environment.getExternalStorageDirectory().toString()+"/capture.jpeg";
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("screenshot", adress);
                    editor.commit();
                    fos = new FileOutputStream(adress);
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                    Intent i = new Intent(mContext, MainActivity.class);
//                    i.putExtra("letterfile", adress);
//                    startActivity(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(mContext, MainActivity.class);
        startActivity(i);
    }
}