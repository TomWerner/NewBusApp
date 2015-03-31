package com.wernerapps.ezbongo.DatabaseObjects;

import com.orm.SugarRecord;

/**
 * Created by Tom on 3/28/2015.
 */
public class Route extends SugarRecord<Route>{

    String name;
    String tag;
    String agency;
    String agencyname;
    double max_lat, max_lng, min_lat, min_lng;
    RoutePath[] paths;
    String[] messages;
    RouteDirection[] directions;

    public Route(){
    }

    public Route(String name, String tag, String agency)
    {
        this.name = name;
        this.tag = tag;
        this.agency = agency;
    }

    public Route(String name, String tag, String color, double max_lat, double max_lng, double min_lat, double min_lng, String agencyname, String agencytag, RoutePath[] paths, RouteDirection[] directions, String[] messages)
    {
        this.name = name;
        this.tag = tag;
        this.agency = agencytag;
        this.max_lat = max_lat;
        this.max_lng = max_lng;
        this.min_lat = min_lat;
        this.min_lng = min_lng;
        this.agencyname = agencyname;
        this.paths = paths;
        this.directions = directions;
        this.messages = messages;
    }
}
