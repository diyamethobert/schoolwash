package com.sanga.schoolwash.ExpandableListView;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class HelpClass extends ExpandableGroup<Help> {
    public HelpClass(String title, List<Help> items) {
        super(title, items);
    }
}
