package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab5 extends AppCompatActivity {

    private SharedPreferences sp;
    private Context mContext;

    public tab5() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static tab5 newInstance(String param1, String param2) {
        tab5 fragment = new tab5();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab5);
        mContext = this;
        sp = getSharedPreferences("DB", MODE_PRIVATE);
        String encoded = sp.getString("screenshot", "");
        Log.d("@@@@@@@@@@@@@@@", encoded);
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
        Log.d("@@@@@@@@@@@@@@@", imageAsBytes.toString());
        ImageView image = (ImageView)findViewById(R.id.tab5);
        Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        image.setImageBitmap(b);

    }

}