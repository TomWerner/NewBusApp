package com.wernerapps.ezbongo.DatabaseObjects;

import com.orm.SugarRecord;

/**
 * Created by Tom on 3/28/2015.
 */
public class RoutePath extends SugarRecord<RoutePath> {
    String tag;
    RoutePoint[] points;

    public RoutePath() {}

    public RoutePath(String tag, RoutePoint[] points)
    {
        this.tag = tag;
        this.points = points;
    }
}
