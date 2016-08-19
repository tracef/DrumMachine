package com.example.trace.drummachine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Trace on 11/25/2015.
 */
public class CreateKit extends AppCompatActivity
{
    ArrayList <String> drumNames = new ArrayList<>(8);
    ArrayList <Integer> positionList = new ArrayList<>(8);
    Spinner[] spinners = new Spinner[8];
    ArrayList<Integer> drumid = new ArrayList<>(8);
    int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kit);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        final TextView statusText = (TextView) findViewById(R.id.statusText);
        final EditText kitName = (EditText) findViewById(R.id.editText);
        final DBHandler dbHandler = new DBHandler(this, null, null, 1);
        final DrumKit drumKit = dbHandler.getDrums();
        drumNames = drumKit.getDrumPaths();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_gallery_item, drumNames);
        spinners[0] = (Spinner) findViewById(R.id.spinner);
        spinners[1] = (Spinner) findViewById(R.id.spinner2);
        spinners[2] = (Spinner) findViewById(R.id.spinner3);
        spinners[3] = (Spinner) findViewById(R.id.spinner4);
        spinners[4] = (Spinner) findViewById(R.id.spinner5);
        spinners[5] = (Spinner) findViewById(R.id.spinner6);
        spinners[6] = (Spinner) findViewById(R.id.spinner7);
        spinners[7] = (Spinner) findViewById(R.id.spinner8);
        for(int i = 0; i < spinners.length; i++)
        {
            spinners[i].setAdapter(adapter);
            spinners[i].setSelection(0);
        }
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if(kitName.getText().toString().matches("")
                            || kitName.getText().toString().contains(" "))
                    {
                        statusText.setText("Please enter a kit name.");
                    }
                    else
                    {
                        for(int i = 0; i < spinners.length; i++)
                        {
                            selectedIndex = spinners[i].getSelectedItemPosition();
                            drumid.add(drumKit.getDrumID(selectedIndex));
                            positionList.add(i+1);
                        }
                        dbHandler.saveKit(drumid, kitName.getText().toString(), positionList);
                        statusText.setText("Kit Created.");
                    }
                }
                catch (Exception e)
                {
                    statusText.setText("Kit was not created.");
                }
            }
        });
    }
}