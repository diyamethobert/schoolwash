package com.sanga.schoolwash.ExpandableListView;

import android.view.View;
import android.widget.TextView;

import com.sanga.schoolwash.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class ExpandableViewHolder extends GroupViewHolder {
    private TextView textView;

    public ExpandableViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bind(HelpClass help){
        textView.setText(help.getTitle());
    }
}
