package com.sanga.schoolwash.ExpandableListView;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanga.schoolwash.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class HelpViewHolder extends ChildViewHolder {
    private TextView textView;
    private ImageView arrow;

    public HelpViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text_view);
        arrow = itemView.findViewById(R.id.expand);
    }

    public void bind(Help help){
        textView.setText(help.name);
    }

    public void expand() {
        animateExpand();
    }

    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF,
                        0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF,
                        0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
