package net.ddns.frosqh.botpaikea;

import android.app.Activity;
import android.os.Looper;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class WaitThread implements Runnable{

    private final MainThread acti;

    public WaitThread(MainThread act){
        acti = act;
    }

    @Override
    public void run(){
        Looper.prepare();
        try {
            synchronized (this){
                wait(15000);
                throw new UnknownHostException("Server not connected");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            acti.bite();
        }

    }

}
