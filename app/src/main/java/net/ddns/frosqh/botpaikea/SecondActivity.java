package net.ddns.frosqh.botpaikea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    boolean isUsed;
    public static TextView isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isUsed = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        TextView isPlaying = (TextView) findViewById(R.id.isPlaying);
        this.isPlaying = isPlaying;

        Thread t2 = new Thread(new PlayingThread(this));
        t2.start();

        final ListView list = (ListView) findViewById(R.id.Song_choice);
        final String [] artists = new String[MainActivity.getMapSong().size()];
        MainActivity.getMapSong().keySet().toArray(artists);
        //SortedMap<String, ArrayList<String>> mapSong = MainActivity.getMapSong();
        //mapSong.key

        list.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, artists));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isUsed) {
                    ArrayList<String> songs = MainActivity.getMapSong().get(list.getItemAtPosition(position));
                    String[] song2 = new String[songs.size()];
                    list.setAdapter(new ArrayAdapter<String>(SecondActivity.this, R.layout.list_item, songs.toArray(song2)));
                    isUsed = true;
                    Button back = (Button) findViewById(R.id.back);
                    back.setVisibility(View.VISIBLE);
                    return;
                }
                Thread t = new Thread(new VlaThread((String) list.getItemAtPosition(position), SecondActivity.this));
                t.start();
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setVisibility(View.INVISIBLE);

        Log.d("DEBUG_TOUT", String.valueOf(back));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.setAdapter(new ArrayAdapter<String>(SecondActivity.this, R.layout.list_item, artists));
                isUsed = false;
                Button back = (Button) findViewById(R.id.back);
                back.setVisibility(View.INVISIBLE);
            }
        });

        Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new NextThread(SecondActivity.this));
                t.start();
            }
        });

        Button ppButton = (Button) findViewById(R.id.playPause);
        ppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new PlayPauseThread(SecondActivity.this));
                t.start();
            }
        });


    }

    public static void setText(String s) {
        isPlaying.setText(s);

    }
}
