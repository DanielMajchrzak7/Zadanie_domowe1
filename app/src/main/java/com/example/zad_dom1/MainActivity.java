package com.example.zad_dom1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button buttonChangeContact;
    private Button buttonChangeSound;
    private TextView nazwiskoView;
    private FloatingActionButton playButton;
    private final int CHANGE_CONTACT = 0;
    private final int CHANGE_SOUND = 1;
    private ImageView imageView;
    private int current_sound = 0;
    public static final String NAZWISKO = "nazwisko";
    public static final String RING = "ring";
    private Uri[] sounds = new Uri[3];
    private static MediaPlayer player;

    int[] avatars = {R.drawable.lewy, R.drawable.nosacz, R.drawable.slawek};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChangeContact = (Button) findViewById(R.id.button);
        buttonChangeSound = (Button) findViewById(R.id.button2);
        nazwiskoView = (TextView) findViewById(R.id.contact_name);
        playButton = (FloatingActionButton) findViewById(R.id.playButton);

        sounds[0]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.mario);
        sounds[1]  =  Uri.parse("android.resource://"  +  getPackageName()  +  "/"  + R.raw.ring01);
        sounds[2]  =  Uri.parse("android.resource://"  + getPackageName()  +  "/"  + R.raw.ring02);

        try {
            player = new MediaPlayer();
            //player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(this, sounds[current_sound]);
            player.prepare();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { //na grafie przejście ze Started do PlaybackCompleted
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                }
            });
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        buttonChangeContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, change_contact.class);
                startActivityForResult(intent, CHANGE_CONTACT);
            }
        });

        buttonChangeSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, change_sound.class);
                startActivityForResult(intent, CHANGE_SOUND);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying())
                {
                    player.stop();
                    try
                    {
                        player.prepare();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else player.start();
            }
        });
    }

    @Override
    public void onStop()
    {
        super.onStop();
        player.stop();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        player.release();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHANGE_CONTACT) {
                String nazwisko = data.getStringExtra(NAZWISKO);
                nazwiskoView.setText(nazwisko);
                if((nazwisko).equals(getString(R.string.robert_lewandowski)))
                {
                    imageView = findViewById(R.id.image_view);
                    imageView.setImageResource(avatars[0]);
                }
                if((nazwisko).equals(getString(R.string.janusz_polak)))
                {
                    imageView = findViewById(R.id.image_view);
                    imageView.setImageResource(avatars[1]);
                }
                if(((String) nazwisko).equals(getString(R.string.jan_kowalsky)))
                {
                    imageView = findViewById(R.id.image_view);
                    imageView.setImageResource(avatars[2]);
                }
            }
            else if(requestCode==CHANGE_SOUND)
            {
                current_sound = data.getIntExtra(RING, 0);
                try
                {
                    player.reset();
                    player.setDataSource(this, sounds[current_sound]);
                    player.prepare();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
