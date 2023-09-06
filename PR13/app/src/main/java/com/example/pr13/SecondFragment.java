package com.example.pr13;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

public class SecondFragment extends Fragment {

    Button button;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        button = (Button) root.findViewById(R.id.button_score);
        textView = (TextView) root.findViewById(R.id.text_fragment_second);

        button.setOnClickListener(viewClickListener);
        return root;
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }
    };

    int i;

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this.getContext(), v);
        popupMenu.inflate(R.menu.popmenu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.plus:
                                Toast.makeText(getContext(), "+", Toast.LENGTH_SHORT).show();
                                i=i+1;
                                textView.setText(""+i);
                                return true;
                            case R.id.minus:
                                Toast.makeText(getContext(), "-", Toast.LENGTH_SHORT).show();
                                i=i-1;
                                textView.setText(""+i);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getContext(), "onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
}