package com.sanga.schoolwash.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanga.schoolwash.R;
import com.sanga.schoolwash.UI.HomeFragment;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private String [] categories;
    private int [] img;
    private Context context;
    private AdapterListener listener;

    public HomeAdapter(String[] categories, int[] img, Context context, HomeFragment fragment) {
        this.categories = categories;
        this.img = img;
        this.context = context;
        this.listener = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.home_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(categories[i]);
        viewHolder.imageView.setImageResource(img[i]);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.passValue(categories[position]);
                }
            });
        }
    }

    public interface AdapterListener{
        void passValue(String value);
    }
}
