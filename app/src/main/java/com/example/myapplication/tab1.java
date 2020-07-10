package com.example.myapplication;
import android.Manifest;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
public class tab1 extends Fragment {
    //RECYCLER ADAPTER를 불러옴
    private RecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //이름과 전화번호를 위한 리스트 생성
        ArrayList name =new ArrayList();
        ArrayList number = new ArrayList();

        //연락처 permission 받아오기
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }

        //projection이 뭘까..?
        String [] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME };
        String [] arrPhoneProjection = {
                ContactsContract.CommonDataKinds.Phone.NUMBER };


        // get user list
        Cursor clsCursor = getActivity().getContentResolver().query (
            ContactsContract.Contacts.CONTENT_URI, arrProjection,
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1" ,
            null, null
        );
        //전화번호가 있는 사람들의 이름과 전화번호를 쿼리를 이용해 가져옴
        while( clsCursor.moveToNext() )
        {
            String strContactId = clsCursor.getString( 0 );

            name.add(clsCursor.getString( 1 ));

            // phone number
            Cursor clsPhoneCursor = getActivity().getContentResolver().query (
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrPhoneProjection,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + strContactId,
                null, null);

            while( clsPhoneCursor.moveToNext() )
            {
                number.add(clsPhoneCursor.getString( 0 ));

            }
            clsPhoneCursor.close();


        }
        clsCursor.close( );

        //data 객체에 값 채우기
        for (int i = 0; i < name.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle((String) name.get(i));
            data.setContent((String) number.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();


        return view;

    }
}