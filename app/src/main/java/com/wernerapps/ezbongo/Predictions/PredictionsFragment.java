package com.wernerapps.ezbongo.Predictions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.wernerapps.ezbongo.MainActivity;
import com.wernerapps.ezbongo.Predictions.Reminders.ReminderDialog;
import com.wernerapps.ezbongo.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PredictionsFragment extends Fragment implements PredictionsView {
    public static final String TAG = "PredictionsFragment";

    private PredictionsPresenter presenter;
    private TextView errorButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    private static final String ARG_1 = "ARG_1";
    private static final String ARG_2 = "ARG_2";
    private String stopId;
    private AbsListView listView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PredictionsFragment newInstance(String stopId, String stopName) {
        PredictionsFragment fragment = new PredictionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_1, stopId);
        args.putString(ARG_2, stopName);
        fragment.setArguments(args);
        return fragment;
    }

    public PredictionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = new PredictionsPresenterImpl(this);

        Bundle args = getArguments();
        stopId = args.getString(ARG_1);
        String stopName = args.getString(ARG_2);

        super.onCreate(savedInstanceState);
        MainActivity activity = (MainActivity)getActivity();
        activity.getSupportActionBar().setTitle(stopName);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getPredictions(stopId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_predictions, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.predictions_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPredictions(stopId);
            }
        });

        listView = (AbsListView) rootView.findViewById(R.id.predictions_listview);

        errorButton = (Button) rootView.findViewById(R.id.predictions_error_display);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getPredictions(stopId);
            }
        });

        return rootView;
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setItems(final List<Prediction> predictions) {
        if (predictions.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            errorButton.setVisibility(View.GONE);

            PredictionsAdapter adapter = new PredictionsAdapter(listView.getContext(), presenter, predictions);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
            animationAdapter.setAbsListView(listView);
            listView.setAdapter(animationAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    presenter.onItemClicked(predictions.get(position));
                }
            });
        }
        else {
            listView.setVisibility(View.GONE);
            errorButton.setVisibility(View.VISIBLE);

            errorButton.setText("No Predictions Available.\nTap to refresh.");
        }
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        listView.setVisibility(View.GONE);
        errorButton.setVisibility(View.VISIBLE);
        System.out.println(errorMessage);
        errorButton.setText(errorMessage);
    }

    @Override
    public void showSetReminderDialog(Prediction prediction) {
        ReminderDialog dialog = ReminderDialog.newInstance(prediction);
        dialog.show(getFragmentManager(), dialog.TAG);
    }
}