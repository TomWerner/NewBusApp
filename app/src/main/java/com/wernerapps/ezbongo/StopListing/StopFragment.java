package com.wernerapps.ezbongo.StopListing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.MainActivity;
import com.wernerapps.ezbongo.Predictions.Prediction;
import com.wernerapps.ezbongo.Predictions.PredictionsFragment;
import com.wernerapps.ezbongo.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopFragment extends Fragment implements StopView {
    public static final String TAG = "StopFragment";

    private ExpandableListView listView;
    private StopPresenterImpl presenter;
    private TextView errorButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableSwipeAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StopFragment newInstance() {
        StopFragment fragment = new StopFragment();
        return fragment;
    }

    public StopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = new StopPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        presenter.loadStopListing(0, 0);

        MainActivity activity = (MainActivity)getActivity();
        activity.getSupportActionBar().setTitle("Stops");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stop, container, false);
        swipeRefreshLayout = (CustomSwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);

        listView = (ExpandableListView) rootView.findViewById(R.id.listview);

        errorButton = (Button) rootView.findViewById(R.id.errorDisplay);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadStopListing(0, 0);
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
    public void setItems(List<String> titles, List<List<Stop>> items, int groupPos, int childPos) {
        listView.setVisibility(View.VISIBLE);
        errorButton.setVisibility(View.GONE);

        adapter = new ExpandableSwipeAdapter(listView.getContext(), presenter, titles, items);
        listView.setAdapter(adapter);

        for (int i = 0; i < adapter.getGroupCount(); i++)
            listView.expandGroup(i);
    }

    @Override
    public void setItems(List<String> titles, List<List<Stop>> items) {
        setItems(titles, items, 0, 0);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getView().getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        listView.setVisibility(View.GONE);
        errorButton.setVisibility(View.VISIBLE);
        System.out.println(errorMessage);
        errorButton.setText(errorMessage);
    }

    @Override
    public Context getContext() {
        return getView().getContext();
    }

    @Override
    public void openPredictionFragment(Stop stop) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.container,
                        PredictionsFragment.newInstance(stop.getStopId(), stop.getStopName()),
                        PredictionsFragment.TAG)
                .addToBackStack(PredictionsFragment.TAG).
                commit();
    }


}
