package com.example.ahmad_zakir.snapchatclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ahmad_zakir on 1/19/2018.
 */

public class StoriesFragment extends Fragment {
    public  static StoriesFragment newInstance(){
        StoriesFragment fragment = new StoriesFragment();
        return fragment;
    }
    //Menampilkan OnCreateView ketika objek kelas dipanggil
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentstories ,container,false);
        return  view;
    }
}
