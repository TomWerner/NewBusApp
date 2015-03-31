package com.wernerapps.ezbongo.StopListing;

import android.content.Context;

import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.Predictions.Prediction;

import java.util.List;

/**
 * Created by Tom on 3/25/2015.
 * This is the abstract
 */
public interface StopView {

    public void showProgress();

    public void hideProgress();

    public void setItems(List<String> titles, List<List<Stop>> items, int groupPos, int childPos);
    public void setItems(List<String> titles, List<List<Stop>> items);

    public void showMessage(String message);

    public void displayErrorMessage(String errorMessage);

    public Context getContext();

    public void openPredictionFragment(Stop stop);
}
