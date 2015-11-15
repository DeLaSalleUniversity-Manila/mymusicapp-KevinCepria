package ph.edu.dlsu.mymusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private String[] filenames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupPlaylist();
    }


    private void setupPlaylist() {
        ListView songs = (ListView) findViewById(R.id.listView);
        filenames = getResources().getStringArray(R.array.filenames);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.listitem, filenames);
        songs.setAdapter(adapter);

       songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
               Intent intent = new Intent(MainActivity.this, MusicService.class);
               intent.putExtra("filename", filenames[index]);
               intent.setAction(MusicService.ACTION_PLAY);
               startService(intent);

           }
       });

        Button stop=(Button)findViewById(R.id.button);
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickStop();
                    }
                });
    }


    public void onClickStop() {
        Intent intent = new Intent(this, MusicService.class);
        intent.setAction(MusicService.ACTION_STOP);
        startService(intent);
    }
}