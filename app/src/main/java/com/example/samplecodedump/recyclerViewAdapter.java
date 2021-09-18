package com.example.samplecodedump;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<StringHolder> recycledStrings = new ArrayList<StringHolder>();
    private Context context;

    public recyclerViewAdapter(ArrayList<StringHolder> recycledStrings, Context context) {
        this.recycledStrings = recycledStrings;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

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
