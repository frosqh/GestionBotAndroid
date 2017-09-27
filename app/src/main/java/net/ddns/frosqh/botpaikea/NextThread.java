package net.ddns.frosqh.botpaikea;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class NextThread implements Runnable{

    private final Activity acti;

    public NextThread(Activity act){
        acti = act;
    }

    @Override
    public void run() {
        Looper.prepare();
        MainActivity.ct.out.println("next");

        try {
            String[] playing = MainActivity.ct.getPlaying();
            Thread t2 = new Thread(new SoloPlayingThread(playing[0]+"\n"+playing[1]));
            acti.runOnUiThread(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
