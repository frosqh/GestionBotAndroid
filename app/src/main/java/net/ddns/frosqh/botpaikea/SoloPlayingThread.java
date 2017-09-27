package net.ddns.frosqh.botpaikea;

import android.os.Looper;

import java.io.IOException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class PlayingThread implements Runnable{

    @Override
    public void run() {
        Looper.prepare();
        while(true) {
            try {
                String[] playing = MainActivity.ct.getPlaying();
                SecondActivity.isPlaying.setText(playing[0] + "\n" + playing[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                synchronized (this) {
                    this.wait(30 * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
