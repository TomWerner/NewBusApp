package com.wernerapps.ezbongo.StopListing;

import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.GetAllStops.GetAllStops;
import com.wernerapps.ezbongo.GetAllStops.GetAllStopsImpl;
import com.wernerapps.ezbongo.Predictions.GetPredictions;
import com.wernerapps.ezbongo.Predictions.GetPredictionsImpl;
import com.wernerapps.ezbongo.Predictions.Prediction;

import java.util.List;

/**
 * Created by Tom on 3/26/2015.
 */
public class StopPresenterImpl implements StopPresenter {

    private final StopView stopView;
    private final GetAllStops getAllStopsInteractor;
    private final GetPredictions getPredictionsInteractor;

    public StopPresenterImpl(StopView view)
    {
        this.stopView = view;
        getAllStopsInteractor = new GetAllStopsImpl();
        getPredictionsInteractor = new GetPredictionsImpl();
    }

    @Override
    public void loadStopListing(int groupPos, int childPos) {
        stopView.showProgress();
        getAllStopsInteractor.getStops(new GetAllStops.OnFinishedListener() {
            @Override
            public void onSuccess(List<String> titles, List<List<Stop>> items, int groupPos, int childPos) {
                stopView.setItems(titles, items, groupPos, childPos);
                stopView.hideProgress();
            }

            @Override
            public void onFailure(String errorMessage) {
                stopView.displayErrorMessage(errorMessage);
                stopView.hideProgress();
            }
        }, groupPos, childPos);
    }

    @Override
    public void onItemClicked(ItemAction action, int groupPos, int childPos, Stop stop) {
        System.out.println(action);
        if (action.equals(ItemAction.INCOMING_TIMES)){
            stopView.openPredictionFragment(stop);
        }
        else if (action.equals(ItemAction.FAVORITE)){
            stop.isFavorited = !stop.isFavorited;
            stop.save();
            loadStopListing(groupPos, childPos);
        }
    }
}
