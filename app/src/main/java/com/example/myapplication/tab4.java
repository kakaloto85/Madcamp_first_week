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
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    public String saveToInternalStorage(Bitmap bitmapImage){

//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        // path to /data/data/yourapp/app_data/imageDir
//        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//        // Create imageDir

        File directory = this.getFilesDir();

        File mypath=new File(directory,"profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void saveBitmapToJpeg(Bitmap bitmap, String name) {

        //내부저장소 캐시 경로를 받아옵니다.
        File storage = getCacheDir();

        //저장할 파일 이름
        String fileName = name + ".jpg";

        //storage 에 파일 인스턴스를 생성합니다.
        File tempFile = new File(storage, fileName);

        try {

            // 자동으로 빈 파일을 생성합니다.
            tempFile.createNewFile();

            // 파일을 쓸 수 있는 스트림을 준비합니다.
            FileOutputStream out = new FileOutputStream(tempFile);

            // compress 함수를 사용해 스트림에 비트맵을 저장합니다.
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            // 스트림 사용후 닫아줍니다.
            out.close();

        } catch (FileNotFoundException e) {
            Log.e("MyTag","FileNotFoundException : " + e.getMessage());
        } catch (IOException e) {
            Log.e("MyTag","IOException : " + e.getMessage());
        }
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.send:
                View container = getWindow().getDecorView();
                container.buildDrawingCache();
                Bitmap cvt = container.getDrawingCache();
                //원본 이미지 crop
                Bitmap captureView = Bitmap.createBitmap(cvt, 0, 250, cvt.getWidth(), cvt.getHeight() - 400);
                //sharedpreferences에 저장
                sp = getSharedPreferences("DB",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                //이미지 내부 디렉토리 저장하기
                String address = MediaStore.Images.Media.insertImage(getContentResolver(),captureView,"title", null);
                //saveBitmapToJpeg(captureView, "cacheimg");
                //data/user/0/com.example.myapplication/app_imageDir
                Log.d("$$$$$$",address);

                //비트맵 저장
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                captureView.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encoded = Base64.encodeToString(b, Base64.DEFAULT);
                editor.putString("screenshot", encoded);
                editor.commit();
                editor.putString("screenshot_internal_path", address);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(mContext, MainActivity.class);
                startActivity(i);

        }

    }
}