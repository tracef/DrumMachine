package com.example.trace.drummachine;

import java.util.ArrayList;
/**
 * Created by Trace on 11/25/2015.
 */
public class DrumKit
{
    public static ArrayList<String> drumPaths;
    public static ArrayList<Integer> drumID;
    public static ArrayList<String> drumTypes;

    public DrumKit(ArrayList<String> drumPaths, ArrayList<String> drumTypes)
    {
        this.drumPaths = drumPaths;
        this.drumTypes = drumTypes;
    }

    public DrumKit()
    {

    }

    public ArrayList<String> getDrumPaths()
    {
        return this.drumPaths;
    }

    public ArrayList<String> getDrumTypes()
    {
        return this.drumTypes;
    }

    public Integer getDrumID(int id)
    {
        return this.drumID.get(id);
    }

    public void setDrumIDs(ArrayList <Integer> drumIDs)
    {
        this.drumID = drumIDs;
    }

    public void setDrumPaths(ArrayList<String> drumPaths)
    {
        this.drumPaths = drumPaths;
    }
}