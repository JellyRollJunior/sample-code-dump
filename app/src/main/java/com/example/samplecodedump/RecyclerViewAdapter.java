package com.example.samplecodedump;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

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
        holder.tvTop.setText(recycledStrings.get(position).getFirst());
        holder.tvMiddle.setText(recycledStrings.get(position).getSecond());

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
        TextView tvTop; // should probably make all of these private
        TextView tvMiddle;
        ImageView ivBot;
        MaterialCardView recycleLayoutSampleEleven;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTop = itemView.findViewById(R.id.tvTopSampleEleven);
            tvMiddle = itemView.findViewById(R.id.tvMiddleSampleEleven);
            ivBot = itemView.findViewById(R.id.ivSample35);
            recycleLayoutSampleEleven = itemView.findViewById(R.id.recycleLayoutSampleEleven);
        }
    }
}
