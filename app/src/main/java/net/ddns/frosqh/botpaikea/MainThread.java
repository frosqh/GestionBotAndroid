package net.ddns.frosqh.botpaikea;

import android.os.Looper;
import android.util.Log;

import java.net.UnknownHostException;

/**
 * Created by Admin on 02/06/2017.
 */

public class MainThread implements Runnable{

    private final String ip;
    private final MainActivity main;

    public MainThread(String ip, MainActivity ma){
        this.ip = ip;
        this.main = ma;
    }
    @Override
    public void run() {
        Looper.prepare();
        Log.d("DEBUG_CONNECT", "Après prepare");
        ClientThread ct;
        try {
            MainActivity.setMapSong(null);
            ct = new ClientThread(ip, this);
            MainActivity.ct = ct;
            new Thread(ct).start();
            synchronized(this){
                wait();
            }
            main.next();
        } catch (UnknownHostException e) {
            MainActivity.showError(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
