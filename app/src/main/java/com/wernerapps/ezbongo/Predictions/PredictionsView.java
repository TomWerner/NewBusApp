package com.wernerapps.ezbongo.Predictions;

import java.util.List;

/**
 * Created by Tom on 3/29/2015.
 */
public interface PredictionsView {
    public void showProgress();
    public void hideProgress();
    public void setItems(List<Prediction> predictions);
    public void displayErrorMessage(String message);

    public void showSetReminderDialog(Prediction prediction);
}
