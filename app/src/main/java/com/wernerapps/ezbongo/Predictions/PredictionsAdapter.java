package com.wernerapps.ezbongo.Predictions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wernerapps.ezbongo.R;

import java.util.List;

/**
 * Created by Tom on 3/29/2015.
 */
public class PredictionsAdapter extends BaseAdapter {

    private final List<Prediction> predictions;
    private final Context context;
    private final PredictionsPresenter presenter;

    public PredictionsAdapter(Context context, PredictionsPresenter presenter, List<Prediction> predictions){
        this.predictions = predictions;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return predictions.size();
    }

    @Override
    public Object getItem(int position) {
        return predictions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Prediction prediction = predictions.get(position);

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.prediction_item, null);
        }

        TextView routeName = (TextView) convertView.findViewById(R.id.prediction_route_name);
        routeName.setText(prediction.title);

        TextView routeDirection = (TextView) convertView.findViewById(R.id.prediction_route_direction);
        routeDirection.setText(prediction.direction);

        TextView time = (TextView) convertView.findViewById(R.id.prediction_time);
        if (prediction.minutes == 0)
            time.setText("Arriving");
        else
            time.setText("" + prediction.minutes + "mins");

        return convertView;
    }
}
