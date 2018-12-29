package com.example.artur.prepareyourself.System;

import android.content.Context;
import android.media.MediaPlayer;

public class Music {

    public static MediaPlayer player;

    public static void createMusic(Context context, int songid)
    {
        player = MediaPlayer.create(context,songid);

    }

    public static void stop()
    {
        player.stop();
    }

    public static void start()
    {
        if(!player.isPlaying())
        {
            player.start();
        }

    }

    public static void pause()
    {
        if(player.isPlaying())
        {
            player.pause();
        }
    }
}
