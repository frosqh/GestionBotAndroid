package net.ddns.frosqh.botpaikea;

import android.view.View;

/**
 * Created by Frosqh on 19/09/2017.
 */

public class ChangeLoadingThread implements Runnable{

    private final boolean b2;

    public ChangeLoadingThread(Boolean b){
       b2=b;
    }

    @Override
    public void run() {
        if (b2) {
            MainActivity.loading.setVisibility(View.VISIBLE);
        } else{
            MainActivity.loading.setVisibility(View.GONE);
        }

    }

}
