package net.ddns.frosqh.botpaikea;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.SortedMap;

public class MainActivity extends AppCompatActivity {

    private static boolean isToasted;
    private static Toast actualToast;

    private static SortedMap<String,ArrayList<String>> mapSong = null;
    public static View view;
    public static ClientThread ct;
    private Thread ma;
    public static ProgressBar loading;
    //private ClientTask cp;

    public static SortedMap<String, ArrayList<String>> getMapSong() {
        return mapSong;
    }

    public static void setMapSong(SortedMap<String, ArrayList<String>> mapSong) {
        MainActivity.mapSong = mapSong;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File fav = new File("favorite");

        loading = (ProgressBar) findViewById(R.id.progressBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button connect = (Button) findViewById(R.id.button);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG_CONNECT", "Connect :p");
                view = v;
                String ip = ((EditText) findViewById(R.id.editText)).getText().toString();
                Log.d("DEBUG_CONNECT", "Def MA");
                ProgressBar loading = (ProgressBar) findViewById(R.id.progressBar);
                loading.setVisibility(View.VISIBLE);
                Thread t = new Thread(new MainThread(ip, MainActivity.this));
                t.start();
                /*if (cp != null){
                    System.out.println(cp.getStatus());
                    cp.cancel(true);
                    System.out.println(cp.getStatus());
                }
                cp = new ClientTask(ip);
                cp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);*/
            }
        });
    }


    public void next(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public static void showError(String text){
        System.out.println("On est bien là");
        Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
        actualToast = toast;
        Looper.loop();
    }
}
