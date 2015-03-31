package com.wernerapps.ezbongo.StopListing;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.wernerapps.ezbongo.DatabaseObjects.Stop;
import com.wernerapps.ezbongo.R;

import java.util.List;

import static com.wernerapps.ezbongo.StopListing.StopPresenter.*;

public class ExpandableSwipeAdapter extends BaseExpandableListAdapter
{
    private final Context context;
    private final List<String> titles;
    private final List<List<Stop>> items;
    private final StopPresenter presenter;

    public ExpandableSwipeAdapter(Context context, StopPresenter presenter, List<String> titles, List<List<Stop>> items)
    {
        this.context = context;
        this.titles = titles;
        this.items = items;
        this.presenter = presenter;
        
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (items == null)
            return 0;
        return items.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition ;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = ((String) getGroup(groupPosition)).toString();
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.stop_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (childPosition == items.get(groupPosition).size())
            return null;
        final Stop listItem = ((Stop) getChild(groupPosition,
                childPosition));
        final String childText = listItem.getStopId() + " - " + listItem.getStopName();
        final boolean isFavorite = listItem.isFavorited;

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.stop_list_item, null);
        }

        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        TextView txtListChild = (TextView) convertView.findViewById(R.id.text_data);
        txtListChild.setText(childText);

        ImageButton times = (ImageButton) convertView.findViewById(R.id.incomingTimes);
        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(ItemAction.INCOMING_TIMES, groupPosition, childPosition, listItem);
            }
        });
        ImageButton reminder = (ImageButton) convertView.findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(ItemAction.REMINDER, groupPosition, childPosition, listItem);
            }
        });
        ImageButton favorite = (ImageButton) convertView.findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onItemClicked(ItemAction.FAVORITE, groupPosition, childPosition, listItem);
            }
        });
        if (isFavorite)
            favorite.setImageResource(R.drawable.ic_action_star_10);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}