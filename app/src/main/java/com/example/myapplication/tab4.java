package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class tab4 extends AppCompatActivity {
    private static String TAG = "MainActivity";
    Button load, save, delete;
    EditText inputText;
    private SharedPreferences sp;
    private String imgpath;
    private final int imgWidth = 410;
    private final int imgHeight = 400;
//    private String phonename = sp.getString("name", "");
//    private String phonenumber = sp.getString("number", "");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);
        sp = getSharedPreferences("DB",MODE_PRIVATE);
        imgpath = sp.getString("imgPath", "");
        BitmapFactory.Options bfo = new BitmapFactory.Options();
        ImageView iv = (ImageView) findViewById(R.id.tab4_image);
        Bitmap bm = BitmapFactory.decodeFile(imgpath, bfo);
        Bitmap resized = Bitmap.createScaledBitmap(bm, imgWidth, imgHeight, true);
        iv.setImageBitmap(resized);

        load= (Button) findViewById(R.id.load);
        save = (Button) findViewById(R.id.save);
        delete = (Button) findViewById(R.id.delete);
        inputText = (EditText) findViewById(R.id.inputText);
        load.setOnClickListener(listener);
        save.setOnClickListener(listener);
        delete.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {

        @Override

        public void onClick(View view) {
            switch (view.getId()){
                case R.id.load:
                    Log.i("TAG", "load 진행");
                    FileInputStream fis = null;
                    try{
                        fis = openFileInput("memo.txt");
                        byte[] data = new byte[fis.available()];
                        while( fis.read(data) != -1){
                        }
                        inputText.setText(new String(data));
                        Toast.makeText(tab4.this, "load 완료", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{ if(fis != null) fis.close(); }catch(Exception e){e.printStackTrace();}
                    }
                    break;
                case R.id.save:
                    Log.i("TAG", "save 진행");
                    FileOutputStream fos = null;
                    try{
                        fos = openFileOutput("memo.txt", Context.MODE_PRIVATE);
                        String out = inputText.getText().toString();
                        fos.write(out.getBytes());
                        Toast.makeText(tab4.this, "save 완료", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        try{ if(fos != null) fos.close(); }catch(Exception e){e.printStackTrace();}
                    }
                    break;
                case R.id.delete:
                    Log.i("TAG", "delete 진행");
                    boolean b = deleteFile("memo.txt");
                    if(b){
                        Toast.makeText(tab4.this, "delete 완료", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(tab4.this, "delete 실패", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}