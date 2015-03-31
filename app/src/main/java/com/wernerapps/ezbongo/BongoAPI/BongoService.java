package com.wernerapps.ezbongo.BongoAPI;

import com.wernerapps.ezbongo.DatabaseObjects.Route;
import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.Predictions.Prediction;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Tom on 3/28/2015.
 */
public interface BongoService {
    public static final String API_URL = "http://api.ebongo.org";
    public static final String API_KEY = "hfjiLcSjgJUQnhqn1WpGu4AfKBZX7Eo1";

    @GET("/stoplist?format=json&api_key=" + API_KEY)
    void getAllStops(Callback<StopListingWrapper> cb);

    @GET("/stop?format=json&api_key=" + API_KEY)
    void getStopDetails(@Query("stopid") String stopId, Callback<Stop> cb);

    @GET("/routelist?format=json&api_key=" + API_KEY)
    List<Route> getAllRoutes();

    @GET("/route?format=json&agency={agency}&route={routeTag}&api_key=" + API_KEY)
    void getRouteDetails(@Path("agency") String agency, @Path("routeTag") String routeTag, Callback<Route> cb);

    @GET("/prediction?format=json&api_key=" + API_KEY)
    void getPredictions(@Query("stopid") String stopId, Callback<PredictionsWrapper> cb);
}
