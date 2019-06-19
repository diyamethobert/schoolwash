package com.sanga.schoolwash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private Context context;
    private List<FormData> dataList;

    public ReportAdapter(Context context, List<FormData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.report_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.data = dataList.get(i);
        viewHolder.school.setText(viewHolder.data.getSchoolName());
        viewHolder.head.setText(viewHolder.data.getHeadName());
        viewHolder.no_of_girls.setText(viewHolder.data.getsFemale());
        viewHolder.no_of_boys.setText(viewHolder.data.getNoStaff());
        viewHolder.staff_toilets.setText(viewHolder.data.getfToilets());
        viewHolder.dustBins_view.setText(viewHolder.data.getDustBins());
        viewHolder.mToilets_view.setText(viewHolder.data.getmToilets());
        viewHolder.sToilets_view.setText(viewHolder.data.getsToilets());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView school, head, no_of_girls, no_of_boys, staff_toilets, mToilets_view,
                sToilets_view, dustBins_view;
        private FormData data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            school = itemView.findViewById(R.id.view_school);
            head = itemView.findViewById(R.id.head_master);
            no_of_girls = itemView.findViewById(R.id.no_girls_view);
            no_of_boys = itemView.findViewById(R.id.boys_view);
            staff_toilets = itemView.findViewById(R.id.toilets_view);
            mToilets_view = itemView.findViewById(R.id.mToilets_view);
            sToilets_view = itemView.findViewById(R.id.sToilets_view);
            dustBins_view = itemView.findViewById(R.id.dustBins_view);
        }
    }
}
