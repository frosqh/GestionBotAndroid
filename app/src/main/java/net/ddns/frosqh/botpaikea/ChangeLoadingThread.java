package net.ddns.frosqh.botpaikea;

import android.os.Looper;

import java.io.IOException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class SoloPlayingThread implements Runnable{

    private final String text2;

    public SoloPlayingThread(String text){
       text2 = text;
    }

    @Override
    public void run() {
        SecondActivity.isPlaying.setText(text2);
    }

}
