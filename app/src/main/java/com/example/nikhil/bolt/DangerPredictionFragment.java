package com.example.nikhil.bolt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DangerPredictionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danger_fragment,container,false);
       // Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();



        return view;
    }

    public static DangerPredictionFragment newInstance() {

        Bundle args = new Bundle();

        DangerPredictionFragment fragment = new DangerPredictionFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
