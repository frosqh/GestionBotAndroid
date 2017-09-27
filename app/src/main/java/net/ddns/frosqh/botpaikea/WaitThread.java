package net.ddns.frosqh.botpaikea;

import android.app.Activity;
import android.os.Looper;

import java.io.IOException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class VlaThread implements Runnable{

    private final Activity acti;
    private String song;

    public VlaThread(String song, Activity act){
        this.song = song;
        acti = act;
    }

    @Override
    public void run() {
        Looper.prepare();
        MainActivity.ct.out.println("vlalachanson" + song);
        try {
            String receivedData;
            while((receivedData = MainActivity.ct.in.readLine())==null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String[] playing = MainActivity.ct.getPlaying();
            Thread t2 = new Thread(new SoloPlayingThread(playing[0]+"\n"+playing[1]));
            acti.runOnUiThread(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
