package com.wernerapps.ezbongo.BongoAPI;

import com.wernerapps.ezbongo.Predictions.Prediction;

import java.util.List;

/**
 * Created by Tom on 3/29/2015.
 */
public class PredictionsWrapper {
    public PredictionStopInfoWrapper stopinfo;
    public List<Prediction> predictions;

    public PredictionsWrapper() {};
    public PredictionsWrapper(PredictionStopInfoWrapper stopinfo, List<Prediction> predictions){
        this.stopinfo = stopinfo;
        this.predictions = predictions;
    }
}
