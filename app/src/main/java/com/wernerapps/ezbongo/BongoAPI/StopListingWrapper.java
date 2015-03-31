package com.wernerapps.ezbongo.BongoAPI;

import java.util.List;

/**
 * Created by Tom on 3/28/2015.
 */
public class StopListingWrapper {
    public List<StopWrapper> stops;
    public StopListingWrapper() {}
    public StopListingWrapper(List<StopWrapper> stops){
        this.stops = stops;
    }
}
