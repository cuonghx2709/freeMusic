package com.example.cuonghx.freemusic.fragments;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuonghx.freemusic.R;
import com.example.cuonghx.freemusic.adapter.DownloadAdpater;
import com.example.cuonghx.freemusic.databases.TopSongModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DowloadFragment extends Fragment {


    @BindView(R.id.rc_view_download)
    RecyclerView recyclerView;

    public DowloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dowload, container, false);
        SetUpUI(view);
        return view;
    }

    private void SetUpUI(View view) {

        List<TopSongModel> list = new ArrayList<>();
        ButterKnife.bind(this, view);


        File myDirectory = new File(Environment.getExternalStorageDirectory(), "Music Download");

        if(!myDirectory.exists()) {
            myDirectory.mkdirs();
        }

        for (File file : myDirectory.listFiles()){
            String name = file.getName();
            int index = name.indexOf("-");
            if (index > 0 ){
                TopSongModel topSongModel = new TopSongModel(name.substring(0, index), name.substring(index + 1, name.indexOf(".mp3")), null);
                list.add(topSongModel);
                Log.d("cuonghx", "SetUpUI: " + topSongModel.getArtist());
            }
        }

        DownloadAdpater downloadAdpater = new DownloadAdpater(getContext(),list );
        recyclerView.setAdapter(downloadAdpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
