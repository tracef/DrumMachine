package com.example.trace.drummachine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.io.IOException;
import java.util.ArrayList;

public class DrumMachineActivity extends AppCompatActivity
{
    Context context = this;
    SoundPool.Builder spNew;
    SoundPool sp;
    ArrayList<String> drumkits = new ArrayList<>(0);
    ArrayList<String> drumList = new ArrayList<>(0);
    ArrayList<String> drumTypes = new ArrayList<>(0);
    ArrayAdapter<String> adapter;
    int drum[] = new int[8];
    Button[] buttons = new Button[8];

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum_machine);
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        //Determine Device API to create SoundPool
        if ((android.os.Build.VERSION.SDK_INT) == 21)
        {
            spNew = new SoundPool.Builder();
            spNew.setMaxStreams(5);
            spNew.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            sp = spNew.build();
        }
        else
        {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        //Populate Spinner with DrumKits
        final Spinner kitsBox = (Spinner) findViewById(R.id.kitsBox);
        final DBHandler dbHandler = new DBHandler(this, null, null, 1);
        drumkits = dbHandler.getKits();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, drumkits);
        kitsBox.setAdapter(adapter);
        final AssetManager am = context.getResources().getAssets();
        kitsBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            //Determine drum paths onItemSelected
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item = kitsBox.getSelectedItem().toString();
                DrumKit drumKit = dbHandler.getKit(item);
                drumList = drumKit.getDrumPaths();
                drumTypes = drumKit.getDrumTypes();
                for (int i = 0; i < 8; i++)
                {
                    buttons[i].setText(drumTypes.get(i));
                    //Load Drums into SoundPool
                    try
                    {
                        drum[i] = sp.load(am.openFd(drumList.get(i) + ".wav"), 1);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //Set default kit selected
        kitsBox.setSelection(0);
        //Set Button OnClick Events
        buttons[0].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[0], 1, 1, 0, 0, 1);
                    buttons[0].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[0].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[1].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[1], 1, 1, 0, 0, 1);
                    buttons[1].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[1].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[2].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[2], 1, 1, 0, 0, 1);
                    buttons[2].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[2].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[3].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[3], 1, 1, 0, 0, 1);
                    buttons[3].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[3].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[4].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[4], 1, 1, 0, 0, 1);
                    buttons[4].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[4].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[5].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[5], 1, 1, 0, 0, 1);
                    buttons[5].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[5].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[6].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[6], 1, 1, 0, 0, 1);
                    buttons[6].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[6].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
        buttons[7].setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    sp.play(drum[7], 1, 1, 0, 0, 1);
                    buttons[7].setBackground(getDrawable(R.drawable.drumhit));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    buttons[7].setBackground(getDrawable(R.drawable.drum));
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drum_machine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}