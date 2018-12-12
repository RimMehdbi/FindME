package com.rim.asus.findme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Accueil_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PositionHelper ph = new PositionHelper(Accueil_Activity.this, PositionHelper.name,null, PositionHelper.version);
        SQLiteDatabase db = ph.getWritableDatabase() ;
        Cursor c = db.query(PositionHelper.table , new String[]{PositionHelper.col_Id ,PositionHelper.col_num,PositionHelper.col_lat,PositionHelper.col_lon},null,null,null,null,null) ;
        ((Cursor) c).moveToFirst();

        ArrayList<Position> data = new ArrayList<Position>() ;
        while (!((Cursor) c).isAfterLast())
        {

            int i = ((Cursor) c).getInt(0);
            String n1 = ((Cursor) c).getString(1) ;
            String n2 = ((Cursor) c).getString(2) ;
            String n3 = ((Cursor) c).getString(3) ;
            Position p = new Position(i,n1,n2,n3);
            data.add(p);
            ((Cursor) c).moveToNext() ;

        }

        ArrayAdapter ad = new ArrayAdapter(Accueil_Activity.this,android.R.layout.simple_list_item_1,data);

        ListView lv = findViewById(R.id.ls) ;
        lv.setAdapter(ad);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

               /* ContentValues values  = new ContentValues() ;
                values.put(PositionHelper.col_num , "97526930");
                values.put(PositionHelper.col_lon , "5206");
                values.put(PositionHelper.col_lat , "6665");

                long a =db.insert(PositionHelper.table,null,values);
                if (a>0)
                {

                    Toast.makeText(Accueil_Activity.this, "element inséré", Toast.LENGTH_SHORT).show();

                }

                else
                {

                    Toast.makeText(Accueil_Activity.this, "echec ", Toast.LENGTH_SHORT).show();


                }
               */

                Intent i = new Intent(Accueil_Activity.this,Ajout.class) ;
                startActivity(i);




            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        PositionHelper ph = new PositionHelper(Accueil_Activity.this, PositionHelper.name,null, PositionHelper.version);
        SQLiteDatabase db = ph.getWritableDatabase() ;

        Cursor c = db.query(PositionHelper.table , new String[]{PositionHelper.col_Id ,PositionHelper.col_num,PositionHelper.col_lat,PositionHelper.col_lon},null,null,null,null,null) ;
        ((Cursor) c).moveToFirst();

        ArrayList<Position> data = new ArrayList<Position>() ;
        while (!((Cursor) c).isAfterLast())
        {

            int i = ((Cursor) c).getInt(0);
            String n1 = ((Cursor) c).getString(1) ;
            String n2 = ((Cursor) c).getString(2) ;
            String n3 = ((Cursor) c).getString(3) ;
            Position p = new Position(i,n1,n2,n3);
            data.add(p);
            ((Cursor) c).moveToNext() ;

        }

        ArrayAdapter ad = new ArrayAdapter(Accueil_Activity.this,android.R.layout.simple_list_item_1,data);

        ListView lv = findViewById(R.id.ls) ;
        lv.setAdapter(ad);
        /*Intent i = new Intent(Accueil_Activity.this,Ajout.class) ;
        startActivity(i);*/



    }
}
