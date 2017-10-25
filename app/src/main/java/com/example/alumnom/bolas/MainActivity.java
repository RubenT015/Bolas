package com.example.alumnom.bolas;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ArrayList<Bola> listBolas;
    long startTime = 0;
    DisplayMetrics display;
    int alturaBarraEstado;

    Handler timerHandlerTiempo = new Handler();
    Runnable timerRunnableTiempo = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            tv.setText(String.format("%d:%02d", minutes, seconds));
            timerHandlerTiempo.postDelayed(this, 500);
        }
    };

    Handler timerHandlerBolas = new Handler();
    Runnable timerRunnableBolas = new Runnable() {

        @Override
        public void run() {
            for(Bola b : listBolas)
                b.movimiento(alturaBarraEstado);

            timerHandlerBolas.postDelayed(this, 20);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            alturaBarraEstado = getResources().getDimensionPixelSize(resourceId);
        }
        tv = (TextView)findViewById(R.id.tv);
        startTime = System.currentTimeMillis();
        timerHandlerTiempo.postDelayed(timerRunnableTiempo, 0);
        timerHandlerBolas.postDelayed(timerRunnableBolas, 0);
        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        listBolas = new ArrayList<>();
        for(int i = 0 ;i<20;i++){
            listBolas.add(new Bola(this, (ConstraintLayout)findViewById(R.id.cLayout), display));
        }
    }
}
