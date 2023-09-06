package com.example.pr13;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstFragment extends Fragment {
    public static final int IDM_A = 101;
    public static final int IDM_B = 102;

    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        button = (Button)root.findViewById(R.id.button_long_press);
        textView = (TextView)root.findViewById(R.id.text_fragment);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Зажми", Toast.LENGTH_LONG)
                        .show();
            }
        });

        registerForContextMenu(button);
        registerForContextMenu(textView);
        return root;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, IDM_A, Menu.NONE, "Чёрный");
        menu.add(Menu.NONE, IDM_B, Menu.NONE, "Белый");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case IDM_A:
                Toast.makeText(getActivity(), "Чёрный", Toast.LENGTH_LONG)
                        .show();
                button.setTextColor(Color.DKGRAY);
                textView.setTextColor(Color.DKGRAY);
                return true;
            case IDM_B:
                Toast.makeText(getActivity(), "Белый", Toast.LENGTH_LONG)
                        .show();
                button.setTextColor(Color.WHITE);
                textView.setTextColor(Color.WHITE);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}