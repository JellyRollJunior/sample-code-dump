package com.example.samplecodedump;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private final ArrayList<StringHolder> recycledStrings;
    private final Context context;

    public RecyclerViewAdapter(Context context, ArrayList<StringHolder> recycledStrings) {
        this.recycledStrings = recycledStrings;
        this.context = context;
    }

    // this function is responsible for inflating the view -> same in almost every recycler view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_sample_eleven, parent, false);
        return new ViewHolder(view);    // make sure to pass OUR created ViewHolder class
    }

    // this function gets called everytime items are recycled
    // write your layout code in here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        // set text views
        holder.tvLeft.setText(recycledStrings.get(position).getFirst());
        holder.tvMiddle.setText(recycledStrings.get(position).getSecond());
        holder.tvRight.setText(recycledStrings.get(position).getThird());

        // set onClickListener
        holder.recycleLayoutSampleEleven.setOnClickListener(view -> {
            Log.d(TAG, "onClick position: " + position);

            Toast.makeText(context, "You clicked on position: " + position, Toast.LENGTH_LONG).show();
        });
    }

    // show how many items to populate
    @Override
    public int getItemCount() {
        return recycledStrings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLeft; // should probably make all of these private
        TextView tvMiddle;
        TextView tvRight;
        ConstraintLayout recycleLayoutSampleEleven;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tvLeftSampleEleven);
            tvMiddle = itemView.findViewById(R.id.tvMiddleSampleEleven);
            tvRight = itemView.findViewById(R.id.tvRightSampleEleven);
            recycleLayoutSampleEleven = itemView.findViewById(R.id.recycleLayoutSampleEleven);
        }
    }
}
