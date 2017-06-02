package net.ddns.frosqh.botpaikea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        ListView list = (ListView) findViewById(R.id.Song_choice);
        String [] artists = new String[MainActivity.getMapSong().size()];
        MainActivity.getMapSong().keySet().toArray(artists);
        //SortedMap<String, ArrayList<String>> mapSong = MainActivity.getMapSong();
        //mapSong.key

        list.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, artists));


    }

}
