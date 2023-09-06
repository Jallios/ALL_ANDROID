package com.example.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondFragment extends Fragment {

    Button buttonFragmentSecond;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        buttonFragmentSecond = (Button) root.findViewById(R.id.button_fragmentSecond);
        bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottom_navigation);

        buttonFragmentSecond.setOnClickListener(viewClickListener);
        return root;
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };
}