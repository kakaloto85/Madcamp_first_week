package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

public class tab3 extends Fragment implements View.OnClickListener {
    private SharedPreferences sp;
    //    private String phonename = sp.getString("name", "");
//    private String phonenumber = sp.getString("number", "");
    private Context mContext;
    public Bitmap b;

    public tab3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    class MyListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            String encoded = sp.getString("screenshot", "");

            if (encoded !=""& let==0) {
                Intent intent = new Intent(getActivity(), letter_popup.class);

                getActivity().startActivity(intent);
            }
            else{
                refresh();
            }
        } // end onClick


    } // end MyListener()



    int let = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
//        sp = getActivity().getSharedPreferences("DB", MODE_PRIVATE);
//        String encoded = sp.getString("screenshot", "");
//        byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
//        ImageView image = (ImageView) view.findViewById(R.id.letter);
//        b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//        image.setImageBitmap(b);

        Button btn_write = (Button) view.findViewById(R.id.btn_write);
        btn_write.setOnClickListener(this);
        Button btn_show = (Button) view.findViewById(R.id.btn_show);
        btn_show.setOnClickListener(this);
        Button btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        return view;
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write:
                Intent i = new Intent(getActivity(), tab4.class);
                Toast.makeText(getActivity().getApplicationContext(), "write a letter!", Toast.LENGTH_LONG).show();
                startActivity(i);
                break;
            case R.id.btn_show:
                Intent i2 = new Intent(getActivity(), tab5.class);
                Toast.makeText(getActivity().getApplicationContext(), "show the letter!", Toast.LENGTH_LONG).show();
                startActivity(i2);
                break;
            case R.id.btn_send:
                Intent i3 = new Intent(getActivity(), letter_popup.class);
                Toast.makeText(getActivity().getApplicationContext(), "send the letter!", Toast.LENGTH_LONG).show();
                startActivity(i3);
                break;
        }
    }
    private void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

}