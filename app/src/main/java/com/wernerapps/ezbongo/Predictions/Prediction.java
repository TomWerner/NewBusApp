package com.wernerapps.ezbongo.Predictions;

import java.io.Serializable;

/**
 * Created by Tom on 3/28/2015.
 */
public class Prediction implements Serializable {
    public String title, tag, agency, agencyName, direction, stopname;
    public int minutes;

    public Prediction(){};

    public Prediction(String title, String tag, int minutes, String agency, String agencyName, String direction, String stopname)
    {
        this.title = title;
        this.tag = tag;
        this.minutes = minutes;
        this.agency = agency;
        this.agencyName = agencyName;
        this.direction = direction;
        this.stopname = stopname;
    }
}
