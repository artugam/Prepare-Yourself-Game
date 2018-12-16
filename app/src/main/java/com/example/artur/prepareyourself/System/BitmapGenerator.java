package com.example.artur.prepareyourself.System;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class BitmapGenerator {

    public static int amount = 255;
    public Bitmap bitmap1;
    public Bitmap bitmap2;
    public ImageView img;
    public static AppCompatActivity app;

    public BitmapGenerator(Bitmap bitmap1, Bitmap bitmap2, ImageView img)
    {
        this.bitmap1 = bitmap1;
        this.bitmap2 = bitmap2;
        this.img = img;
    }

    public void run(AppCompatActivity application)
    {

        app = application;
        final long changeTime = 20;

        final Thread thread = new Thread(){
            @Override
            public void run() {

                while (!isInterrupted())
                {
                    try{
                        Thread.sleep(changeTime);

                        app.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                amount -= 10;
                                amount = amount < 0 ? 0 : amount;
                                Bitmap mergedImg = overlayBitmapToCenter(bitmap1, bitmap2, amount);
                                img.setImageBitmap(mergedImg);

                                if(amount == 0)
                                {
                                    interrupt();
                                }
                            }
                        });

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }

    public Bitmap overlayBitmapToCenter(Bitmap bitmap1, Bitmap overlayBitmap, int alpha ) {

        Paint paint = new Paint();
        paint.setAlpha(alpha);
        Bitmap bmOverlay = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getWidth(), bitmap1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bitmap1, null, new Rect(0,0,bitmap1.getWidth(),bitmap1.getHeight()), null);
        canvas.drawBitmap(overlayBitmap, null, new Rect(0,0,bitmap1.getWidth(),bitmap1.getHeight()), paint);
        return bmOverlay;

    }
}
