package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;

import static android.content.Context.MODE_PRIVATE;

public class tab3 extends Fragment implements View.OnClickListener {
    private SharedPreferences sp;
    //    private String phonename = sp.getString("name", "");
//    private String phonenumber = sp.getString("number", "");
    private Context mContext;

    public tab3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        sp = getActivity().getSharedPreferences("DB", MODE_PRIVATE);
        String encoded = sp.getString("screenshot", "");
        Log.d("@@@@@@@@@@@@@@@", encoded);
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
        Log.d("@@@@@@@@@@@@@@@", imageAsBytes.toString());
        ImageView image = (ImageView) view.findViewById(R.id.letter);
        Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        image.setImageBitmap(b);

        Button btn = (Button) view.findViewById(R.id.calltab4);
        btn.setOnClickListener(this);
        Button bts = (Button) view.findViewById(R.id.calltab5);
        bts.setOnClickListener(this);
        return view;//B@23b75c7 --> B@69a483d
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calltab4:
                Intent intent = new Intent(getActivity(), tab4.class);
                startActivity(intent);
                break;
            case R.id.calltab5:
                Intent it = new Intent(getActivity(), tab5.class);
                startActivity(it);
                break;
        }
    }
}