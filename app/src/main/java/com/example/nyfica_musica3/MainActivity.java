package com.example.nyfica_musica3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ListView lv;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.txtTitle);

        lv = findViewById(R.id.list);
        String[] grupos = getResources().getStringArray(R.array.grupos);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.filas, grupos);
        lv.setAdapter(arrayAdapter);

        String[] canciones = getResources().getStringArray(R.array.canciones);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedGroup = (String) lv.getItemAtPosition(i);
                String selectedSong = canciones[i];
                tv.setText("Cancion: " + selectedSong + "\ndel grupo " + selectedGroup);
                int song = getResources().getIdentifier(selectedSong, "raw", getPackageName());
                mp = MediaPlayer.create(MainActivity.this, song);
                mp.seekTo(0);
                if (mp.isPlaying()) {
                    mp.stop();
                } else {
                    mp.start();
                }
            }
        });

    }
}