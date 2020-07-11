package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

public class tab2 extends Fragment {

    private GridView mgridView;
    private Image_Adapter image_adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        image_adapter=new Image_Adapter(getActivity());

        mgridView = (GridView) view.findViewById(R.id.grid_view);
        mgridView.setAdapter( image_adapter);
        return view;
    }
}
