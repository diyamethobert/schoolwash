package com.sanga.schoolwash.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanga.schoolwash.ExpandableListView.ExpandableViewHolder;
import com.sanga.schoolwash.ExpandableListView.Help;
import com.sanga.schoolwash.ExpandableListView.HelpClass;
import com.sanga.schoolwash.ExpandableListView.HelpViewHolder;
import com.sanga.schoolwash.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class HelpAdapter extends ExpandableRecyclerViewAdapter<ExpandableViewHolder, HelpViewHolder> {
    public HelpAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ExpandableViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_layout, parent, false);
        return new ExpandableViewHolder(view);
    }

    @Override
    public HelpViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exapandable_help, parent, false);
        return new HelpViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(HelpViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Help help = (Help) group.getItems().get(childIndex);
        holder.bind(help);
    }

    @Override
    public void onBindGroupViewHolder(ExpandableViewHolder holder, int flatPosition, ExpandableGroup group) {
        final HelpClass helpClass = (HelpClass) group;
        holder.bind(helpClass);
    }
}
