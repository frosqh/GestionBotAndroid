package net.ddns.frosqh.botpaikea;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.net.UnknownHostException;

/**
 * Created by Admin on 01/06/2017.
 */

public class ClientTask extends AsyncTask<String, Integer, Long>{
    private final String ip;

    public ClientTask(String ip) {
        this.ip = ip;
    }


    @Override
    protected Long doInBackground(String... params) {
        Log.d("DEBUG_CONNECT", "CONNECT");
        Looper.prepare();
        //Thread ma = new Thread(new MainThread(ip));
       // ma.run();
        return null;
    }
}
