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

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
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
//                String adress = sp.getString("uri", "");
//                Uri providerURI; // sendMMS 함수 파라메터로 전달할 URI 변수
//                if(Build.VERSION.SDK_INT < 24) {
//                    providerURI = Uri.fromFile(new File(adress)); // savefile은 MMS로 보낼 이미지 파일의 File 형식의 변수입니다.
//                }
//                else {
//                    providerURI = FileProvider.getUriForFile(getActivity().getBaseContext(), getActivity().getPackageName(), new File(adress));
//                }
//                sendMMS(providerURI);
                String pathofBmp = sp.getString("uri", "");
                Log.d("@sdk----------------", pathofBmp); //  /storage/emulated/0/capture.jpeg
                Uri bmpUri = Uri.parse(pathofBmp);
                Log.d("@uri----------------", bmpUri.toString()); //   /storage/emulated/0/capture.jpeg
                sendMMS(bmpUri);
//                final Intent emailIntent1 = new Intent(android.content.Intent.ACTION_SEND);
//                emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
//                emailIntent1.setType("image/png");
                break;
        }
    }

    public void sendMMS(Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpg");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(Intent.createChooser(intent, "send"), 0);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity().getApplicationContext(), "no sns", Toast.LENGTH_SHORT).show();
        }
    }
}