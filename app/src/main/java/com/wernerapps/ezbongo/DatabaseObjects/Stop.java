package com.wernerapps.ezbongo.DatabaseObjects;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

/**
 * Created by Tom on 3/28/2015.
 */
public class Stop extends SugarRecord<Stop> {
    @Ignore
    public List<Route> routes;
    @Ignore
    public String stopid;
    @Ignore
    public String latitude;
    @Ignore
    public String longitude;


    public String stopnumber;
    public String stoptitle;
    public String stoplat;
    public String stoplng;
    public boolean isFavorited;

    public Stop(){
    }

    public Stop(String stopnumber, String stoptitle, String stoplat, String stoplng)
    {
        this.stopnumber = stopnumber;
        this.stoptitle = stoptitle;
        this.stoplat = stoplat;
        this.stoplng = stoplng;
    }

    public Stop(String stopid, String stoptitle, String latitude, String longitude, List<Route> routes)
    {
        this.stopid = stopid;
        this.stoptitle = stoptitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routes = routes;
    }

    public String toString()
    {
        return "Stop: " + stopnumber + " - " + stoptitle + " - ( " + stoplat + " , " + stoplng + " )";
    }

    public String getStopName() {
        return stoptitle;
    }

    public String getStopId(){
        return stopnumber;
    }
}
