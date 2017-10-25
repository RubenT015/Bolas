package com.example.alumnom.bolas;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

/**
 * Created by alumnom on 24/10/2017.
 */

public class Bola {

    DisplayMetrics dm;
    ImageView bola;
    int velX , velY;

    public Bola(Context c, ConstraintLayout l, DisplayMetrics d){
        Random r = new Random();
        dm =d;
        bola = new ImageView(c);
        bola.setImageResource(R.drawable.ball_blue);
        bola.setMaxWidth(20);
        bola.setMaxHeight(20);
        l.addView(bola);
        bola.setX(r.nextInt(dm.widthPixels-bola.getWidth()));
        bola.setY(r.nextInt(dm.heightPixels-bola.getHeight()));

        velX=2;
        velY=2;
    }

    public void movimiento(int alturaBarraEstado){
        bola.setX(bola.getX()+velX);
        bola.setY(bola.getY()+velY);
        if(bola.getX()+bola.getWidth()>dm.widthPixels && velX>0||bola.getX()<0 && velX<0)
            velX = -velX;
        if(bola.getY()+bola.getHeight()>dm.heightPixels-alturaBarraEstado && velY>0||bola.getY()<0 && velY<0)
            velY = -velY;
    }
}
