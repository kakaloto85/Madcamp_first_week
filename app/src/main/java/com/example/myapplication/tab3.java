package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.Context.MODE_PRIVATE;

public class tab3 extends Fragment implements View.OnClickListener{
    private SharedPreferences sp;
//    private String phonename = sp.getString("name", "");
//    private String phonenumber = sp.getString("number", "");

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
//        sp = getActivity().getSharedPreferences("DB", MODE_PRIVATE);
//        String finalimg = sp.getString("screenshot", "");
//        BitmapFactory.Options bfo = new BitmapFactory.Options();
//        ImageView iv = (ImageView) view.findViewById(R.id.letter);
//        Bitmap bm = BitmapFactory.decodeFile(finalimg, bfo);
//        Bitmap resized = Bitmap.createScaledBitmap(bm, 400, 677, true);
//        iv.setImageBitmap(resized);
        Button btn = (Button) view.findViewById(R.id.calltab4);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.calltab4:
                Intent intent = new Intent(getActivity(), tab4.class);
                startActivity(intent);
                break;
        }
    }
}