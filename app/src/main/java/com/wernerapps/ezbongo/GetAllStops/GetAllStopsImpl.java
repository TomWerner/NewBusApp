/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.wernerapps.ezbongo.GetAllStops;

import com.orm.StringUtil;
import com.wernerapps.ezbongo.BongoAPI.BongoService;
import com.wernerapps.ezbongo.BongoAPI.StopListingWrapper;
import com.wernerapps.ezbongo.BongoAPI.StopWrapper;
import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.StopListing.ExpandableSwipeAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetAllStopsImpl implements GetAllStops {
    @Override
    public void getStops(final OnFinishedListener listener, int groupPosition, int childPosition) {
        List<Stop> stops = Stop.listAll(Stop.class);
        if (stops.size() == 0)
            getStopListings(listener, groupPosition, childPosition);
        else
        {
            ArrayList<String> titles = new ArrayList<String>();
            ArrayList<List<Stop>> items = new ArrayList<List<Stop>>();

            addFavorites(titles, items);
            titles.add("Stops");
            items.add(stops);
            listener.onSuccess(titles, items, groupPosition, childPosition);
        }
    }

    private void getStopListings(final OnFinishedListener listener, final int groupPosition, final int childPosition) {
        RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(BongoService.API_URL)
        .build();

        BongoService service = restAdapter.create(BongoService.class);
        service.getAllStops(new Callback<StopListingWrapper>() {
            @Override
            public void success(StopListingWrapper wrapper, Response response) {
                ArrayList<String> titles = new ArrayList<String>();
                ArrayList<List<Stop>> items = new ArrayList<List<Stop>>();

                addFavorites(titles, items);
                titles.add("Stops");

                final ArrayList<Stop> stops = new ArrayList<Stop>(wrapper.stops.size());
                for (StopWrapper sw : wrapper.stops)
                    stops.add(sw.stop);
                items.add(stops);

                listener.onSuccess(titles, items, groupPosition, childPosition);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Iterator<Stop> iterator = Stop.findAll(Stop.class);
                        if (!iterator.hasNext()){
                            for (Stop stop : stops)
                                stop.save();
                        }
                        else {
                            while (iterator.hasNext()){
                                Stop stop = iterator.next();
                                if (stops.indexOf(stop) == -1)
                                    stop.save();
                            }
                        }
                    }
                }).start();

            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    listener.onFailure("Network Failure.\nTap to try again.");
                else
                    listener.onFailure(error.toString() + ",, " + error.getKind());
            }
        });
    }

    private void addAllStops(Iterator<Stop> iterator, ArrayList<String> titles, ArrayList<List<Stop>> items) {
        titles.add("Stops");
        ArrayList<Stop> stops = new ArrayList<>();
        while (iterator.hasNext())
        {
            Stop stop = iterator.next();
            System.out.println(stop);
            stops.add(stop);
        }
        items.add(stops);
    }

    private void addFavorites(ArrayList<String> titles, ArrayList<List<Stop>> items) {
        List<Stop> favorites = Stop.find(Stop.class, StringUtil.toSQLName("isFavorited") + " = ?", "1");
        if (favorites.size() > 0){
            titles.add("Favorites");
            items.add(favorites);
        }
    }
}
