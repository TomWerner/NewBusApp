package com.wernerapps.ezbongo.Predictions;

import com.wernerapps.ezbongo.BongoAPI.BongoService;
import com.wernerapps.ezbongo.BongoAPI.PredictionsWrapper;
import com.wernerapps.ezbongo.DatabaseObjects.Stop;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Tom on 3/29/2015.
 */
public class GetPredictionsImpl implements GetPredictions {
    @Override
    public void getPrediction(String stopId, final OnFinishedListener listener) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BongoService.API_URL)
                .build();

        BongoService service = restAdapter.create(BongoService.class);

        service.getPredictions(stopId, new Callback<PredictionsWrapper>() {
            @Override
            public void success(PredictionsWrapper predictionsWrapper, Response response) {
                listener.onSuccess(predictionsWrapper.predictions);
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
}
