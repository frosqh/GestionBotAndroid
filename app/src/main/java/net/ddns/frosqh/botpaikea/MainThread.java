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
            Thread waitThread = new Thread(new WaitThread(this));
            waitThread.start();
            synchronized(this){
                wait();
            }
            waitThread.interrupt();
            Thread t2 = new Thread(new ChangeLoadingThread(false));
            main.runOnUiThread(t2);
            main.next();
        } catch (UnknownHostException e) {
            Thread t2 = new Thread(new ChangeLoadingThread(false));
            main.runOnUiThread(t2);
            MainActivity.showError(e.getMessage());
        } catch (InterruptedException e) {
            Thread t2 = new Thread(new ChangeLoadingThread(false));
            main.runOnUiThread(t2);
            e.printStackTrace();
        }
    }

    public void bite() {
        Thread t2 = new Thread(new ChangeLoadingThread(false));
        main.runOnUiThread(t2);
        MainActivity.showError("Server not connected");
    }
}
