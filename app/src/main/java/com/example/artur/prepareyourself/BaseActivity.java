package com.example.artur.prepareyourself;

import android.support.v7.app.AppCompatActivity;

import com.example.artur.prepareyourself.System.Music;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        Music.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Music.start();
    }
}
