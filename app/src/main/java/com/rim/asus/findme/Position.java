package com.rim.asus.findme;

public class Position {

   public  int id ;
   public  String num ;
   public  String lon ;
   public  String Lat ;

    public Position(int id, String num, String lon, String lat) {
        this.id = id;
        this.num = num;
        this.lon = lon;
        Lat = lat;
    }


    @Override
    public String toString() {
        return
                "id='" + id + '\'' + "\n"+
                "numéro='" + num + '\'' +"\n"+
                "longitude='" + lon + '\'' +"\n"+
                "Latitude='" + Lat
                ;
    }
}
