package com.wernerapps.ezbongo.Predictions;

import com.wernerapps.ezbongo.DatabaseObjects.Stop;

import java.util.List;

/**
 * Created by Tom on 3/29/2015.
 */
public interface GetPredictions {
    public void getPrediction(String stopId, OnFinishedListener listener);

    public interface OnFinishedListener {
        void onSuccess(List<Prediction> predictions);
        void onFailure(String errorMessage);
    }
}
