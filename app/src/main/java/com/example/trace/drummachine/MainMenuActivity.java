package com.example.trace.drummachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/* Program: Drum Machine
   Programmer: Trace Flynn
   CNIT355 Prof. Smith
 */
public class MainMenuActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void playDrums(View view)
    {
        Intent intent = new Intent(this, DrumMachineActivity.class);
        startActivity(intent);
    }
    public void createDrums(View view)
    {
        Intent intent = new Intent(this, CreateKit.class);
        startActivity(intent);
    }
    public void deleteDrumKit(View view)
    {
        Intent intent = new Intent(this, DeleteKitActivity.class);
        startActivity(intent);
    }
}