package com.example.nikhil.bolt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PostFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.post_fragment,container,false);
        return view;
    }
   public static PostFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
