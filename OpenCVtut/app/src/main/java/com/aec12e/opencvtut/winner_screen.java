package com.aec12e.opencvtut;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

public class winner_screen extends AppCompatActivity {

    private int winner;
    private Animation show;
    private TextView winner_text;
    private ImageView winner_icon;
    private long mP1addr;
    private long mP2addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);
        Intent intent = getIntent();
        winner = intent.getIntExtra("winner",0);
        mP1addr = intent.getLongExtra("mP1addr",0);
        mP2addr = intent.getLongExtra("mP2addr",0);
        Mat tmpImg1 = new Mat(mP1addr);
        Mat tmpImg2 = new Mat(mP2addr);
        Mat mP1 = tmpImg1.clone();
        Mat mP2 = tmpImg2.clone();
        Bitmap p1Bmp = Bitmap.createBitmap(mP1.cols(), mP1.rows(), Bitmap.Config.ARGB_8888);
        Bitmap p2Bmp = Bitmap.createBitmap(mP2.cols(), mP2.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mP1, p1Bmp);
        Utils.matToBitmap(mP2, p2Bmp);
        ImageView p1Img = (ImageView) findViewById(R.id.p1Img);
        ImageView p2Img = (ImageView) findViewById(R.id.p2Img);
        p1Img.setImageBitmap(p1Bmp);
        p2Img.setImageBitmap(p2Bmp);
        winner_text = (TextView) findViewById(R.id.winner_text);
        winner_icon = (ImageView) findViewById(R.id.winner_icon);
        winner_icon.setImageResource(R.mipmap.logo);
        if (winner == 0)
        {
            winner_text.setText(R.string.p1winner);

        }
        else
        {
            winner_text.setText(R.string.p2winner);
        }

        show = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_grow_fade_in_from_bottom);
        show.setStartOffset(600);

        winner_text.setVisibility(View.VISIBLE);
        winner_text.startAnimation(show);
    }

    public void restartGame(View v) {
        Intent intent = new Intent(getBaseContext(),chooser.class);
        this.startActivity(intent);
    }


}
