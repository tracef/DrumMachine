package com.example.trace.drummachine;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

/**
 * Created by Trace on 11/25/2015.
 */

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "drumsDB.db";
    private static final String TABLE_DRUMS = "drums";
    private static final String COLUMN_ID = "id";//PK
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_PATH = "path";
    private static final String TABLE_DRUMKITS = "drumkits";
    private static final String COLUMN_KITNAME = "kitname";
    private static final String COLUMN_POSITION = "position";

    String CREATE_DRUMS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DRUMS + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY, " + COLUMN_TYPE + " TEXT NOT NULL, " + COLUMN_PATH
            + " TEXT NOT NULL" + ")";

    String CREATE_DRUMKITS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DRUMKITS + "(" + COLUMN_ID
            + " INTEGER NOT NULL, " + COLUMN_KITNAME + " TEXT NOT NULL, " + COLUMN_POSITION
            + " INTEGER NOT NULL)";

    String[] drumarray = {"kick_808", "snare_808", "hat_808", "clap_808", "rim_808", "maracas_808",
            "cowbell_808", "cymbal_808", "kick_c78", "snare_c78", "hat_c78", "cymbal_c78",
            "rim_c78", "tambourine_c78", "clave_c78", "conga_c78", "kick_echo", "snare_echo",
            "hat_echo", "crash_echo", "ride_echo", "conga_echo", "stick_echo", "cowbell_echo" };

    Integer[] drumIDs = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112 ,113, 114, 115,
            116, 117, 118, 119, 120, 121, 122, 123, 124};

    DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_DRUMS_TABLE);
        ContentValues values = new ContentValues();
        String drum;
        try
        {
            for (int i = 0; i < drumIDs.length; i++)
            {
                values.put(COLUMN_ID, drumIDs[i]);
                values.put(COLUMN_PATH, drumarray[i]);
                values.put(COLUMN_TYPE, drumarray[i].substring(0, drumarray[i].indexOf("_")).toString());
                db.insert(TABLE_DRUMS, null, values);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        db.execSQL(CREATE_DRUMKITS_TABLE);

        ContentValues values2 = new ContentValues();
        String kit1 = "808";
        String kit2 = "c78";
        String kit3 = "echo";
        int position = 1;
        try
        {
            for(int i = 0; i < drumarray.length;i++)
            {
                values2.put(COLUMN_ID, drumIDs[i]);
                drum = drumarray[i];
                if(drum.contains(kit1))
                {
                    values2.put(COLUMN_KITNAME, kit1);
                }
                else if(drum.contains(kit2))
                {
                    values2.put(COLUMN_KITNAME, kit2);
                }
                else if(drum.contains(kit3))
                {
                    values2.put(COLUMN_KITNAME, kit3);
                }
                if(position > 8)
                {
                    position = 1;
                }
                values2.put(COLUMN_POSITION, position);
                db.insert(TABLE_DRUMKITS, null, values2);
                position++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getKits()
    {
        ArrayList<String> drumkits = new ArrayList<>();
        String query = "SELECT DISTINCT " + TABLE_DRUMKITS + "." + COLUMN_KITNAME + " FROM " + TABLE_DRUMKITS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                drumkits.add(cursor.getString(0).toUpperCase());
            }
        }
        cursor.close();
        db.close();
        return drumkits;
    }

    public DrumKit getKit(String item)
    {
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + TABLE_DRUMS + "." + COLUMN_PATH + ", " + TABLE_DRUMS + "." + COLUMN_TYPE + " FROM " + TABLE_DRUMS + " INNER JOIN "
                + TABLE_DRUMKITS + " ON drums.id = drumkits.id AND " + TABLE_DRUMKITS + "." + COLUMN_KITNAME
                + " =\"" + item.toLowerCase() + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                paths.add(cursor.getString(0));
                types.add(cursor.getString(1));
            }
        }
        cursor.close();
        db.close();
        DrumKit drumKit = new DrumKit(paths, types);
        return  drumKit;
    }

    public DrumKit getDrums()
    {
        DrumKit drumKit = new DrumKit();
        ArrayList <String> drumNames = new ArrayList<>();
        ArrayList <Integer> drumIDs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DRUMS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                drumIDs.add(cursor.getInt(0));
                drumNames.add(cursor.getString(2).toUpperCase());
            }
        }
        cursor.close();
        db.close();
        drumKit.setDrumIDs(drumIDs);
        drumKit.setDrumPaths(drumNames);
        return drumKit;
    }

    public void saveKit(ArrayList <Integer> drumid, String kitName, ArrayList <Integer> position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < drumid.size(); i++)
        {
            contentValues.put(COLUMN_ID, drumid.get(i));
            contentValues.put(COLUMN_KITNAME, kitName.toLowerCase());
            contentValues.put(COLUMN_POSITION, position.get(i));
            db.insert(TABLE_DRUMKITS, null, contentValues);
        }
        db.close();
    }

    public boolean deleteKit(String kitName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DRUMKITS, COLUMN_KITNAME + " = " + "\"" + kitName.toLowerCase() + "\"", null) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUMKITS);
        onCreate(db);
    }
}