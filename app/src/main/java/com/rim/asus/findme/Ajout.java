package com.rim.asus.findme;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;


public class Ajout extends AppCompatActivity implements LocationListener{
    private LocationManager locationManager;
    String info;
    static final int PERMISSION_READ_STATE = 123;

    EditText e1;
    EditText e2;
    EditText e3;
    Button b ;
    Button b2 ;
    Button b3 ;
    double longitude = 0;
    double latitude =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        e1 = findViewById(R.id.ed_num);
        e2 = findViewById(R.id.ed_long);
        e3 = findViewById(R.id.ed_lat);
        b = findViewById(R.id.b_val);
        b3= findViewById(R.id.LocationB) ;
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               Intent i = new Intent(Ajout.this,MapsActivity.class) ;
                i.putExtra("long",longitude ) ;
                i.putExtra("lat",latitude) ;
                startActivity(i);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PositionHelper ph = new PositionHelper(Ajout.this, PositionHelper.name,null, PositionHelper.version);
                SQLiteDatabase db = ph.getWritableDatabase() ;
                ContentValues values  = new ContentValues() ;
                values.put(PositionHelper.col_num , e1.getText().toString());
                values.put(PositionHelper.col_lon , e2.getText().toString());
                values.put(PositionHelper.col_lat , e3.getText().toString());

                long a =db.insert(PositionHelper.table,null,values);
                if (a>0)
                {

                    Toast.makeText(Ajout.this, "element inséré", Toast.LENGTH_SHORT).show();



                }

                else
                {

                    Toast.makeText(Ajout.this, "echec ", Toast.LENGTH_SHORT).show();


                }

            }
        });
        b2= findViewById(R.id.b_qt);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ajout.this,Accueil_Activity.class) ;
                startActivity(i);

            }
        });
       locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permission == PackageManager.PERMISSION_GRANTED)
            MyTeLMan();
        else
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE

            );


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        if (location == null) {
            // request location update!!
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
        onLocationChanged(location);
       /* requestPermission();

        TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        EditText e1 = findViewById(R.id.ed_num);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)) {

            return;
        }
        String s = t.getLine1Number();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();


     /*   if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }*/
        // e1.setText(t.getLine1Number());

        //   client = LocationServices.getFusedLocationProviderClient(this);
  /*      if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        client.getLastLocation().addOnSuccessListener(Ajout.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                    EditText e1 = findViewById(R.id.ed_num);
                    EditText e2 = findViewById(R.id.ed_long);
                    EditText e3 = findViewById(R.id.ed_lat);
                    e2.setText("vvoila "+location.getLongitude());
                    e3.setText(""+location.getLatitude());

                    if (ActivityCompat.checkSelfPermission(Ajout.this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Ajout.this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Ajout.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                 //   e1.setText(t.getLine1Number());




                }
            }
        }) ;

*/


    }


 @Override
    public void onLocationChanged(Location location) {
         longitude = location.getLongitude();
         latitude = location.getLatitude();
        e2.setText(longitude + "");
        e3.setText(latitude + "");


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

   /* private void requestPermission()
    { ActivityCompat.requestPermissions(this, new String[]{ READ_PHONE_STATE}, 1);

    }
*/

    /*public void Start() {

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permission == PackageManager.PERMISSION_GRANTED)
            MyTeLMan();
        else
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE

            );


    }
*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    MyTeLMan();

            }
        }
    }

    public void MyTeLMan() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        boolean romaing = tm.isNetworkRoaming();
        if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String t = tm.getSimSerialNumber();
           e1.setText(t);

    }
}
