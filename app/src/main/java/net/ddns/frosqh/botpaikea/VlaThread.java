package net.ddns.frosqh.botpaikea;

import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class NextThread implements Runnable{

    @Override
    public void run() {
        Looper.prepare();
        MainActivity.ct.out.println("next");
    }

}
