package com.example.tictactoe;

import static com.example.tictactoe.R.color.black;
import static com.example.tictactoe.R.color.white;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, themecolor;
    String krest, nol;
    TextView textView;
    RelativeLayout background;
    RadioButton PVP, PVI;
    int i = 0;
    int a = 0;
    int step = 0;
    int randomInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        krest = "X";
        nol = "0";
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        textView = findViewById(R.id.textView);
        themecolor = findViewById(R.id.themecolor);
        background = findViewById(R.id.background);
        PVP = findViewById(R.id.PVP);
        PVI = findViewById(R.id.PVI);
    }

    public void clcBtn1(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button1.setText(krest);
                button1.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button1.setText(nol);
                button1.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button1.setText(krest);
            button1.setEnabled(false);
            isPlayerWinner();
            step++;
            Bot();
        }
    }

    public void clcBtn2(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button2.setText(krest);
                button2.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button2.setText(nol);
                button2.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button2.setText(krest);
            button2.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn3(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button3.setText(krest);
                button3.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button3.setText(nol);
                button3.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button3.setText(krest);
            button3.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn4(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button4.setText(krest);
                button4.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button4.setText(nol);
                button4.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button4.setText(krest);
            button4.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn5(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button5.setText(krest);
                button5.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button5.setText(nol);
                button5.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button5.setText(krest);
            button5.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn6(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button6.setText(krest);
                button6.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button6.setText(nol);
                button6.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button6.setText(krest);
            button6.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn7(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button7.setText(krest);
                button7.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button7.setText(nol);
                button7.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button7.setText(krest);
            button7.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn8(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button8.setText(krest);
                button8.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button8.setText(nol);
                button8.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button8.setText(krest);
            button8.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void clcBtn9(View v) {
        if (PVP.isChecked() == true) {


            if (i == 0) {
                button9.setText(krest);
                button9.setEnabled(false);
                isPlayerWinner();
                i = 1;
            } else if (i == 1) {
                button9.setText(nol);
                button9.setEnabled(false);
                isPlayerWinner();
                i = 0;
            }
            step++;
        }
        else if (PVI.isChecked() == true){
            button9.setText(krest);
            button9.setEnabled(false);
            isPlayerWinner();
            i = 1;
            step++;
            Bot();
        }
    }

    public void isPlayerWinner() {
        if (button1.getText() == krest && button4.getText() == krest && button7.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button2.getText() == krest && button5.getText() == krest && button8.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button3.getText() == krest && button6.getText() == krest && button9.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button1.getText() == krest && button2.getText() == krest && button3.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button4.getText() == krest && button5.getText() == krest && button6.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button7.getText() == krest && button8.getText() == krest && button9.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button1.getText() == krest && button5.getText() == krest && button9.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button3.getText() == krest && button5.getText() == krest && button7.getText() == krest )
        {
            textView.setText("Крестики победили!");
            Restart();
        }
        if (button1.getText() == nol && button4.getText() == nol && button7.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button2.getText() == nol && button5.getText() == nol && button8.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button3.getText() == nol && button6.getText() == nol && button9.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button1.getText() == nol && button2.getText() == nol && button3.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button4.getText() == nol && button5.getText() == nol && button6.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button7.getText() == nol && button8.getText() == nol && button9.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button1.getText() == nol && button5.getText() == nol && button9.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        if (button3.getText() == nol && button5.getText() == nol && button7.getText() == nol )
        {
            textView.setText("Нолики победили!");
            Restart();
        }
        else if (step == 8)
        {
            textView.setText("Ничья!");
            Restart();
        }
    }

    public void Bot () {
        if (step < 8) {
            Random random = new Random();
            randomInt = random.nextInt(8 - 1) + 1;
            if (button1.isEnabled() == true && randomInt == 1) {
                button1.setText(nol);
                button1.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button2.isEnabled() == true && randomInt == 2) {
                button2.setText(nol);
                button2.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button3.isEnabled() == true && randomInt == 3) {
                button3.setText(nol);
                button3.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button4.isEnabled() == true && randomInt == 4) {
                button4.setText(nol);
                button4.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button5.isEnabled() == true && randomInt == 5) {
                button5.setText(nol);
                button5.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button6.isEnabled() == true && randomInt == 6) {
                button6.setText(nol);
                button6.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button7.isEnabled() == true && randomInt == 7) {
                button7.setText(nol);
                button7.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button8.isEnabled() == true && randomInt == 8) {
                button8.setText(nol);
                button8.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
            else if (button9.isEnabled() == true && randomInt == 9) {
                button9.setText(nol);
                button9.setEnabled(false);
                step++;
                i = 0;
                isPlayerWinner();
            }
        }
    }

    public void Restart(){
        i = 0;
        randomInt = 0;
        step = 0;
        button1.setText(" ");
        button1.setEnabled(true);
        button2.setText(" ");
        button2.setEnabled(true);
        button3.setText(" ");
        button3.setEnabled(true);
        button4.setText(" ");
        button4.setEnabled(true);
        button5.setText(" ");
        button5.setEnabled(true);
        button6.setText(" ");
        button6.setEnabled(true);
        button7.setText(" ");
        button7.setEnabled(true);
        button8.setText(" ");
        button8.setEnabled(true);
        button9.setText(" ");
        button9.setEnabled(true);
    }

    public void Theme (View v){
        if (a == 0){
            background.setBackgroundResource(black);
            button1.setBackgroundColor(getColor(R.color.red));
            button1.setTextColor(getColor(white));
            button2.setBackgroundColor(getColor(R.color.red));
            button2.setTextColor(getColor(white));
            button3.setBackgroundColor(getColor(R.color.red));
            button3.setTextColor(getColor(white));
            button4.setBackgroundColor(getColor(R.color.red));
            button4.setTextColor(getColor(white));
            button5.setBackgroundColor(getColor(R.color.red));
            button5.setTextColor(getColor(white));
            button6.setBackgroundColor(getColor(R.color.red));
            button6.setTextColor(getColor(white));
            button7.setBackgroundColor(getColor(R.color.red));
            button7.setTextColor(getColor(white));
            button8.setBackgroundColor(getColor(R.color.red));
            button8.setTextColor(getColor(white));
            button9.setBackgroundColor(getColor(R.color.red));
            button9.setTextColor(getColor(white));
            themecolor.setBackgroundColor(getColor(R.color.red));
            themecolor.setTextColor(getColor(white));
            textView.setTextColor(getColor(white));
            PVP.setTextColor(getColor(white));
            PVI.setTextColor(getColor(white));
            a = 1;
        }
        else if (a == 1){
            background.setBackgroundResource(white);
            button1.setBackgroundColor(getColor(R.color.purple_500));
            button1.setTextColor(getColor(black));
            button2.setBackgroundColor(getColor(R.color.purple_500));
            button2.setTextColor(getColor(black));
            button3.setBackgroundColor(getColor(R.color.purple_500));
            button3.setTextColor(getColor(black));
            button4.setBackgroundColor(getColor(R.color.purple_500));
            button4.setTextColor(getColor(black));
            button5.setBackgroundColor(getColor(R.color.purple_500));
            button5.setTextColor(getColor(black));
            button6.setBackgroundColor(getColor(R.color.purple_500));
            button6.setTextColor(getColor(black));
            button7.setBackgroundColor(getColor(R.color.purple_500));
            button7.setTextColor(getColor(black));
            button8.setBackgroundColor(getColor(R.color.purple_500));
            button8.setTextColor(getColor(black));
            button9.setBackgroundColor(getColor(R.color.purple_500));
            button9.setTextColor(getColor(black));
            themecolor.setBackgroundColor(getColor(R.color.purple_500));
            themecolor.setTextColor(getColor(black));
            textView.setTextColor(getColor(black));
            PVP.setTextColor(getColor(black));
            PVI.setTextColor(getColor(black));
            a = 0;
        }
    }
}