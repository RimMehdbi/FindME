package com.rim.asus.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PositionHelper extends SQLiteOpenHelper {
    public static final String table  ="Position" ;
    public static final String col_Id ="Identifiant" ;
    public static final String col_num="Numero";
    public static final String col_lon="Longitude" ;
    public static final String col_lat="Latitude";
    public static String name ="position.db" ;
    public static  int version = 1 ;
    String req = "create table "+table+"( "+col_Id+" integer primary Key AutoIncrement, "+col_num+" TextNotNull, "+col_lon+" TextNotNull, "+col_lat+" TextNotNull );" ;



    public PositionHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(req) ;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table "+table);
        db.execSQL(req);

    }
}
