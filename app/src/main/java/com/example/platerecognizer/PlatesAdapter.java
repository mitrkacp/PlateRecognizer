package com.example.platerecognizer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlatesAdapter extends RecyclerView.Adapter<PlatesAdapter.PlatesViewHolder> {

    private Context mContext;
    ArrayList<String> mlistData;

    public PlatesAdapter(Context context, ArrayList<String> listData){
        mContext = context;
        mlistData = listData;
    }

    @NonNull
    @Override
    public PlatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.plate_item, parent, false);
        return new PlatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatesViewHolder holder, int position) {
        holder.plateNumber.setText(mlistData.get(position));
        holder.itemView.setTag(mlistData.get(position));
    }

    @Override
    public int getItemCount() {
        return mlistData.size();
    }

    public class PlatesViewHolder extends RecyclerView.ViewHolder{

        public TextView plateNumber;

        @SuppressLint("ResourceType")
        public PlatesViewHolder(@NonNull View itemView) {
            super(itemView);

            plateNumber = (TextView) itemView.findViewById(R.id.textview_plate_item);

        }
    }
}
