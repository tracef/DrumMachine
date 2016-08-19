package com.example.trace.drummachine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Trace on 12/4/2015.
 */

public class DeleteKitActivity extends AppCompatActivity
{
    ArrayList <String> drumKits = new ArrayList<>();
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_kit);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        final Spinner kitBox = (Spinner) findViewById(R.id.kitBox2);
        final TextView statusText = (TextView) findViewById(R.id.statusText2);
        final DBHandler dbHandler = new DBHandler(this, null,  null, 1);
        drumKits = dbHandler.getKits();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, drumKits);
        kitBox.setAdapter(adapter);
        deleteButton.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean bool = dbHandler.deleteKit(kitBox.getSelectedItem().toString());
                if(bool == true)
                {
                    adapter.clear();
                    drumKits = dbHandler.getKits();
                    for (int i = 0; i < drumKits.size(); i++)
                    {
                        adapter.add(drumKits.get(i));
                    }
                    statusText.setText("Kit Deleted Successful.");
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    statusText.setText("Error: Kit delete unsuccessful.");
                }
            }
        });
    }
}