package com.example.mealsforu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "WordsDefinitionsList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "WordsAndMeanings";
    private  static final String COLUMN_ID = "ID";
    private static final String COLUMN_WORD = "Word";
    private static final String COLUMN_MEANING = "Meaning";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WORD + " TEXT, " + COLUMN_MEANING + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addWord(String word, String meaning){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_WORD,word);
        contentValues.put(COLUMN_MEANING,meaning);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            Toast.makeText(context,"Failed.",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(context,"Successfully.",Toast.LENGTH_SHORT);
        }
    }


    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
