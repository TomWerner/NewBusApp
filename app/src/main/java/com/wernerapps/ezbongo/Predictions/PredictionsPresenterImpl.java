package com.wernerapps.ezbongo.Predictions;

import java.util.List;

/**
 * Created by Tom on 3/29/2015.
 */
public class PredictionsPresenterImpl implements PredictionsPresenter {
    private final PredictionsFragment predView;
    private final GetPredictionsImpl getPredictionsInteractor;

    public PredictionsPresenterImpl(PredictionsFragment predictionsFragment) {
        this.predView = predictionsFragment;
        getPredictionsInteractor = new GetPredictionsImpl();
    }

    @Override
    public void getPredictions(String stopId) {
        getPredictionsInteractor.getPrediction(stopId, new GetPredictions.OnFinishedListener() {
            @Override
            public void onSuccess(List<Prediction> predictions) {
                predView.setItems(predictions);
                predView.hideProgress();
            }

            @Override
            public void onFailure(String errorMessage) {
                predView.displayErrorMessage(errorMessage);
                predView.hideProgress();
            }
        });
    }

    @Override
    public void onItemClicked(Prediction prediction) {
        predView.showSetReminderDialog(prediction);
    }
}
