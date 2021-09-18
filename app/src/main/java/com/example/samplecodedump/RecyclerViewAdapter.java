package com.example.samplecodedump;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<StringHolder> recycledStrings = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<StringHolder> recycledStrings, Context context) {
        this.recycledStrings = recycledStrings;
        this.context = context;
    }

    // this function is responsible for inflating the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_sample_eleven, parent, false);
        return new ViewHolder(view);
    }

    // this function gets called everytime items are recycled
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.tvLeft.setText(recycledStrings.get(position).getFirst());
        holder.tvMiddle.setText(recycledStrings.get(position).getSecond());
        holder.tvRight.setText(recycledStrings.get(position).getThird());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeft;
        TextView tvMiddle;
        TextView tvRight;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tvLeftSampleEleven);
            tvMiddle = itemView.findViewById(R.id.tvMiddleSampleEleven);
            tvRight = itemView.findViewById(R.id.tvRightSampleEleven);
        }
    }
}
